#ifndef _MONITOR_MONITOR_H_
#define _MONITOR_MONITOR_H_

#include "common.h"
#include "monitor.h"
#include "proxyproc.h"
#include "util.h"

namespace le { namespace tpmonitor {

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
