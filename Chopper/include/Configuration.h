#include "common.h"  
#include "Server.h"

class Configuration {
  public:
    const size_t connector_pool_size_;
    const size_t wrapper_pool_size_;
    Configuration(size_t connector_pool_size, size_t wrapper_pool_size)
      :connector_pool_size_(connector_pool_size),wrapper_pool_size_(wrapper_pool_size){}
}
