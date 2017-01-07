package ie.gmit.sw.client;

import java.util.Scanner;

public class Menu {
	private int option;
	private boolean connected = false;
	private boolean valid = false;
	private Scanner scan = new Scanner(System.in);
	
	public int menuOptions(){
		
		do{
		getMenu();
		option = scan.nextInt();
		if(option == 1)
		{
			connected = true;
		}
		if(option > 0 && option < 5 && connected == true){
			valid = true;	
		}
		//System.out.println(valid);
		}while(valid == false);
		valid = false;
		//System.out.println(valid);
		return option;
	}
	
	private void getMenu(){
		System.out.println("=========================================");
		System.out.println("                   Menu                  ");
		System.out.println("=========================================");
		System.out.println("           1. Connect to Server");
		System.out.println("           2. Print file List");
		System.out.println("           3. Download File");
		System.out.println("           4. Quit");
		System.out.println("\n");
		System.out.println("           Type-Option [1-4]");
		System.out.println("\n");
		System.out.println("Please choose from one of the Options?(You must connect to Server first in order to avail of other options)");
		
	}
}
