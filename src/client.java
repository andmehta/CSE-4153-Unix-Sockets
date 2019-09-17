import java.nio.file.*;
import java.net.*;
import java.io.*; 
import java.util.Scanner;
  
public class client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = input.readLine(); 
                out.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
                 System.out.println(i); 
            } 
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
  
    public static void main(String args[]) { 
    	Scanner input = new Scanner(System.in);
    	System.out.print("What is the filename of the textfile? ");
    	String filename = input.next();
    	
    	//Test if that file exists
    	try {
    		String output = readFileAsString(filename);
    		
    		System.out.println(output); //TODO use something 
    	}
    	catch (Exception e) {
    		System.err.println("That filename does not exist. exiting");
    		System.exit(1);
    	}
    	
    	
        //client client = new client("127.0.0.1", 5000); 
    } 
} 