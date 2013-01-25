#include "../include/common.h"
#include "../include/Connector.h"

namespace isvr{

  //no implements;
  Connector::ConnectorImpl() {
    sockid_ = socked(AF_INET,SOCK_STREAM,0);
    memset(&server_addr_,0,sizeof(server_addr_));
    sockaddr_.sin_family = AF_INET;
    //TODO fix the hardcode
    sockaddr_.s_addr = inet_addr("127.0.0.1");
    sockaddr_.sin_addr_.sin_port = htons( 60000);    
  }

  Connector::~ConnectorImpl() {
  }

  int Connector::initailize() {
  }

  static void* Connector::run(void *){
      err = bind(sockid_,
          (const struct sockaddr*)&server_addr_,
          sizeof(server_addr_));
      if(err != 0) {
        abort();
      }
  }
  int Connector::start() {
    pthread_create(&tid_, NULL, Connector::run, NULL);
  }

  int Connector::isStart() {
    return tid_ != 0;
  }

  int Connector::stop() {
    if(pid_ == 0){
      return -1;
    }
    else {
      err_ = pthread_kill(pid_,0);
      if(err_ == EINVAL) {
        return EINVAL;
      } else if(err_ == ESRCH){
        return ESRCH;
      }
      tid_ = 0;
      return err_; 
    }
  }

  Request* Connector::createRequest() {
  }

  Response* Connector::createResponse(){
  }

  //the elements show implements the Processor interface
  int Connector::setProcessorsCount(size_t count) {
  }

}

