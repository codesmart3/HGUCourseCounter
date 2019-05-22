package edu.handong.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysise.utils.NotEnoughArgumentException;
import edu.handong.analysise.utils.Utils;
import java.util.*;


public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args)  {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		students = loadStudentCourseRecords(lines);
		
		//To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		// TODO: Implement this method
		int size = 1, temp = 1, sidexist = 1, c = 0;
		String key;
		//ArrayList<Student> students = new ArrayList<Student>();
		for(int i = 0; i < lines.size(); i++) {
			temp = Integer.parseInt(lines.get(i).split(", ")[0]);
			if(temp > size) {
				size = temp;
			}
		}
		
		students = new HashMap<String, Student>();
		
		
		ArrayList<Course> courses = new ArrayList<Course>();
		Student[]studentsarr = new Student[size];
		
		temp = 0;
	
		for(int i = 0; i < lines.size(); i++) {
			if(i == 0) {
				studentsarr[i] = new Student(lines.get(i).split(", ")[0]);
				temp = Integer.parseInt(lines.get(i).split(", ")[0]);
			}
			sidexist = Integer.parseInt(lines.get(i).split(", ")[0]);
			courses.add(new Course(lines.get(i)));
			
			if(temp == sidexist) {
				studentsarr[temp - 1].addCourse(courses.get(i));
				continue;
			} else {
			studentsarr[sidexist - 1] = new Student(lines.get(i).split(", ")[0]);
			studentsarr[sidexist - 1].addCourse(courses.get(i));
			temp = sidexist;
			}
		}
		
		for(c = 0; c < studentsarr.length; c++) {
			key = studentsarr[c].getStudentId();
			students.put(key, studentsarr[c]);
		}
		
		
//	int delete = Integer.parseInt(studentsarr[0].getStudentId()); c = 0;
//		
//		while(delete == 1) {
//			try {
//			System.out.println(studentsarr[0].getCoursesTaken().get(c).getcourseName());
//			c++;
//			}catch (IndexOutOfBoundsException e){
//				System.out.println("reached the end");
//				break;
//			}
//			
//		}
//		System.out.println("된다 돼 ㅠㅠㅠㅠ");

		
//		for(int x = 0; x < 60; x++) {
//			System.out.println(students.get(0).getArrayListCourse().get(0));
//		}
//		students = new HashMap<String, Student>();
//		String year = lines.get(i).split(", ")[7];
//		String semester = lines.get(i).split(", ")[8];
//		String key = year + "-" + semester;		
//		System.out.println(key);
		
			
//		System.out.println((students.get(0).getArrayListCourse()).size());
		
		return students; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		HashMap<String, Integer> temporary = new HashMap<String, Integer>();
//		ArrayList<Entry<String, Integer>> arrlist;
		ArrayList<String> arr = new ArrayList<String>();
		String header = "StudentID" + "," + "TotalNumberOfSemestersRegistered" + "," + "Semester" + "," + "NumCoursesTakenInTheSemester" + "\n";
		header.replace("[","");
		arr.add(header);
		
		
		Map<String, Integer> tmp = new TreeMap<String, Integer>(temporary);

//			
//			while(itr1.hasNext()) {
//				String key1 = itr1.next();
//				int value = sorted.get(key1);
//				String insert = key + "-" + value;
//				arr.add(insert);
//				
//			}
			
//			Set<Entry<String, Integer>> tempSet = temporary.entrySet();
//			arrlist = new ArrayList<Entry<String, Integer>>(tempSet);
//		}
		
		
		
		
		
		//System.out.println(sortedStudents.get("0002").getCoursesTaken().get(3).getcourseName());
//		
//		for(int z = 0; z < arr.size(); z++) {
//			System.out.println(arr.get(z));
//		}
		
		
//		System.out.println(tmp);
		
		int semsTakenInNthSem;
		int finalCoursesTaken = 0;
		Iterator<String> itr = sortedStudents.keySet().iterator();
		while(itr.hasNext()) { 
			String key = itr.next();
			
			finalCoursesTaken = sortedStudents.get(key).totalSemesterTaken();
			
			for(int i = 0; i < finalCoursesTaken + 1; i++) {
				semsTakenInNthSem = sortedStudents.get(key).getNumCourseInNthSementer(i);
				int sid1 = Integer.parseInt(key);
				
				String line = sid1 + "," + finalCoursesTaken + "," + i + "," + semsTakenInNthSem + "\n";
				if(i == 0) {
					continue;
				}
				arr.add(line);
			}
//			Map<String, Integer> sorted = new TreeMap<String, Integer>(temporary);
//			Iterator<String> itr1 = sorted.keySet().iterator();
//			System.out.println(finalCoursesTaken);
			
		}
		
		
		
//		
//		for(String s : arr)
//			System.out.println(s);
		
		
		
		
		
		return arr; // do not forget to return a proper variable.
	}
}
