#ifndef _SERVERCAT_CONNECTOR_H_
#define _SERVERCAT_CONNECTOR_H_ 

#include "common.h"
#include "Server.h"
#include "Queue.h"
#include "Request.h"
#include "Response.h"

namespace isvr{
  
  class SocketStatus{
    public:
    int sockid_;
    sockaddr_in sockaddr_;
  };

  class Connector{
    public:
      int initailize()=0;
      Request* createRequest()=0;
      Response* createResponse()=0;
  };

  class ConnectorImpl: public Connector{
    public:
      int initailize();
      int start();
      int stop();
      bool isStart();
      Request* createRequest();
      Response* createResponse();
      //no implements;
      ConnectorImpl();
      ~ConnectorImpl();
    protected:
      static void* run(void *);
      int setProcessorsCount(size_t count);
    protected:
      pthread_t tid_;
      //the elements show implements the Processor interface
      Queue* processor_pool_;
      int sockid_;
      sockaddr_in server_addr_;
      int err_;
  };
}

#endif
