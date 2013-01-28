#ifndef _MONITOR_UTIL_H_
#define _MONITOR_UTIL_H_

#include <stdio.h>
#include <string.h>
#include <set>
#include <map>
#include <limits.h>

namespace le { namespace tpmonitor {

class Util {
 public:
  static void tolower (char* str);
  static char* next (char **line, const char *post_delim);
  static char* first (char **line, const char *pre_delim, const char *post_delim); 
  static int index (char* line, const char index, int flag);
  static void trim (char* line);
  static size_t count (const char* str, const char target);

  static int parse_args (const char* str, size_t* argn, const char** argv);
  static std::map<char*,char*> loadKV (FILE* file);
  static void releaseKV (std::map<char*, char*> imap);
};

}}

#endif
