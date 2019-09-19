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
//import java.util.Scanner;
  
public class client 
{ 
    // initialize socket and input output streams 
    private DatagramSocket socket    = null;  
    private String message           = "";
    private Boolean running          = true;
    private byte[] buf;
  
    // constructor to put ip address and port 
    public client(String address, int port, String filename) 
    { 
        // establish a connection 
        try {
				socket = new DatagramSocket();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
     // TODO add handshake here
        System.out.println("Connected"); 
        testFilename(filename);
    	
    	List<String> chunks = splitEqually(message, 4);
    	
    	buf = chunks.get(5).getBytes(); // TODO turn this into a function? index 5 = end
    	
    	DatagramPacket packet = null;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
    	try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	packet = new DatagramPacket(buf, buf.length);
    	try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String received = new String(packet.getData(), 0, packet.getLength()); //TODO turn this into a function
    	System.out.println("Ack received " + received);
    	
    	System.out.println("close client socket");
    	socket.close();
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
    	System.out.print("messageSent = " + messageSent + "ACK = " + ACK);
    	if(messageSent.toUpperCase() != ACK) {
    		System.err.println("bad ACK, resend packet");
    		return false;
    	}
    	else {
    		System.out.println(ACK);
    		ACK.notify();
    		
    		return true;
    	}
    }
  
    public static void main(String args[]) { 
		/*
		 * Scanner input = new Scanner(System.in);
		 * System.out.print("What is the filename of the textfile? "); //TODO remove and
		 * replace with a command line call String filename = input.next(); String
		 * message = "";
		 * 
		 * //Test if that file exists try { message = readFileAsString(filename); }
		 * catch (Exception e) {
		 * System.err.println("That filename does not exist. exiting"); System.exit(1);
		 * }
		 * 
		 * List<String> chunks = splitEqually(message, 4); for (int i = 0; i <
		 * chunks.size(); i++) { System.out.print(chunks.get(i));
		 * System.out.println(" length = " + chunks.get(i).length()); }
		 */
    	
    	
        client client = new client("127.0.0.1", 5000, "test.txt"); 
        
		/* TODO change to this format??
		 * client.handshake(); 
		 * client.testFilename("test.txt");
		 * client.splitEqually(4)
		 */        
    } 
} 