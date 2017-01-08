package ie.gmit.sw.client;

//imports
import java.io.BufferedOutputStream;
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
	private static Context ctx;
	
	//main
	public static void main(String[] args){
		//parse conf.xml
		ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		try {
			cp.init();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("Problem parsing conf.xml" + e.getMessage());
		}
		//confirm parsed
		System.out.println("xml file parsed");//tells me file is parsed
		//variables
		
		//get instance of menu
		Menu myMenu = new Menu();
		int choice = 0;
	
		//do while run till 4 is entered
		do
		{
			choice = myMenu.menuOptions();//returns choice
			// connect to server
			if(choice == 1)
			{
				run();
				try {
					out = new ObjectOutputStream(Connection.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//ask for file list
			else if(choice == 2)
			{
				getList();
			}
			//ask for file
			else if(choice == 3)
			{
				try {
					getFile();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Error downloading file"+ e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error downloading file"+ e.getMessage());
				}
			}
			//close connection
			else if(choice == 4)
			{
				try {
					closeSocket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error closing socket" + e.getMessage());
				}
			}
			
		}while(choice != 4);//end while
	}

	//close socket
	private static void closeSocket() throws IOException {
		// TODO Auto-generated method stub
		Connection.close();
	}
	//get file
	private static void getFile() throws IOException, ClassNotFoundException{
		//instance if scanner
		Scanner scan = new Scanner(System.in);
		String command;
		String response;
		
		//send over 3 to choose option on server side
		command = "3";
		sendMessage(command);
		
		//response from server asking for file name
		in = new ObjectInputStream(Connection.getInputStream());
		response = (String) in.readObject(); //Deserialise
		System.out.println(response);
		
		//ask user for file they want to download and send name to server
		String fileName = scan.nextLine();
		sendMessage(fileName);
		//returns weather file is vaild
		response = (String) in.readObject(); //Deserialise
		
		//if equals 1 except file
		if(response == "1")
		{
			//download file
			//declare byte array
			byte[] byteArray = new byte[1024];
			FileOutputStream fos = new FileOutputStream(ctx.getDir()+ fileName);//set fileoutpotstream location
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int bytesRead = in.read(byteArray, 0, byteArray.length);
			bos.write(byteArray, 0, bytesRead);
			
			bos.close();//close bufferedoutputstream
		}
		else//output that file does not exist
		{
			System.out.println("File does not exist!");
		}
		
	}
	//get list of files
	private static void getList(){
		// TODO Auto-generated method stub
		//send command to get file list
		String command = "2";
		String response;
		sendMessage(command);
		
		//response from server with file list
		try {
			in = new ObjectInputStream(Connection.getInputStream());
			response = (String) in.readObject(); //Deserialise
			System.out.println(response);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//establish connection
	public static void run(){
		// TODO Auto-generated method stub
		try { //Attempt the following. If something goes wrong, the flow jumps down to catch()
			System.out.println("run");
			Connection = new Socket("localhost", 7777); //Connect to the server
		}catch (Exception e) { //Deal with the error here. A try/catch stops a programme crashing on error  
			System.out.println("Error: " + e.getMessage());
		}//End of try /catch
	}
	//send message
	static void sendMessage(String msg)
	{
		try{
			//send message to serve
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			System.out.println("Error sending message" + ioException.getMessage());	
		}
	}
}