package edu.handong.analysis.datamodel;
import java.util.*;

public class Student {
	private String studentId;
	private ArrayList<Course>coursesTaken;
	private HashMap<String, Integer> semestersByYearAndSemester;
			//key: Year-Semester
			//e.g., 2003-1,
	
	
	public Student(String studentId) {
		this.studentId = studentId;
		coursesTaken = new ArrayList<Course>();
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	public int totalSemesterTaken() {
		
		String key = null;
		int year;
		int semesterTaken;
		int nthsem = 1;
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		semestersByYearAndSemester = new HashMap<String, Integer>();
		//ArrayList <Integer> temp_int = new ArrayList<Integer>();
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			if(i == 0) {
				semestersByYearAndSemester.put(temp, nthsem);
			}
			year = coursesTaken.get(i).getyearTaken();
			semesterTaken = coursesTaken.get(i).getsemesterCourseTaken();
			key = year + "-" + semesterTaken;
			if(key.contentEquals(temp)) {
				continue;
			} else {
				temp = key;
				nthsem++;
				semestersByYearAndSemester.put(key, nthsem);
			
			}	
		}
		return nthsem;
	}
	
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		String key = null;
		int year;
		int semesterTaken;
		int nthsem = 1;
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		semestersByYearAndSemester = new HashMap<String, Integer>();
		//ArrayList <Integer> temp_int = new ArrayList<Integer>();
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			if(i == 0) {
				semestersByYearAndSemester.put(temp, nthsem);
			}
			year = coursesTaken.get(i).getyearTaken();
			semesterTaken = coursesTaken.get(i).getsemesterCourseTaken();
			key = year + "-" + semesterTaken;
			if(key.contentEquals(temp)) {
				continue;
			} else {
				temp = key;
				nthsem++;
				semestersByYearAndSemester.put(key, nthsem);
				
			
			}	
		}
		

		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSementer(int semester) {
		
		String key = null;
		int year;
		int semesterTaken;
		int nthsem = 1;
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		HashMap<String, Integer> checker;
		checker = new HashMap<String, Integer>();
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			if(i == 0) {
				checker.put(temp, nthsem);
			}
			year = coursesTaken.get(i).getyearTaken();
			semesterTaken = coursesTaken.get(i).getsemesterCourseTaken();
			key = year + "-" + semesterTaken;
			if(key.contentEquals(temp)) {
				continue;
			} else {
				temp = key;
				nthsem++;
				checker.put(key, nthsem);
			}	
		}
		
		
		int yeartaken;
		int semtaken;
		String key1;
		int totaltaken = 0;
		int nthsem1 = 0;
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			yeartaken = coursesTaken.get(i).getyearTaken();
			semtaken = coursesTaken.get(i).getsemesterCourseTaken();
			key1 = yeartaken + "-" + semtaken;
			nthsem1 = checker.get(key1);
			if(nthsem1 == semester) {
				totaltaken++;
			}
			
		}
		
//		System.out.println(totaltaken);
		return totaltaken;
		
	}
	
	public ArrayList<Course> getCoursesTaken(){
		return coursesTaken;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
}
