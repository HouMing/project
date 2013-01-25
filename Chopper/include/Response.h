#ifndef _SERVERCAT_RESPONSE_H_
#define _SERVERCAT_RESPONSE_H_

#include "common.h"
#include "Server.h"

namespace isvr{

  class Response{
    public:
      virtual size_t write(char *_buf, size_t _size)=0;
      virtual int flush()=0;
      virtual ~Response()=0;
  };

  class ResponseImpl: public Response{
    public:
      ResponseImpl(const int _sockid, const sockaddr_in _sockaddr);
      size_t write(char *_buf, size_t _size);
      ~ResponseImpl();
    protected:
      const int sockid_;
      const sockaddr_in *sockaddr_;
      char *output_;
    private:
      bool isFlushed_;
  };

}
#endif
