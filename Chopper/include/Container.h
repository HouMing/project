#ifndef _SERVERCAT_CONTAINER_H_
#define _SERVERCAT_CONTAINER_H_ 

#include "common.h"
#include "Queue.h"
#include "Wrapper.h"

namespace isvr {

  class Container{
    public:
      int initailize()=0;
      int addWrapper(Request *req,Response *resp);
  };

  class ContainerImpl : public Container{
    public:
      int initailize();
      int addWrapper(Request *req,Response *resp);
      //this is the Lifecycle's function.should be abstracted to the Lifecycle.class
      int start();
      int stop();
      bool isStart();
      ContainerImpl();
      ~ContainerImpl();
    protected:
      int run();
      static int whiletrue();
    protected:
      //Queue for wrapper
      Queue* wrapper_queue_;
      int err_;
      int tid_;
  }
}

#endif
