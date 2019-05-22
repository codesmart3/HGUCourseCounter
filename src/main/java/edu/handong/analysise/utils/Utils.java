package edu.handong.analysise.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Utils {
	
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<>();
//		Path dataPath = Paths.get(file);
		try {
			//boolean exist = dir.exists();
			boolean pathExist = new File(file).exists();
			if(!pathExist)
				throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));	
			
			String line;
			line = br.readLine();
			
			while(line != null) {
				lines.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(removeHeader == true) {
			lines.remove(0);
		}
		return lines;
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		
		//Path givenPath = Paths.get(targetFileName);
		
		//File dir = new File(targetFileName);
		//dir.mkdirs();
		//String fileName = "hw5result11";
		PrintWriter outputStream = null;
//
		
		File resultFile = new File(targetFileName);
//		
//		try {
//			resultFile.createNewFile();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//			//boolean exist = dir.exists();
		
		System.out.println("1");

		boolean created = false;
		
//		File newFile = new File(dir + File.separator + fileName);
		
				
		try {
		
		boolean exist = resultFile.exists();
		if(exist) {
			System.out.println("Error creating File. File with same name already exists.");
			System.exit(0);
		}
		
		if(!exist) {
		try {
			created = resultFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!created) 
			throw new NotEnoughArgumentException("File not created");
		}
		} catch(NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		

		
		System.out.println("@");
		int i = 0;
		
			try {
			BufferedWriter fw = new BufferedWriter(new FileWriter (resultFile, true));
			for(String line : lines) {
				fw.write(line);
				//fw.newLine();
				if(i + 1 == lines.size())
						break;
				i++;
			}
			
			
			fw.flush();
			fw.close();
			
		} catch (Exception e) {
			System.out.println("Error opening the file ");
			System.exit(0);
		}
		
		System.out.printf("These lines are printed to: %s", lines);
	}
	
	
}
