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
import java.io.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.*;

public class Utils {

	public static ArrayList<CSVRecord> getLines(String file, boolean removeHeader) {

		ArrayList<CSVRecord> lines = new ArrayList<CSVRecord>();
		
		try {
			//System.out.println(file);
			File readFile = new File(file);

			if (!readFile.exists())
				throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
			Reader reader = Files.newBufferedReader(Paths.get(file));
			CSVParser csv = CSVParser.parse(reader, CSVFormat.EXCEL.withIgnoreSurroundingSpaces().withTrim());
			

			Iterator<CSVRecord> itr = csv.iterator();
			
			while(itr.hasNext()) {
				
				//String a = itr.next().get(0) + "," + itr.next().get(1) + "," + itr.next().get(2) + "," + itr.next().get(3) + "," + itr.next().get(4) + "," + itr.next().get(5) + "," + itr.next().get(6) + "," + itr.next().get(7) + "," + itr.next().get(8);
				lines.add(itr.next());
				//lines.add(convert);
				//System.out.println(convert);
			}
			
			reader.close();
			
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		Reader reader = Files.newBufferedReader(Paths.get(file));
//		CSVParser csv = CSVParser.parse(reader, CSVFormat.EXCEL.withIgnoreSurroundingSpaces().withTrim());
//
//		Iterator<CSVRecord> itr = csv.iterator();

		if(removeHeader == true) {
			lines.remove(0);
		}
		return lines;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {

		File resultFile = new File(targetFileName);
		resultFile.getParentFile().mkdirs();
		boolean created = false;

		try {
			boolean exist = resultFile.exists();
			if (exist) {
				System.out.println("Error creating File. File with same name already exists.");
				System.exit(0);
			}

			if (!exist) {
				try {
					created = resultFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!created)
					throw new NotEnoughArgumentException("File not created");
			}
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		int i = 0;

		try {
			FileOutputStream fos = new FileOutputStream(targetFileName);
			OutputStreamWriter ops = new OutputStreamWriter(fos, "MS949");
			BufferedWriter bw = new BufferedWriter(ops);
			//BufferedWriter fw = new BufferedWriter(new FileWriter(resultFile, true));
			for (String line : lines) {
				bw.write(line);
				// fw.newLine();
				if (i + 1 == lines.size())
					break;
				i++;
			}

			bw.flush();
			bw.close();

		} catch (Exception e) {
			System.out.println("Error opening the file ");
			System.exit(0);
		}

		System.out.println("Success");
	}

}
