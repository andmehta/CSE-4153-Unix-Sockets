import java.net.*; 
import java.io.*; 
  
public class Server 
{ 
    //initialize socket and input stream  
    private DatagramSocket  socket   = null; 
    private Boolean         running  = true;
    private byte[]          buf      = new byte[256];
    
  
    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            socket = new DatagramSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            // reads message from client until TODO implement end of message
            while (running) { 
                try { 
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    
                    InetAddress address = packet.getAddress();
                    //int port = packet.getPort();
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    if(received.contentEquals("end")) {
                    	running = false;
                    	continue;
                    }
                    
                    received = received.toUpperCase();
                    System.out.println(received);
                    socket.send(packet);
                    System.out.println("packet sent");
                    running = false;
  
                } 
                catch(IOException i) { 
                    System.out.println(i); 
                    running = false;
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 