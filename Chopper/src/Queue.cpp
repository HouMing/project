#include "../include/common.h"

namespace isvr {

  Queue::Queue(){
    ret->count_ = 0;
    ret->head_ = 0;
    ret->tail_ = 0;
    ret->data_ = (void **) malloc(sizeof(void *) * DEFAULT_SIZE);
    if(!ret->data_) {
      abort(-2);
    }
  } 

  void* Queue::pop(){
    void *ret=NULL;
    pthread_mutex_lock(&lock_);
    if(count_ == 0) {
      pthread_mutex_unlock(&lock_);
      return NULL;
    }
    head_ = (head_ + 1) % DEFAULT_SIZE;
    ret = data_[head_] ;
    count_--;
    pthread_mutex_unlock(&lock_);
    return ret;
  }

  int Queue::push(void* elem) {
    pthread_mutex_lock(&lock_);
    if(elem == NULL) {
      pthread_mutex_unlock(&lock_);
      return count_;
    }
    if(count_ == DEFAULT_SIZE) {
      pthread_mutex_unlock(&lock_);
      return -1;
    }
    tail_ = (tail_ + 1) % DEFAULT_SIZE;
    data_[tail_] = elem;
    count_++;
    pthread_mutex_unlock(&lock_);
    return count_;
  }

  void* Queue::top() {
    if(count_ == 0) {
      return NULL;
    }
    return data_[(head_+1)%DEFAULT_SIZE];
  }

}
