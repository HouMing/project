#ifndef _MONITOR_MONITOR_H_
#define _MONITOR_MONITOR_H_

#include "common.h"
#include "monitor.h"
#include "proxyproc.h"
#include "util.h"

namespace le { namespace tpmonitor {

using namespace std;

class Monitor {
 public:
  Monitor();
  virtual ~Monitor();
  void setCfgs(const std::set<string> cfgs);
  void start();
 protected:
  set<string> cfgs_;
  map<string, ProxyProc> proxys_;
}; 

}}

#endif
