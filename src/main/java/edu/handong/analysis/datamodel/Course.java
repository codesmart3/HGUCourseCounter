package edu.handong.analysis.datamodel;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.commons.csv.*;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	
	
	public Course(CSVRecord line) {
		this.studentId = line.get(0);
		this.yearMonthGraduated = line.get(1);
		this.firstMajor = line.get(2);
		this.secondMajor = line.get(3);
		this.courseCode = line.get(4);
		this.courseName = line.get(5);
		this.courseCredit = line.get(6);
		this.yearTaken = Integer.parseInt(line.get(7));
		this.semesterCourseTaken = Integer.parseInt(line.get(8));
	}
	
	public String getcourseName() {
		return courseName;
	}
	
	public int getstudentId(){
		int sid = Integer.parseInt(studentId);
		
		return sid;
	}
	
	public int getyearTaken() {
		return yearTaken;
	}
	
	public int getsemesterCourseTaken() {
		return semesterCourseTaken;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public static Comparator<Course> yearComparator = new Comparator<Course>() {

			public int compare(Course c1, Course c2) {
			   int course1 = c1.getyearTaken();
			   int course2 = c2.getyearTaken();

			   //ascending order
			   return course1 - course2;

			   //descending order
			   //return StudentName2.compareTo(StudentName1);
		    }};
}
