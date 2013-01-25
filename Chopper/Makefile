CC=g++
CFLAGS=-Wall -g -lpthread

OBJ=obj
SRC=src
INCLUDE=include

vpath %.cpp $(SRC)
vpath %.h $(INCLUDE)
vpath %.o $(OBJ)

all : main
.PHONY : all

main:main.o
	$(CC) $< -o main $(CFLAGS)

%.o:%.cpp
	$(CC) $^ -o $(OBJ)/$@ $(CFLAGS)

