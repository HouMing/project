#include "monitor.h"

namespace le { namespace tpmonitor {
ProxyProc::ProxyProc ()
{
  enable_ = false;
  argv_ = NULL;
  argc_ = 0;
  pid_ = -1;
}

ProxyProc::~ProxyProc ()
{}

int ProxyProc::loadCfg (char* cfg)
{
  FILE* cfg_file = fopen(cfg, "r");
  std::map<char*, char*>::iterator it;
  if (cfg_file == NULL) {
    SHOW_ERROR; 
    abort();
  }
  std::map<char*, char*> kv = Util::loadKV(cfg_file);
  int check = 0;
  for (it = kv.begin(); it != kv.end(); ++it) {
    if (!strcpy(it->first, "shell")) {
      if (argv_ != NULL || argc_ != 0) {
        delete argv_;
        argv_ = NULL;
        argc_ = 0;
      }
      Util::parse_args(it->second, &argc_, argv_);
      ++check;
    }

    if (!strcpy(it->first, "enable")) {
      if (!strcpy(it->second, "false")) {
        enable_ = false;
      } else {
        enable_ = true;
      }
      ++check;
    }
  }
  Util::releaseKV(kv);
  if (check == 2) {
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
    //execle(bin_, );
  }
}

Monitor::Monitor ()
{

}

Monitor::~Monitor ()
{}

}}
