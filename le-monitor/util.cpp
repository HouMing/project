#include "util.h"
#include "common.h"
#include <iostream>
#include <string>

namespace le { namespace tpmonitor {

using namespace std;

void Util::tolower (char* str) 
{
  for (; *str != '\0'; ++str) {
    char c = *str;
    switch (c) {
      case 'A' ... 'Z' :
        *str = c - 'A' + 'a';
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
  bool t = true;
  while (line[b] != '\0') {
    if (line[b] == ' ' && t) {
      ++b;
      t = true;
    } else {
      line[a] = line[b];
      ++a;
      ++b;
      t = false;
    }
  }
  if (line[a - 1] == ' ') {
    line[a - 1] = 0;
  }
  line[a] = 0;
}

size_t Util::count (const char* str, const char target)
{
  size_t cnt = 0;
  for (;*str != '\0'; ++str) {
    if (target == *str) {
      ++cnt;
    }
  }
  return cnt;
}

int Util::parse_args (const char* str, size_t* argn, char*** argv)
{
  if ( str == NULL || *argv != NULL || argn == NULL || *argn != 0) 
  {
    SHOW_ERROR;
    abort();
  }
  if (!strlen(str)) {
    SHOW_ERROR;
    abort();
  }

  *argn = Util::count(str, ' ') + 2;// COUNT(arg0,arg1,...,argN,NULL) == N + 2
  *argv = new char*[*argn];

  off_t off_str = 0;
  size_t n = 0;
  const char* p = str;
  for (;n < *argn - 1; ++n) {
    off_str = ::strcspn(p, " \0");
    char* tmp = new char[off_str + 1];
    memcpy(tmp, p, sizeof(char) * off_str);
    tmp[off_str] = 0;
    argv[0][n] = tmp;
    p = p + off_str + 1;
  }
  argv[0][*argn - 1] = NULL;
  return 0;
}

std::map<string, char*> Util::loadKV (FILE* file)
{
  std::map<string, char*> ret;
  if (file == NULL) {
    return ret;
  }
  rewind(file);
  char line[FILENAME_MAX + 1];
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
    string ikey(key);
    char* ival = new char[FILENAME_MAX + 1];
    strcpy(ival, val);
    ret.insert(std::pair<string, char*>(ikey, ival));
  }
  return ret;
}

void Util::releaseKV (std::map<string, char*> imap)
{
  for (std::map<string, char*>::iterator it = imap.begin();
       it != imap.end();
       ++it) {
    if (it->second != NULL) {
      delete it->second;
      it->second = NULL;
    }
    imap.erase(it);
  }
}

}}
