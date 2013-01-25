#include "Request.h"

namespace isvr{

  RequestImpl::RequestImpl(const int sockid, const sockaddr_in sockaddr) {
    sockid_ = sockid; 
    sockaddr_ = sockaddr;
    char buf[BUF_SIZE];
    int q=0;
    int err=0;
    for( ; ;err = read(sockid_,buf,BUF_SIZE)){
      if(err == 0) {
        break;
      }
      if(err == -1) {
        break;
      }
      data_ = realloc(data_,sizeof(const char)*(q*BUF_SIZE+err)); 
      q++;
      memcpy(data_+q*BUF_SIZE,buf,err);
    }
  }

  size_t RequestImpl::read(char *buf, size_t size) {
    int ret = read(sockid_, buf, size); 
    return ret;
  }

  RequestImpl::~RequestImpl() {
    free(data_);
  }

}
