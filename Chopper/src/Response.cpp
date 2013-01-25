#include "../include/common.h"
#include "../include/Response.h"

namespace isvr {

  ResponseImpl::ResponseImpl(const int _sockid, const sockaddr_in _sockaddr){
    sockid_ = _sockid;
    sockaddr_ = _sockaddr;
    output_ = new char[BUF_SIZE];
    isFlushed_ = false; 
  } 

  size_t ResponseImpl::write(const char *_buf,size_t _size) {
    char * ret = memcpy(output_, _buf, _size); 
    if(ret != NULL){
    return _size;
    }else{
      isFlushed = false;
      return 0;
    }
  }

  int ResponseImpl::flush(){
    int ret = write(sockid_, output, BUF_SIZE);
    if(ret < 0) {
      return -1;
    }
    isFlushed_ = true;
    return ret;
  }

  ResponseImpl::~ResponseImpl(){
   if(isFlushed_ == false) {
    flush();
   } 
    sockid_ = 0;
    sockaddr_ = NULL;
    delete [] output_;
  }

}

