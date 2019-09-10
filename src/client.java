import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) {
		Socket mySocket = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		
		try {
			//Open a socket at port 8000
			mySocket = new Socket("am3258@MehtaXPS-13", 8000);
			//outputStream = new DataOutputStream(mySocket.getOutputStream());
			//inputStream = new DataInputStream(mySocket.getInputStream());
		}
		catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		}
		catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection: hostname");
		}
		if(mySocket != null && outputStream != null && inputStream != null) {
			//try {
					System.err.println("Successful declarations");
			//}
		}
	}
}