package ie.gmit.sw.server;

import java.io.File;
import java.util.ArrayList;

public class FileList {
	//method result of files
	public static ArrayList resultOfFiles(){
	//create arraylist of strings
	ArrayList<String> result = new ArrayList<String>();
	//get handle on filder where files are held
	File folder = new File("C:/Users/User/Desktop/Client2/Project/Files/");
	//list of files in folder
	File[] listOfFiles = folder.listFiles();
	//loop through listoffiles to get list of file names
	for (int i = 0; i < listOfFiles.length; i++) {
	    if (listOfFiles[i].isFile()) {
	       String files = listOfFiles[i].getName();
	       //System.out.println(files);
	       result.add(files);
	    }
	}
	return result;//return list of files
	}
	
	/*public static void main(String[] args)
	{
		ArrayList r  = resultOfFiles();
		
		System.out.println(r);
	}*/
	
}

