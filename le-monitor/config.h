#ifndef _LEMONITOR_CONFIG_H_
#define _LEMONITOR_CONFIG_H_

#include "common.h"

namespace le { namespace tpmonitor {

using namespace std;

class Configuration {
 public:
  ~Configuration();
  void dump();

  static Configuration* readConfig(const char* load_path);
  const set<char*> getAppCfgs();
  void showAppCfgs();

 private:
  const char* load_path_;
  const char* cfg_dir_;
  const char* log_dir_;
  set<char*> cfg_files_;

  Configuration(const char* load_path, const char *cfg_dir, const char *log_dir);
  void loadFiles();
};

}}

#endif
