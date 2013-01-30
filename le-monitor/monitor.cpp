#include "monitor.h"
#include "config.h"

namespace le { namespace tpmonitor {

using namespace std;
Monitor::Monitor ()
{}

Monitor::~Monitor ()
{}

void Monitor::setCfgs(const std::set<string> cfgs)
{
  cfgs_ = cfgs; 
}

void Monitor::start()
{
  for (set<string>::iterator it = cfgs_.begin(); it != cfgs_.end(); ++it) {
    ProxyProc proc; 
    proc.loadCfg(it->data());
    proxys_.insert(pair<string, ProxyProc>(*it, proc));
  }

  for (map<string, ProxyProc>::iterator it = proxys_.begin(); it != proxys_.end(); ++it) {
        it->second.start();
  }
   
  while (1) {
    for (map<string, ProxyProc>::iterator it = proxys_.begin(); it != proxys_.end(); ++it) {
      pid_t p = waitpid(it->second.pid_, NULL, WNOHANG);
      if (p > 0) {
        it->second.start();
      }
    }
    sleep(1);
  }
}

}}

#if 1
using namespace std;
int main(int argc, char** argv)
{
  le::tpmonitor::Configuration* test = le::tpmonitor::Configuration::readConfig("/home/hm/test/test.cfg");
  if (!test) {
    return -1;
  }
  le::tpmonitor::Monitor master;
  master.setCfgs(test->getAppCfgs());
  master.start();
  delete test;
  return 0;
}
#endif
