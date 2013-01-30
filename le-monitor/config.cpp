#include "config.h"
#include "util.h"
#include <dirent.h>

#define CONF_REG "^.*\\.cfg$"

namespace le { namespace tpmonitor {

Configuration::Configuration (const string load_path, const string cfg_dir, const string log_dir)
    : load_path_(load_path),
    cfg_dir_(cfg_dir),
    log_dir_(log_dir)
  {}

Configuration::~Configuration ()
{}

void Configuration::loadFiles ()
{
  cfg_files_.clear();
  DIR* dir = opendir(cfg_dir_.data());
  if (!dir) {
    cout << "dir is not exist : " << cfg_dir_.data() << endl;
    SHOW_ERROR;
    exit(-1);
  }
  dirent *pf;
  regex_t rx;
  if (regcomp(&rx, CONF_REG, REG_ICASE | REG_NOSUB)) {
    SHOW_ERROR;
  }
  char* file_name;
  while ((pf = readdir(dir))) {
    file_name = pf->d_name;
    if (!regexec(&rx, file_name, 1, NULL, 0)) {
      string elem = cfg_dir_ + file_name;
      cfg_files_.insert(elem);
    }
  }
  closedir(dir);
}

Configuration* Configuration::readConfig (const char* monitor_cfg)
{
  FILE *f_monitor_cfg = fopen(monitor_cfg, "r");
  if (!f_monitor_cfg) {
    // TODO add google log error output
    SHOW_ERROR;
    exit(-1);
  }
  Configuration* cfg;
  string cfg_dir;
  string log_dir;

  char line[1024];
  int lineno = 0;
  char *p, *key, *val;
  while (!feof(f_monitor_cfg)) {
    if ( NULL == fgets(line, sizeof(line), f_monitor_cfg)) {
      break;
    }
    ++lineno;
    int left = strcspn(line, "#\r\n");
    if (left == 0) {
      continue;
    } else {
      line[left] = 0;
      p = line;
    }
    int pos = -1;
    pos = Util::index(p, '=', -1);
    if (pos == -1) {
      continue;
    } else {
      line[pos] = '\0';
      key = p; 
      val = p + pos + 1;
    }
    Util::trim(key);
    Util::trim(val);
    Util::tolower(key); 
    Util::tolower(val); 
    if (!strcasecmp(key, "cfg.dir")) {
      cfg_dir.replace(0, strlen(val), val);
    }
    if (!strcmp(key, "log.dir")) {
      log_dir.replace(0, strlen(val), val);
    }
  }
  cfg = new Configuration(monitor_cfg, cfg_dir, log_dir);
  cfg->getAppCfgs();
  return cfg;
}

const set<string> Configuration::getAppCfgs ()
{
  loadFiles();
  return cfg_files_;
}

void Configuration::showAppCfgs()
{
  for (set<string>::iterator it = cfg_files_.begin(); it != cfg_files_.end(); ++it) {
    cout << *it << endl;
  }
}

}}

#if 0
int main(int argc, char** argv) 
{
  le::tpmonitor::Configuration* test = le::tpmonitor::Configuration::readConfig("/home/hm/test/test.cfg");
  if (!test) {
    return -1;
  }
  test->getAppCfgs();
  test->showAppCfgs();
  delete test;
  return 0;
}
#endif
