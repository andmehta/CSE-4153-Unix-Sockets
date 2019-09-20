# CSE-4153-Unix-Sockets
Project written in Java to demonstrate how to write a Unix socket. 

## Project description 
The  goal  of  this  assignment  is  to  gain  experience  with  both  TCP  and  UDP  socket  programming  in  a client-server environment by implementing a file transfer protocol. You will use Java ~or C++~  (your  choice)  to  design  and  implement  a  client  program  (client)  and  server  program (server) to communicate between themselves. 

## goals left in client
- [x] implement call (should read as `java client <host1/server address> <n_port> <filename>`) 
- [ ] **TODO figure out how [makefiles](http://www.henrywowen.com/post.php?p_id=7 "makefile intro") work**
- [x] implement handshake (USE TCP)
- [x] implement data chunk sender (USE UDP)
- [x] print each ACK 
- [x] implement ACK response (send next payload)
    * ~make sure ACK is proper~ 
    * ~increment `index`~   
- [x] determine loop exit condition


## goals left in server
- [x] implement call (should read as `java server <n_port>`)
- [x] implement [random](https://www.geeksforgeeks.org/generating-random-numbers-in-java/ "random number generator") port assignment <r_port> (1024 and 65535 inclusive)
    * ~print what random port selected~
    * ~send that port to client via TCP (ACK of initial handshake)~
- [x] implement ACK response (capitalize payload and return, USE UDP)
    * ~send payload back and forth~
    * ~implement function that turns payload into all caps, names it `ACK`~
    
- [x] save each payload into a textfile "dataReceived.txt"
- [x] implement check for end of file
   * ~send final ACK with info stating end of the file~

