package ie.gmit.sw.client;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
//runner class to start program
public class Runner {
	 //varibles
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static Socket Connection = null;
	
	//main
	public static void main(String[] args){
		//parse conf.xml
		Context ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		try {
			cp.init();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//confirm parsed
		System.out.println("xml file parsed"+ ctx);//tells me file is parsed
		//variables
		final String confInfo = ctx.toString(); //create a string of confInfo to pass to server
		Menu myMenu = new Menu();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		//do while run till 4 is entered
		do{
			choice = myMenu.menuOptions();//returns choice
			// connect to server
			if(choice == 1)
			{
				run();
			}
			//ask for file list
			else if(choice == 2)
			{
				try {
					getList();
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//ask for file
			else if(choice == 3)
			{
				try {
					getFile();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//close connection
			else if(choice == 4)
			{
				try {
					closeSocket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(choice != 4);//end do while
		}

	//close socket
	private static void closeSocket() throws IOException {
		// TODO Auto-generated method stub
		Connection.close();
	}
	//get file
	private static void getFile() throws ClassNotFoundException, IOException {
		//send command 3
		Scanner scan = new Scanner(System.in);
		
		out = new ObjectOutputStream(Connection.getOutputStream());
		int command = 3;
		out.writeObject(command); //Serialise
		out.flush(); 
		
		//response from server with file list
		in = new ObjectInputStream(Connection.getInputStream());
		String response = (String) in.readObject(); //Deserialise
		System.out.println(response);
		
		
		String message = scan.nextLine();
		out.writeObject(message); //Serialise
		out.flush(); 
		
		response = (String) in.readObject(); //Deserialise
		if(response.toString().equals("File Exists"))
		{
			message = "download";
			out.writeObject(message); //Serialise
			out.flush();
			//recieve file
			int bytesRead;

	        DataInputStream clientData = new DataInputStream(Connection.getInputStream());

	        String fileName = message;
	        OutputStream output = new FileOutputStream(("received_from_client_" + fileName));
	        long size = clientData.readLong();
	        byte[] buffer = new byte[1024];
	        while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
	            output.write(buffer, 0, bytesRead);
	            size -= bytesRead;
	        }
	        System.out.println(+buffer.length);
	        output.close();
	        clientData.close();

	        System.out.println("File "+fileName+" received from client.");
			
		}
		else
		{
		System.out.println(response);
		}
	}
	//get list of files
	private static void getList() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//send command to get file list
		out = new ObjectOutputStream(Connection.getOutputStream());
		int command = 2;
		out.writeObject(command); //Serialise
		out.flush(); 
		
		//response from server with file list
		in = new ObjectInputStream(Connection.getInputStream());
		String response = (String) in.readObject(); //Deserialise
		System.out.println(response);
	}
	//establish connection
	public static Socket run(){
		// TODO Auto-generated method stub
		try { //Attempt the following. If something goes wrong, the flow jumps down to catch()
			System.out.println("run");
			Connection = new Socket("localhost", 8081); //Connect to the server
		}catch (Exception e) { //Deal with the error here. A try/catch stops a programme crashing on error  
			System.out.println("Error: " + e.getMessage());
		}//End of try /catch
		return null;
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
}