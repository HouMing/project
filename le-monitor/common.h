#ifndef _MONITOR_COMMON_H_
#define _MONITOR_COMMON_H_

#include <vector>
#include <map>
#include <string>
#include <iostream>
#include <iomanip>
#include <set>

#include <regex.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

#define SHOW_ERROR cerr << "FILE:" << __FILE__ << " LINE:" << __LINE__ << " " << "error" << "(" << errno << "):" << strerror(errno) << endl;


#endif
