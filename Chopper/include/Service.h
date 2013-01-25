#ifndef _SERVERCAT_SERVICE_H_
#define _SERVERCAT_SERVICE_H_ 

#include "common.h"
#include "Connector.h"
#include "Container.h"

namespace isvr{
  class Service {
    public:
      Container* getContainer()=0;
      int setContainer(Container* container)=0;
      char* getInfo()=0;
      char* getName()=0;
      int setName(const char* name)=0;
      Server* getServer()=0;
      int setServer(Server* server)=0;
      int addConnector(Connector* connector)=0;
      Queue* findConnectors()=0;
      int removeConnector(Connector *connector)=0;
      int initialize()=0;
  }

  class ServiceImpl {
    public:
      Container* getContainer();
      int setContainer(Container* container);
      char* getInfo();
      char* getName();
      int setName(const char* name);
      Server* getServer();
      int setServer(Server* server);
      int addConnector(Connector* connector);
      Queue* findConnectors();
      int removeConnector(Connector *connector);
      int initialize();
      //this is the Lifecycle's function.should be abstracted to the Lifecycle.class
      int start();
      int stop();
      ServiceImpl();
      ~ServiceImpl();
    protected:
      char* info_;
      char* name_;
      bool initialized_;
      bool start_;
      Container *container_;
      Server *server_;
      Queue* queue_;
  }
}


#endif
