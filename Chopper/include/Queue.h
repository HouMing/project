#ifndef _SERVERCAT_QUEUE_H_
#define _SERVERCAT_QUEUE_H_ 
#include "common.h"

#define SEFAULT_SIZE 255

namespace isvr {
/**
 * thread safety
 */
  class Queue {
    public:
      Queue(); 
      void* pop(); 
      int push(void* elem); 
      const void* top();
    protected:
      pthread_mutex_t lock_;
      int count_;
      int tail_;
      int head_;
      void **data_;
  };
}

#endif
