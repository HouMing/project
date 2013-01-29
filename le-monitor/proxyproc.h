#ifndef _MONITOR_PROXYPROC_H_
#define _MONITOR_PROXYPROC_H_

namespace le { namespace tpmonitor {

class ProxyProc {
 public:
  ProxyProc();
  virtual ~ProxyProc();
  int loadCfg(const char* cfg);
  pid_t start();
 protected:
  bool enable_;
  size_t argc_;
  char** argv_;
  const char* path_;
  pid_t pid_;
};

}}
#endif
