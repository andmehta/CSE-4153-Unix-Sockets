/*******************
 * Andrew Mehta    *
 * am3258          *
 *                 *
 *******************/

import java.net.*; 
import java.util.Random;
import java.io.*; 
  
public class server 
{ 
    //initialize socket and input stream  
	//For TCP handshake
	private Socket           tcp_socket = null;
	private ServerSocket     tcp_server = null;
	private DataInputStream  input      = null;
	private DataOutputStream output     = null;
	
	//for UDP data transfer
	private int              UDP_port;
    private DatagramSocket   socket     = null;
    private byte[]           buf        = new byte[256];
    
    //for Running the server
    private Boolean          running    = true;
    
  
    // constructor with port 
    public server(int port) 
    {  
    	// starts server and waits for a connection 
    	try {
    		tcp_server = new ServerSocket(port);
    		//System.out.println("waiting on client");
    		tcp_socket = tcp_server.accept();
    		output = new DataOutputStream(tcp_socket.getOutputStream());
    		input = new DataInputStream(tcp_socket.getInputStream());
    		String line = "";
    		
    		line = input.readUTF();
    		
    		UDP_port = randomPortGenerator();
    		System.out.println("Handshake detected. Selected the random port " + UDP_port);
    		output.writeInt(UDP_port);
    		
    		tcp_server.close();
    		output.close();
    		input.close();
    	}
    	catch (IOException io){
    		System.err.println(io);
    	}
    	
    	//Now do UDP to send file info
        try
        { 
            socket = new DatagramSocket(UDP_port); 
            //System.out.println("DatagramSocket started"); 
  
            //System.out.println("Waiting for a client ... on port " + UDP_port); 
  
            // reads message from client until implement end of message
            while (running) { 
                try { 
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    //get the payload from the client
                    socket.receive(packet);
                    
                    
                    //change the new packet into a string that we can use
                    String received = packetToString(packet);
                    
                    //System.out.println(received.contentEquals("\nend"));
                    //Check if the payload is the end of the file
                    if(received.contentEquals("\nend")) { // TODO need a better system here
                    	running = false;
                    } else {
                    	writeToTextfile(received);
                    }
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
            //System.out.println("Closing server socket"); 
  
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
    
    public static int randomPortGenerator() {
    	Random rand = new Random();
		return rand.nextInt(64551) + 1024;
    }
    
    public static void main(String args[]) 
    { 
        server Server = new server(8000); 
    } 
} 