#ifndef _MONITOR_UTIL_H_
#define _MONITOR_UTIL_H_

#include <stdio.h>
#include <string.h>
#include <set>

namespace le { namespace tpmonitor {

class Util {
 public:
  static void tolower (char* str);
  static char* next (char **line, const char *post_delim);
  static char* first (char **line, const char *pre_delim, const char *post_delim); 
  static int index (char* line, const char index, int flag);
  static void trim (char* line);
  static std::set<char*> loadProperties (FILE* file);
};

}}

#endif
