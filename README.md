# CSE-4153-Unix-Sockets
Project written in Java to demonstrate how to write a Unix socket. 

## Project description 
The  goal  of  this  assignment  is  to  gain  experience  with  both  TCP  and  UDP  socket  programming  in  a client-server environment by implementing a file transfer protocol. You will use Java or  C++  (your  choice)  to  design  and  implement  a  client  program  (client)  and  server  program (server) to communicate between themselves. 

##goals left in client
implement call (should read as java client <host1/server address> <n_port> <filename>)
	Look at [this](https://canvas.msstate.edu/courses/22469/assignments/126620) and look at makefiles

implement handshake (USE TCP)
implement data chunk sender (USE UDP)
print ACK
implement ACK response (send next payload)


##goals left in server
implement call (should read as java server <n_port>)
implement random port assignment <r_port> (1024 and 65535 inclusive)
implement ACK response (capitalize payload and return, USE UDP) 
save each payload into a textfile "dataReceived.txt"

