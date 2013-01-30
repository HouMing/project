#ifndef _MONITOR_COMMON_H_
#define _MONITOR_COMMON_H_

#include <pthread.h>
#include <iostream>
#include <iomanip>

#include <vector>
#include <map>
#include <string>
#include <set>

#include <regex.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

#define SHOW_ERROR std::cerr << "FILE:" << __FILE__ << " LINE:" << __LINE__ << " " << "error" << "(" << errno << "):" << strerror(errno) << std::endl;

#endif
