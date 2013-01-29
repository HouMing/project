#include "monitor.h"

namespace le { namespace tpmonitor {

using namespace std;
Monitor::Monitor ()
{}

Monitor::~Monitor ()
{}

void Monitor::setCfgs(const std::set<string> cfgs)
{
   
}

void Monitor::start()
{}

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
  delete test;
  return 0;
}
#endif
