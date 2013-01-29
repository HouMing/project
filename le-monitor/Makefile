CXX ?= g++

CXXFLAGS = -g -Wall -O0 -DFILE_OFFSET_BITS=64 
LDFLAGS  = -g -static 

SOURCES = config.cpp \
				 	monitor.cpp \
					util.cpp \
					proxyproc.cpp

HEADERS = common.h \
					config.h \
					monitor.h \
					util.h \
					proxyproc.h

OBJECTS = $(SOURCES:.cpp=.o)

TARGET := tpmonitor

all: ${TARGET}

${TARGET}: ${OBJECTS}
	${CXX} $^ $(LDFLAGS) -o $@

${OBJECTS}: ${HEADERS} Makefile

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $< -o $@

clean:
	@rm -rf *.o ${TARGET}
