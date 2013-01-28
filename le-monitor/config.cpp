#include "config.h"
#include "util.h"
#include <dirent.h>

#define CONF_REG "^.*\\.cfg$"

namespace le { namespace tpmonitor {

Configuration::Configuration (const char* load_path, const char cfg_dir[], const char log_dir[])
{
  if (sizeof(cfg_dir) > FILENAME_MAX || sizeof(log_dir) > FILENAME_MAX) {
    abort();
  }
  char* tmp = new char [FILENAME_MAX + 1];
  strcpy(tmp, load_path);
  tmp[FILENAME_MAX] = 0;
  load_path_ = tmp;

  tmp = new char [FILENAME_MAX + 1];
  strcpy(tmp, cfg_dir);
  tmp[FILENAME_MAX] = 0;
  cfg_dir_ = tmp;

  tmp = new char [FILENAME_MAX + 1];
  strcpy(tmp, log_dir);
  tmp[FILENAME_MAX] = 0;
  log_dir_ = tmp;
}

Configuration::~Configuration ()
{
  for (set<char*>::iterator it = cfg_files_.begin(); it != cfg_files_.end(); ++it) {
    delete *it;
  }
  if (!cfg_dir_) {
    delete cfg_dir_;
  }
  if (!log_dir_) {
    delete log_dir_;
  }
}

void Configuration::loadFiles ()
{
  cfg_files_.clear();
  DIR* dir = opendir(cfg_dir_);
  if (!dir) {
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
      char* elem = new char[FILENAME_MAX+1];
      strcpy(elem, file_name);
      elem[FILENAME_MAX] = 0;
      cfg_files_.insert(elem);
    }
  }
  closedir(dir);
}

Configuration* Configuration::readConfig (const char* load_path)
{
  FILE *cfg_fd = fopen(load_path, "r");
  if (!cfg_fd) {
    // TODO add google log error output
    SHOW_ERROR;
    return NULL;
  }
  Configuration* cfg;
  char cfg_dir[FILENAME_MAX + 1], log_dir[FILENAME_MAX + 1];
  char line[1024];
  int lineno = 0;
  char *p, *key, *val;

  while (!feof(cfg_fd)) {
    if ( NULL == fgets(line, sizeof(line), cfg_fd)) {
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
    if (!strcmp(key, "cfg.dir")) {
      cout << key << endl;
      strcpy(cfg_dir, val);
      cfg_dir[FILENAME_MAX] = 0;
    }
    if (!strcmp(key, "log.dir")) {
      strcpy(log_dir, val);
      log_dir[FILENAME_MAX] = 0;
    }
  }
  cfg = new Configuration(load_path, cfg_dir, log_dir);
  cfg->getAppCfgs();
  return cfg;
}

const set<char*> Configuration::getAppCfgs ()
{
  loadFiles();
  return cfg_files_;
}

void Configuration::showAppCfgs()
{
  for (set<char*>::iterator it = cfg_files_.begin(); it != cfg_files_.end(); ++it) {
    cout << *it << endl;
  } 
}

}}

int main(int argc, char** argv) 
{
  le::tpmonitor::Configuration* test = le::tpmonitor::Configuration::readConfig("/home/hm/test/test.cfg");
  if (!test) {
    return -1;
  }
  test->getAppCfgs();
  delete test;
  return 0;
}
