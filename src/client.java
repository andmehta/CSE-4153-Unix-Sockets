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
import java.util.Scanner;
  
public class client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
    private String message           = "";
    private Boolean END              = false;
  
    // constructor to put ip address and port 
    public client(String address, int port, String filename) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // receive ACK from server with input
            input  = new DataInputStream(socket.getInputStream()); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.err.println(u); 
        } 
        catch(IOException i) 
        { 
            System.err.println(i); 
        } 
  
      //Test if that file exists
    	try {
    		message = readFileAsString(filename);
    	}
    	catch (Exception e) {
    		System.err.println("That filename does not exist. exiting");
    		System.exit(1);
    	}
    	
    	List<String> chunks = splitEqually(message, 4);
    	
    	int index = 0;
    	String ACK = "";
  
        // keep reading if ACK comes in
        while (!END) 
        { 
            try
            { 
                out.writeUTF(chunks.get(index)); 
            } 
            catch(IOException i) 
            { 
                 System.err.println(i); 
            } 
            
            //next read ACK
            try {
            	ACK = input.readUTF();
            }
            catch(IOException i) {
            	System.err.println(i);
            }
            
            if(ExpectedACK(chunks.get(index), ACK)) {
            	index++;
            }
            else END = !END;
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
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
    } 
} 