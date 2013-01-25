#include "../include/common.h"
#include "../include/Processor.h"

namespace isvr {

  ProcessorImpl::ProcessorImpl() {
    sockid_ = 0;
    client_ = NULL;
    used_ = false;
  }

  ProcessorImpl::setSocket(const int sockid, const sockaddr *sock) {
    sockid_ = sockid;
    client_ = (const sockaddr_in *)sock;
    used_ = true;
  }

  //without protocol
  Request* ProcessorImpl::produceRequest() {
    Request *ret = new RequestImpl( sockid_, sock);
    if(ret == NULL) {
      return NULL:
    }
    return ret;
  }
  
  Response* ProcessorImpl::produceResponse() {
    Response *ret = new ResponseImpl( sockid_, sock);
    if(ret == NULL) {
      return NULL;
    }
    return ret;
  }

  int ProcessorImpl::clean(){
    int ret = close(socket_);
    socket_ = 0;
    client_ = NULL;
    used_ = false;
    return ret;
  }

}
