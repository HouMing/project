#ifndef _SERVERCAT_REQUEST_H_
#define _SERVERCAT_REQUEST_H_ 
#include "common.h"
#include "Server.h"

namespace isvr{

  class Request{
    public:
      virtual size_t read(char *buf, size_t size)=0;
      virtual ~Request()=0;
  };

  class RequestImpl: public Request{
    public:
      RequestImpl(const int sockid, const sockaddr_in sockaddr);
      size_t read(char *buf, size_t size);
      ~RequestImpl();
    public:
      const int sockid_;
      const sockaddr_in sockaddr_;
      const char *data_;
      size_t offset_;
  };
}

#endif
