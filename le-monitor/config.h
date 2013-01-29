#ifndef _LEMONITOR_CONFIG_H_
#define _LEMONITOR_CONFIG_H_

#include "common.h"

namespace le { namespace tpmonitor {

using namespace std;

class Configuration {
 public:
  ~Configuration();
  void dump();

  static Configuration* readConfig(const char* monitor_cfg);
  const set<string> getAppCfgs();
  void showAppCfgs();

 private:
  const string load_path_;
  const string cfg_dir_;
  const string log_dir_;
  set<string> cfg_files_;

  Configuration(const string load_path, const string cfg_dir, const string log_dir);
  void loadFiles();
};

}}

#endif
