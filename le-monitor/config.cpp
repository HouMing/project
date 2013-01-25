#include "common.h"
#include "config.h"

namespace le { namespace tpmonitor {


void tolower(char* str) 
{
  for (; *str != '\0'; ++str) {
    char c = *str;
    switch (c) {
      case 'A' ... 'Z' :
        *str = c - 'Z' - 'z';
        break;
    }
  }
}

char* next(char **line, const char *post_delim)
{   
  char *p = *line;
  char *ret = p;
  int len = ::strlen(p);
  if (len == 0) {
    return NULL;
  }     

  int pos = ::strcspn(p, post_delim);
  if (pos == 0) {
    return NULL;
  } 

  p += pos;
  *p++ = 0;

  pos = ::strspn(p, post_delim);
  p += pos;

  *line = p;
  return ret;
}

char* first(char **line, const char *pre_delim, const char *post_delim)
{   
  char *p = *line;
  int len = strlen(p);
  int pos = ::strspn(p, pre_delim);

  if (pos == len) {
    return NULL;
  }
  p += pos;
  *line = p;
  return next(line, post_delim);
} 

Configuration::Configuration (const string load_path, const char *cfg_dir, const char *log_dir)
:  load_path_(load_path),
    cfg_dir_(cfg_dir),
    log_dir_(log_dir)
{
}

Configuration::~Configuration ()
{}

void Configuration::loadFiles() {
  cout << "load files" << endl;
  FILE* dir = fopen(cfg_dir_);
}

Configuration* Configuration::readConfig (const char* load_path)
{
  FILE *fpcfg = fopen(load_path, "r");
  if (!fpcfg) {
    // TODO add google log error output
    cout << "(" << errno << ")" << strerror(errno) << endl;
    return NULL;
  }
  Configuration* cfg;
  const char* cfg_dir;
  const char* log_dir;

  char line[1024];
  int lineno = 0;
  while (!feof(fpcfg)) {
    if ( NULL == fgets(line, sizeof(line), fpcfg)) {
      break;
    }
    ++lineno;
    int left = strcspn(line, "#\r\n");
    if (left == 0) {
      continue;
    }
    line[left] = 0;
    char* p = line; 

    char* type = first(&p, " ", "= "); 
    if (type == NULL) {
      //LOG(ERROR) << "wrong line " << lineno << ": missing `type'"; 
      exit(1); 
    } 
    tolower(type); 
    if (!strcmp(type, "cfg.dir")) {
      cfg_dir = next(&p, " ");
      cout << cfg_dir << endl;
    }
    if (!strcmp(type, "log.dir")) {
      log_dir = next(&p, " ");
      cout << log_dir << endl;
    }
  }
  cfg = new Configuration(load_path, cfg_dir, log_dir);
  cfg->loadFiles();
  return cfg;
}

set<string> Configuration::getAppSet ()
{
  set<string> ret;
  return ret;
}

map<string, string> Configuration::getAppConfMap (const string& key)
{
  map<string, string> ret;
  return ret;
}
}}

int main(int argc, char** argv) 
{
  std::cout << "ddd" << std::endl;
  le::tpmonitor::Configuration* test = le::tpmonitor::Configuration::readConfig("/home/hm/test/test.cfg");
  return 0;
}
