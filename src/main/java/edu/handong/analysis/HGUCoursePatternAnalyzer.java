package edu.handong.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysise.utils.NotEnoughArgumentException;
import edu.handong.analysise.utils.Utils;

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
		System.out.println(lines.get(0));///////////////delete
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		//Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		//ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		//Utils.writeAFile(linesToBeSaved, resultPath);
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
		//ArrayList<Student> students = new ArrayList<Student>();
		for(int i = 0; i < lines.size(); i++) {
			temp = Integer.parseInt(lines.get(i).split(", ")[0]);
			if(temp > size) {
				size = temp;
			}
		}//
		
		
		ArrayList<Course> courses = new ArrayList<Course>();
		Student[]students = new Student[size];
		
		temp = 0;
//		for(int i = 0; i < lines.size(); i++) {
//			courses.add(new Course(lines.get(i)));
//		}//works
	
		System.out.println(size);
		for(int i = 0; i < lines.size(); i++) {
			if(i == 0) {
				students[i] = new Student(lines.get(i).split(", ")[0]);
				temp = Integer.parseInt(lines.get(i).split(", ")[0]);
			}
			sidexist = Integer.parseInt(lines.get(i).split(", ")[0]);
			courses.add(new Course(lines.get(i)));
			
			if(temp == sidexist) {
				students[temp - 1].addCourse(courses.get(i));
				continue;
			} else {
			students[sidexist - 1] = new Student(lines.get(i).split(", ")[0]);
			students[sidexist - 1].addCourse(courses.get(i));
			temp = sidexist;
			}
		}
		
//		int delete = Integer.parseInt(students[0].getStudentId());
//		
//		while(delete == 1) {
//			try {
//			System.out.println(students[0].getCoursesTaken().get(c).getcourseName());
//			c++;
//			}catch (IndexOutOfBoundsException e){
//				System.out.println("reached the end");
//				break;
//			}
//			
//		}
//		System.out.println("된다 돼 ㅠㅠㅠㅠ");
//		for(c = 0; students[0].getCoursesTaken().get(c) != null; c++)
//		System.out.println(students[0].getCoursesTaken().get(c).getcourseName());
		
		//int studentid = 1;
		
		for(int i = 0; i < lines.size(); i++) {
		//Course crs = new Course(lines.get(i));
		//int j = i + 1;
//		System.out.println(lines.get(i).split(", ")[0]);
		//students.add(new Student(lines.get(i).split(", ")[0]));
		//students.add(i,new Student(lines.get(i).split(", ")[0]));
		
		
//		int k = Integer.parseInt(lines.get(i).split(", ")[0]);
//		int j = 0;
//		while(courses.get(j).getstudentId() != k) {
//			students.get(i).addCourse(courses.get(j));
//			j++;
//		}
		
		
//		for(int x = 0; x < 60; x++) {
//			System.out.println(students.get(0).getArrayListCourse().get(0));
//		}
//		students = new HashMap<String, Student>();
//		String year = lines.get(i).split(", ")[7];
//		String semester = lines.get(i).split(", ")[8];
//		String key = year + "-" + semester;		
//		System.out.println(key);
		}
		
//		for(int i = 0; i < students.size(); i++)
//		{
//			
//			System.out.println(students.get(i).getStudentId());
//			
//			
//		}
		
		
//		System.out.println((students.get(0).getArrayListCourse()).size());
		
		return null; // do not forget to return a proper variable.
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
		
		return null; // do not forget to return a proper variable.
	}
}
