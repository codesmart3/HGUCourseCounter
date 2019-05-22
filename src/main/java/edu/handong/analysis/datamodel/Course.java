package edu.handong.analysis.datamodel;

import java.io.BufferedReader;
import java.io.File;

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
	
	
	
	public Course(String line) {
		this.studentId = line.split(", ")[0];
		this.yearMonthGraduated = line.split(", ")[1];
		this.firstMajor = line.split(", ")[2];
		this.secondMajor = line.split(", ")[3];
		this.courseCode = line.split(", ")[4];
		this.courseName = line.split(", ")[5];
		this.courseCredit = line.split(", ")[6];
		this.yearTaken = Integer.parseInt(line.split(", ")[7]);
		this.semesterCourseTaken = Integer.parseInt(line.split(", ")[8]);
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
}
