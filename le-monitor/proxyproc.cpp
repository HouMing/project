#include "common.h"
#include "util.h"
#include "proxyproc.h"

namespace le { namespace tpmonitor {

ProxyProc::ProxyProc ()
{
  enable_ = false;
  argv_ = NULL;
  argc_ = 0;
  pid_ = -1;
}

ProxyProc::~ProxyProc ()
{

}

int ProxyProc::loadCfg (const char* cfg)
{
  FILE* cfg_file = fopen(cfg, "r");
  std::map<string, char*>::iterator it;
  if (cfg_file == NULL) {
    cout << "file name:" << cfg << endl;
    SHOW_ERROR; 
    abort();
  }
  std::map<string, char*> kv = Util::loadKV(cfg_file);
  fclose(cfg_file);

#if 0
  for (map<string, char*>::iterator it = kv.begin(); it != kv.end(); ++it) {
    cout << it->first << ":" << it->second << endl; 
  }
#endif

  int check = 0;
  for (it = kv.begin(); it != kv.end(); ++it) {
    // parse shell, input the shell command to ProxyProc
    if (!it->first.compare("shell")) {
      if (argv_ != NULL || argc_ != 0) {
        delete argv_;
        argv_ = NULL;
        argc_ = 0;
      }
      Util::parse_args(it->second, &argc_, &argv_);
      ++check;
    }

    // set PATH, input the path of ProxyProc
    if (!it->first.compare("path")) {
      size_t len = strlen(it->second);
      char* tmp = new char[len + 1];
      strcpy(tmp,it->second);
      tmp[len] = 0;
      path_ = tmp;
    }

    // enable ProxyProc
    if (!it->first.compare("enable")) {
      if (!strcpy(it->second, "false")) {
        enable_ = false;
      } else {
        enable_ = true;
      }
      ++check;
    }
  }

#if 0
  for (size_t i = 0; i < argc_; ++i) {
    cout << i << ":" << argv_[i] << endl;
  }
  getchar();
#endif

  Util::releaseKV(kv);
  if (check == 3) {
    return 0;
  } else {
    return check;
  }
}

pid_t ProxyProc::start ()
{
  if (!enable_) {
    return 0;
  }

  pid_ = fork();
  if (pid_ > 0) {
    return pid_;
  }

  if (pid_ < 0) {
    SHOW_ERROR;
    return -1;
  }

  if (pid_ == 0) {
#ifdef LOG
    //add glog
    cout << "exec:" << getppid() << endl;
    int i = 0;
    while (argv_[i] != NULL) {
      cout << argv_[i++] << " ";
    }
    cout << endl;
#endif
    chdir(path_);
    execv(argv_[0], argv_);
    return 0;
  }
  return -1;
}
}}

#if 0
using namespace std;
int main(int argc, char** argv)
{
  le::tpmonitor::ProxyProc p;
  p.loadCfg("/home/hm/test/test.cfg");
  p.start();
  return 0; 
}
#endif
