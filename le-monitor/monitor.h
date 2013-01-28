#ifndef _MONITOR_MONITOR_H_
#define _MONITOR_MONITOR_H_

#include "common.h"
#include "monitor.h"
#include "util.h"

namespace le { namespace tpmonitor {

class ProxyProc {
 public:
  ProxyProc();
  virtual ~ProxyProc();
  int loadCfg(char* cfg);
  pid_t start();
 protected:
  bool enable_;
  std::map<char*, char*> cfg_;
  size_t argc_;
  const char** argv_;
  pid_t pid_;
};

class Monitor {
 public:
  Monitor();
  virtual ~Monitor();
  void setCfgs(const std::set<char*> cfgs);
  void start();
 protected:
  const std::set<char*> cfgs_;
  std::map<char*, ProxyProc> pids_;
}; 

}}

#endif
