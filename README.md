# CSE-4153-Unix-Sockets
Project written in Java to demonstrate how to write a Unix socket. 

## Project description 
The  goal  of  this  assignment  is  to  gain  experience  with  both  TCP  and  UDP  socket  programming  in  a client-server environment by implementing a file transfer protocol. You will use Java or  C++  (your  choice)  to  design  and  implement  a  client  program  (client)  and  server  program (server) to communicate between themselves. 

## goals left in client
1. implement call (should read as `java client <host1/server address> <n_port> <filename>`)
     * Look at [this](https://canvas.msstate.edu/courses/22469/assignments/126620 "canvas link") and look at makefiles
2. implement handshake (USE TCP)
3. ~implement data chunk sender (USE UDP)~
4. ~print ACK~
5. implement ACK response (send next payload)
    * make sure ACK is proper **TODO ask Maxwell why my function doesn't work**
    * ~increment `index`~


## goals left in server
1. implement call (should read as `java server <n_port>`)
2. implement random port assignment <r_port> (1024 and 65535 inclusive)
3. ~implement ACK response (capitalize payload and return, USE UDP)~ 
    * ~send payload back and forth~
    * ~implement function that turns payload into all caps, names it `ACK`~
    
4. ~save each payload into a textfile "dataReceived.txt"~
    * ~implement check for end of file~

