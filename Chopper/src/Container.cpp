#include "../include/Container.h"
namespace isvr {

  ContainerImpl::ContainerImpl() {
  }

  ContainerImpl::~ContainerImpl() {
  }

  int ContainerImpl::initailize() {
    err_ = 0;
    wrapper_queue_ = new Queue();  
  }

  int ContainerImpl::addWrapper(Request *req,Response *resp){
    WrapperImpl *wrapper = new WrapperImpl(req,resp);
    wrapper_queue_->push((void *wrapper));
  }

  int ContainerImpl::run() {
    WrapperImpl *wrapper = (WrapperImple *) wrapper_queue_->pop();
    if(wrapper == NULL) {
      return 0;
    } else {
      wrapper.start();
    }
  }

  //thread function 
  static void* ContainerImpl::whiletrue(void *){
    if(pid_ == 0) {
      return -1;
    }
    for(;;) {
      for(;tid_ != 0;) {
        int logvalue = run();
      }
      for(;tid_ == 0;) {
        return NULL;
      }
    }
  }
}

  bool ContainerImpl::isStart() {
    return tid_ != 0;
  }

  //this is the Lifecycle's function.should be abstracted to the Lifecycle.class
  int ContainerImpl::start() {
    if(tid_ == 0) {
      err_ = pthread_create(&tid_, NULL,WrapperImpl::whiletrue,NULL); 
      return err_;
    } else {
      return tid_;
    } 
  }

  int ContainerImpl::stop() {
    if(pid_ == 0){
      return -1;
    }
    else {
      err_ = pthread_kill(pid_,0);
      if(err_ == EINVAL) {
        return EINVAL;
      } else if(err_ == ESRCH){
        return ESRCH;
      }
      tid_ = 0;
      return err_; 
    }
  }
}
