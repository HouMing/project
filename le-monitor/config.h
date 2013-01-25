#ifndef _LEMONITOR_CONFIG_H_
#define _LEMONITOR_CONFIG_H_

namespace le { namespace tpmonitor {

using namespace std;

class Configuration {
 public:
  ~Configuration();

  void dump();

  static Configuration* readConfig(const char* load_path);
  set<string> getAppSet();
  map<string, string> getAppConfMap(const string& key);

 private:
  const string load_path_;
  const char* cfg_dir_;
  const char* log_dir_;
  set<string> config_files_;
  map<string, map<string, string> > cfg_files_;

  Configuration(const string load_path, const char *cfg_dir, const char *log_dir);
  void loadFiles();
};

}}

#endif
