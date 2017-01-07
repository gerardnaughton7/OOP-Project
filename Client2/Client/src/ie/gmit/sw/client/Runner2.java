package ie.gmit.sw.client;
/*
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Runner2{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message="";
 	String ipaddress;
 	Scanner stdin;
	Runner2(){}
	void run() throws ClassNotFoundException
	{
		stdin = new Scanner(System.in);
		try{
			int choice = 0;
			Menu myMenu = new Menu();
			//3: Communicating with the server
			do{
				choice = myMenu.menuOptions();//returns choice
				// connect to server
				if(choice == 1)
				{
					connect();
					sendMessage("hi");
				}
				//ask for file list
				else if(choice == 2)
				{
					getList();
				}
				//ask for file
				else if(choice == 3)
				{
					getFile();
				}
				//close connection
				else if(choice == 4)
				{
					closeSocket();
				}
				//2. get Input and Output streams
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
			}while(choice != 4);
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	private void closeSocket() {
		// TODO Auto-generated method stub
		
	}
	private void getFile() {
		// TODO Auto-generated method stub
		
	}
	private void getList() {
		// TODO Auto-generated method stub
		String command = "2";
		sendMessage(command);
	}
	private void connect() {
		// TODO Auto-generated method stub
		try {
			requestSocket = new Socket("localhost", 8081);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Runner2 client = new Runner2();
		try {
			client.run();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/