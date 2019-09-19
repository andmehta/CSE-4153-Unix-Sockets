/*******************
 * Andrew Mehta    *
 * am3258          *
 *                 *
 *******************/

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
                    //get the payload from the client
                    socket.receive(packet);
                    
                    //change the new packet into a string that we can use
                    String received = packetToString(packet);
                    
                    //Check if the payload is the end of the file
                    if(received.contentEquals("\nend")) {
                    	running = false;
                    }

                    writeToTextfile(received);
                    
                    //Prepare an ACK of the received payload
                    DatagramPacket ACK = packetToACK(packet);
                    
                    //send the ACK to the client
                    socket.send(ACK); 
                } 
                catch(IOException i) { 
                    System.out.println(i); 
                    running = false;
                } 
            } 
            System.out.println("Closing server socket"); 
  
            // close socket
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static String packetToString(DatagramPacket Datagrampacket) {
    	String received = new String(Datagrampacket.getData(), 0, Datagrampacket.getLength());
    	return received;
    }
    
    public static DatagramPacket packetToACK(DatagramPacket packet) {
    	String CAPS = packetToString(packet);
    	CAPS = CAPS.toUpperCase();
    	
    	DatagramPacket ACK = null;
    	byte[] buf = CAPS.getBytes();
		try {
			ACK = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), packet.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		return ACK;
    }
    
    public static void writeToTextfile(String string) throws IOException {
    	File dataReceived = new File("dataReceived.txt");
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter("dataReceived.txt", dataReceived.exists()));
    	
    	if(!dataReceived.exists()) {
    		dataReceived = File.createTempFile("dataReceived", ".txt");
    		writer.write(string);
    	}
    	else {
    		writer.newLine();
    		writer.append(string);
    	}
    	
    	writer.close();
    }
    
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 