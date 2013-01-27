#include "util.h"

namespace le { namespace tpmonitor {

void Util::tolower (char* str) 
{
  for (; *str != '\0'; ++str) {
    char c = *str;
    switch (c) {
      case 'A' ... 'Z' :
        *str = c - 'Z' - 'z';
        break;
    }
  }
}

char* Util::next (char **line, const char *post_delim)
{   
  char *p = *line;
  char *ret = p;
  int len = ::strlen(p);
  if (len == 0) {
    return NULL;
  }     

  int pos = ::strcspn(p, post_delim);
  if (pos == 0) {
    return NULL;
  } 

  p += pos;
  *p++ = 0;

  pos = ::strspn(p, post_delim);
  p += pos;

  *line = p;
  return ret;
}

char* Util::first (char **line, const char *pre_delim, const char *post_delim)
{
  char *p = *line;
  int len = strlen(p);
  int pos = ::strspn(p, pre_delim);

  if (pos == len) {
    return NULL;
  }
  p += pos;
  *line = p;
  return next(line, post_delim);
} 

int Util::index (char* line, const char index, int flag)
{
  int pos = 0;
  int ret = -1;
  for (; line[pos] != '\0'; ++pos) {
    if (line[pos] == index) {
      if (flag == -1) {
        ret = pos;
        break;
      }
      if (flag == 1) {
        ret = pos;
      }
    }
  }
  return ret;
}

void Util::trim (char* line)
{
  if (line == NULL) {
    return;
  }
  int a = 0;
  int b = 0;
  while (line[b] != '\0') {
    if (line[b] == ' ') {
      ++b;
    } else {
      line[a] = line[b];
      ++a;
      ++b;
    }
  }
  line[a] = 0;
}

std::set<char*> Util::loadProperties (FILE* file)
{
  std::set<char*> ret;
  char line[1024];
  int lineno = 0;
  char* p;
  char* key;
  char* val;
  while (!feof(file)) {
    if ( NULL == fgets(line, sizeof(line), file)) {
      break;
    }
    ++lineno;
    int left = strcspn(line, "#\r\n");
    if (left == 0) {
      continue;
    } else {
      line[left] = 0;
      p = line;
    }
    int pos = -1;
    pos = Util::index(p, '=', -1);
    if (pos == -1) {
      continue;
    } else {
      line[pos] = '\0';
      key = p; 
      val = p + pos + 1;
    }
    Util::trim(key);
    Util::trim(val);
    Util::tolower(key); 
    Util::tolower(val);
  }
    return ret;
}

}}
