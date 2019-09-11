import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) {
		Socket mySocket = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		
		try {
			//Open a socket at port 8000
			mySocket = new Socket("localhost", 8000); //TODO error here, throws to unknownHostException
			outputStream = new DataOutputStream(mySocket.getOutputStream());
			inputStream = new DataInputStream(mySocket.getInputStream());
		}
		catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost");
		}
		catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection: localhost");
		}
		if(mySocket != null && outputStream != null && inputStream != null) {
			//try {
					System.err.println("Successful declarations");
			//}
		}
	}
}