#ifndef _SERVERCAT_WRAPPER_H_
#define _SERVERCAT_WRAPPER_H_ 

namespace isvr {

  //Runnable 
  class Wrapper{
    public:
      int start()=0;
      int setWrapper(Request *req, Response *resp)=0;
      bool isBusy()=0;
  }; 

  class WrapperImpl: public Wrapper {
    public:
      int start();
      int run();
      int setWrapper(Request *req, Response *resp);
      bool isStart();
      static int whiletrue();
      WrapperImpl(Request *req,Response *resp);
      ~WrapperImpl();
    protected:
      pthread_t pid_;
      bool start_;
      Request* req_;
      Response* resp_;
  };

}

#endif 
