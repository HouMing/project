#ifndef _SERVERCAT_SERVER_H_
#define _SERVERCAT_SERVER_H_
#include "common.h"
#include "Service.h"

namespace isvr{

  class Server{
    public:
      char* getInfo()=0;
      int getPort()=0;
      int setPort(int port)=0;
      const char* getShutdown()=0;
      int setShutdown(const char *shutdown)=0;
      int addService(Service *service)=0;
      int await()=0;
      Service *findService(const char* name)=0;
      int removeService(const char* name)=0;
      Queue* findServices()=0;
      int initailize()=0;
  };

  class ServerImpl : public Server{
    public:
      char* getInfo();
      int getPort();
      int setPort(int port);
      const char* getShutdown();
      int setShutdown(const char *shutdown);
      int addService(Service *service);
      int await();
      Service *findService(const char* name);
      int removeService(const char* name);
      Queue* findServices();
      int initailize();
      //this is the Lifecycle's function.should be abstracted to the Lifecycle.class
      int start();
      int stop();
      ServerImpl();
      ~ServerImpl();
    public:
      char* info_;
      Queue* services_; 
  };

}

#endif
