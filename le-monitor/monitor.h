#include "monitor.h"

namespace le { namespace tpmonitor {

class ProxyProc {
 public:
  bool enable_;
  pid_t pid_; 
  const char* bin_;
  const char* run_path_;
  const char* argv;
}

class Monitor {
 public:
  Monitor();
  ~Monitor();
  void setCfgFiles(const set<char*> cfgs);
  void start();
 protected:
  const set<char*> cfgs_;
  map<char*,pid_t> pids_;
  
}; 

}}
