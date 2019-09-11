import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) {
		Socket mySocket = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		
		try {
			//Open a socket at port 8000
			String serverIP = String.valueOf(InetAddress.getLocalHost().getHostAddress());
			mySocket = new Socket(serverIP, 8000); //TODO error here, throws IOException 
			//outputStream = new DataOutputStream(mySocket.getOutputStream());
			//inputStream = new DataInputStream(mySocket.getInputStream());
		}
		catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost");
		}
		catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection: localhost");
		}
		if (mySocket != null /* && outputStream != null && inputStream != null */) {
			try {
					System.out.println("Successful declarations");
					mySocket.close(); 
			}
			catch (IOException io) {
				System.err.println("Couldn't close I/O for the connection: localhost");
			}
		}
	}
}