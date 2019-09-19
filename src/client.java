/*******************
 * Andrew Mehta    *
 * am3258          *
 *                 *
 *******************/

import java.nio.file.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
  
public class client 
{ 
    // initialize socket and input output streams 
	private Socket tcp_socket        = null;
	private DataInputStream input    = null;
	private DataOutputStream output  = null;
	
	//UDP stuff
    private DatagramSocket socket    = null;  
    private String message           = "";
    private byte[] buf;
    
    //Maintenance stuff
    private Boolean running          = true;
  
    // constructor to put ip address and port 
    public client(String address, int port, String filename) 
    { 
    	//TCP handshake
    	try {
    		tcp_socket = new Socket(address, 8000);
    		input = new DataInputStream(tcp_socket.getInputStream());
    		output = new DataOutputStream(tcp_socket.getOutputStream());
    		
    		output.writeUTF("117");
    		
    		int UDP_port = input.readInt();
    		System.out.println("client received port " + UDP_port);
    		
    		tcp_socket.close();
    		output.close();
    		input.close();
    		
    	}
    	catch (IOException io) {
    		System.err.println(io);
    	}
    	/*try 
		 *
		 * // establish a UDP connection try { socket = new DatagramSocket(); } catch
		 * (SocketException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * } // TODO add handshake here System.out.println("Connected");
		 * testFilename(filename);
		 * 
		 * List<String> chunks = splitEqually(message, 4); int index = 0; while(running)
		 * { buf = chunks.get(index).getBytes(); // TODO turn this into a function?
		 * 
		 * //initialize packet DatagramPacket packet = null; try { packet = new
		 * DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port); } catch
		 * (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); System.exit(1); } try { System.out.println("packet = " +
		 * chunks.get(index)); socket.send(packet); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } packet = new
		 * DatagramPacket(buf, buf.length); try { socket.receive(packet); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * if(ExpectedACK(chunks.get(index), packetToString(packet))) { index++; }
		 * 
		 * if(index == chunks.size()) { running = false; } }
		 * 
		 * System.out.println("close client socket"); socket.close();
		 */
    } 
    
    public void testFilename(String filename) {
        //Test if that file exists
      	try {
      		message = readFileAsString(filename);
      	}
      	catch (Exception e) {
      		System.err.println("That filename does not exist. exiting");
      		System.exit(1);
      	}
      }
    
    public static String readFileAsString(String filename) throws Exception {
		String data = "";
		data = new String (Files.readAllBytes(Paths.get(filename)));
		
		data = data + "\nend"; //signal end of file
		return data;
	}
    
    public static List<String> splitEqually(String text, int size) {
        List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }
    
    public static Boolean ExpectedACK(String messageSent, String ACK) { 
    	if(messageSent.toUpperCase().equals(ACK)) { 
    		System.out.println(ACK);
    		return true;
    	}
    	else {
    		System.err.println("bad ACK, resend packet");
    		return false;
    	}
    }
  
    public static String packetToString(DatagramPacket Datagrampacket) {
    	String received = new String(Datagrampacket.getData(), 0, Datagrampacket.getLength());
    	return received;
    }
    
    public static void main(String args[]) { 
    	
        client Client = new client("127.0.0.1", 8000, "test.txt"); 
        
		/* TODO change to this format??
		 * client.handshake(); 
		 * client.testFilename("test.txt");
		 * client.splitEqually(4)
		 */        
    } 
} 