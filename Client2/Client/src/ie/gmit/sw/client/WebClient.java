package ie.gmit.sw.client;
/*package ie.gmit.sw;

import java.io.*; //We need the Java IO library to read from the socket's input stream and write to its output stream
import java.net.*; //Sockets are packaged in the java.net library
import java.util.Scanner;

public class WebClient{//The class WebClient must be declared in a file called WebClient.java
	
	//Main method to get the ball rolling
	public static void main(String[] args) throws Throwable{
		//parse conf.xml
		Context ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		cp.init();
		System.out.println("xml file parsed"+ ctx);//tells me file is parsed
		final String confInfo = ctx.toString(); //create a string of confInfo to pass to server
			
		/* Create a new Thread using the constructor Thread(Runnable r, String threadName).
		 * The Runnable is created on-the-fly as an anonymous inner class. Normally it should
		 * be declared either in its own class or as an inner class (for encapsulation).
		 */
		/*new Thread(new Runnable(){
			/* Every thread / runnable needs a run() method. Any objects instantiated inside run() and not passed
			 * as arguments to other objects are thread-safe, i.e. there is no need to worry about synchronization.
			 */
/*			public void run() { 
				try { //Attempt the following. If something goes wrong, the flow jumps down to catch()
					int choice;
					int command = 0;
					String file = "";
					Menu myMenu = new Menu();
					Scanner scan = new Scanner(System.in);
					do{
						choice = myMenu.menuOptions();
						System.out.println(choice);
						Socket s = new Socket("localhost", 8080); //Connect to the server
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						if(choice == 1)
						{
							System.out.println("Already connected to server");
						}
						else if(choice == 2)
						{
							System.out.println("send query to server for file list");
							command = 2;
							out.writeObject(command); //Serialise
							out.flush(); //Ensure all data sent by flushing buffers
						}
						else if(choice == 3)
						{
							System.out.println("send query to server to download file and must send conf-file");
							command = 3;
							out.writeObject(command); //Serialise
							out.flush(); //Ensure all data sent by flushing buffers
						}
						
						Thread.yield(); //Pause the current thread for a short time (not used much)
						if(choice != 1)
						{
							//Deserialise / unmarshal response from server 
							ObjectInputStream in = new ObjectInputStream(s.getInputStream());
							String response = (String) in.readObject(); //Deserialise
							
							if(response.toString().equalsIgnoreCase("3"))//if equal to 3 send the confInfo to the server and then recieve file
							{
								out.writeObject(confInfo); //Serialise
								out.flush();
								response = (String) in.readObject();
								System.out.println(response);
								file = scan.nextLine();
								out.writeObject(file); //Serialise
								out.flush();
								response = (String) in.readObject();
								System.out.println(response);
								
							}
							else//else print response will be either list of files or error sayint there is no such file to download
							{
								//Get the name of the thread (worker) doing this runnable (job)
								String threadName = Thread.currentThread().getName(); 
			 					System.out.println(response + "-->" + threadName);
							}
						}
						//if choice equal to 4 close socket
						if(choice == 4)
						{
							s.close(); //Tidy up
						}
						
					}while(choice != 4);//do while end
					
				} catch (Exception e) { //Deal with the error here. A try/catch stops a programme crashing on error  
					System.out.println("Error: " + e.getMessage());
				}//End of try /catch
			}//End of run(). The thread will now die...sob..sob...;)
			
		}, "Client-").start(); //Start the thread
		
		System.out.println("Main method will return now....");
		
	}//End of main method

}
*/