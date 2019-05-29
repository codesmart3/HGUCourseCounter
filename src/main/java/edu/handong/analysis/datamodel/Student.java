package edu.handong.analysis.datamodel;
import java.util.*;

import edu.handong.analysis.HGUCoursePatternAnalyzer;

public class Student {
	private String studentId;
	private ArrayList<Course>coursesTaken;
	private HashMap<String, Integer> semestersByYearAndSemester;
	private int totalSemsTaken; 

	public Student(String studentId) {
		this.studentId = studentId;
		coursesTaken = new ArrayList<Course>();
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	public int totalSemesterTaken() {
		
		String key = null;
		int syear = Integer.parseInt(HGUCoursePatternAnalyzer.startyear);
		int eyear = Integer.parseInt(HGUCoursePatternAnalyzer.endyear);
		int year;
		int semesterTaken;
		int nthsem = 1;
		
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		semestersByYearAndSemester = new HashMap<String, Integer>();
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			if(coursesTaken.get(i).getyearTaken() < syear || coursesTaken.get(i).getyearTaken() > eyear)
				continue;
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
		int syear = Integer.parseInt(HGUCoursePatternAnalyzer.startyear);
		int eyear = Integer.parseInt(HGUCoursePatternAnalyzer.endyear);
		int year;
		int semesterTaken;
		int nthsem = 1;
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		semestersByYearAndSemester = new HashMap<String, Integer>();
		
		
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			if(coursesTaken.get(i).getyearTaken() < syear || coursesTaken.get(i).getyearTaken() > eyear)
				continue;
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

		return totaltaken;
		
	}
	
	public ArrayList<Course> getCoursesTaken(){
		return coursesTaken;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String ratioForGivenCourse(String Course, String year) {
		int valSemester;
		int valYear;
		String yearAndSem, temp = null;
		String givenCourse = Course, givenYear = year;
		int countYears = 1;
		int countYearsWithCourse;
		float ratio;
		
		for(Course countCourse:coursesTaken) {
			valYear = countCourse.getyearTaken();
			valSemester = countCourse.getsemesterCourseTaken();
			yearAndSem = valYear + "-" + valSemester;
			if(temp.contentEquals(yearAndSem)) {
				countYears++;
				continue;
			}else {
			temp = yearAndSem;
			System.out.println(yearAndSem);
			}
		}
		
		
		return null;
	}
	
	public int getTotalSemsTaken() {
		totalSemsTaken = 0;
		int year;
		int semesterTaken;
		int nthsem = 1;
		String temp = coursesTaken.get(0).getyearTaken() + "-" + coursesTaken.get(0).getsemesterCourseTaken();
		ArrayList<String> checkForDuplicate = new ArrayList<String>();
		String toGetTheTotalSemsTaken = temp;
		
		for(int i = 0; i < coursesTaken.size(); i++) {
			year = coursesTaken.get(i).getyearTaken();
			semesterTaken = coursesTaken.get(i).getsemesterCourseTaken();
			toGetTheTotalSemsTaken = year + "-" + semesterTaken;
		
		if(i == 0) {
			String compareWithExistingSems;
			year = coursesTaken.get(i).getyearTaken();
			semesterTaken = coursesTaken.get(i).getsemesterCourseTaken();
			compareWithExistingSems = year + "-" + semesterTaken;
			checkForDuplicate.add(compareWithExistingSems);
			totalSemsTaken++;
		}else {
				if(!checkForDuplicate.contains(toGetTheTotalSemsTaken)) {
					checkForDuplicate.add(toGetTheTotalSemsTaken);
					totalSemsTaken++;
				
				}
			}
		}
		
		
		return totalSemsTaken;
	
	}
	
}
