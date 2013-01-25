#ifndef _SERVERCAT_PROCESSOR_H_
#define _SERVERCAT_PROCESSOR_H_ 

#include "common.h"
#include "Request.h"
#include "Response.h"
#include "Connector.h"

namespace isvr {

  class Processor {
    public:
      virtual int setSocketStatus(const int sockid, const sockaddr *sock)=0;
      virtual Request* produceRequest()=0;
      virtual Response* produceResponse()=0;
      virtual int clean()=0;
  };

  class ProcessorImpl: public Processor {
    public:
      int setSocketStatus(SocketStatus *status);
      Request* produceRequest();
      Response* produceResponse();
      int clean();
      ProcessorImpl();
      ~ProcessorImpl();
    protected:
      int sockid_;
      const sockaddr_in *client_;
    private :
      bool used_;
  }; 

}

#endif
