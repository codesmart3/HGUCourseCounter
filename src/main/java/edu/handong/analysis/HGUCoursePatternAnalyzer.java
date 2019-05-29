package edu.handong.analysis;

import java.io.*;
import java.util.Map.Entry;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysise.utils.NotEnoughArgumentException;
import edu.handong.analysise.utils.Utils;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.ParseException;


public class HGUCoursePatternAnalyzer {

	String input;
	String output;
	String analysis;
	String coursecode;
	public static String startyear;
	public static String endyear;
	boolean help;
	
	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args)  {
		
		Options options = createOptions();
		
		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
				System.exit(0);
				return;
			}
		}
		
		if(parseOptions(options, args)) {
			if(analysis.contentEquals("2") && coursecode == null) {
				printHelp(options);
				System.exit(0);
			}
		}
		
		System.out.println(coursecode);
		
		
		String dataPath = input; // csv file to be analyzed
		String resultPath = output; // the file path where the results are saved.
		
		
		ArrayList<CSVRecord> lines = Utils.getLines(dataPath, true);
		students = loadStudentCourseRecords(lines);
		
		//To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		if(Integer.parseInt(analysis) == 1) {
		Utils.writeAFile(linesToBeSaved, resultPath);
		}
		
	}
	
	/**
	 * This method crea3te HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			coursecode = cmd.getOptionValue("c");
			startyear = cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");
			help = cmd.hasOption("h");
			
			

		} catch (Exception e) {
			printHelp(options);
			System.exit(0);
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.build());
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input Path")
				.required()
				.build());

		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path") 
				.hasArg()     // this option is intended not to have an option value but just an option
				.argName("Output path")
				.required() // this is an optional option. So disabled required().
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
		        .build());

		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
		
		//System.out.println(help);
	}
	
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<CSVRecord> lines) {
		
		// TODO: Implement this method
		int size = 1, temp = 1, sidexist = 1, c = 0;
		String key;
		
		for(CSVRecord line : lines) {
			Course oneCourse = new Course(line);
			temp = oneCourse.getstudentId();
			if(temp > size) size = temp;
		}
		
		
		students = new HashMap<String, Student>();
		
		
		ArrayList<Course> courses = new ArrayList<Course>();
		Student[]studentsarr = new Student[size];
		
		temp = 0;
	
		for(int i = 0; i < lines.size(); i++) {
			if(studentsarr.length == 0) {
				studentsarr[0] = new Student(lines.get(i).get(0));
				temp = Integer.parseInt(lines.get(i).get(0));
			}
			sidexist = Integer.parseInt(lines.get(i).get(0));
			courses.add(new Course(lines.get(i)));
			
			if(temp == sidexist) {
				studentsarr[temp - 1].addCourse(courses.get(i));
				continue;
			} else {
			studentsarr[sidexist - 1] = new Student(lines.get(i).get(0));
			studentsarr[sidexist - 1].addCourse(courses.get(i));
			temp = sidexist;
			}
		}
		
		for(c = 0; c < studentsarr.length; c++) {
			key = studentsarr[c].getStudentId();
			students.put(key, studentsarr[c]);
		}
		
		
/* Getting ratio for hw6*/		

		int checkForCourseCode = Integer.parseInt(analysis);
		
		if(checkForCourseCode == 2 && coursecode != null) {
		ArrayList<Course> takenGivenCourse = new ArrayList<Course>();
		HashMap<String, Integer> numberOfCoursesInGivenYearSem = new HashMap<String, Integer>();
		HashMap<String, Integer> numOfStudentsInGivenYear = new HashMap<String, Integer>();
		
		String givenCourse = coursecode;
		
		for(Course crs:courses) {
			if(crs.getCourseCode().contentEquals(givenCourse)) {
				takenGivenCourse.add(crs);
			}
		}
		
		String courseName = takenGivenCourse.get(0).getcourseName();
		
		
		Collections.sort(takenGivenCourse, Course.yearComparator);
		
		int yearsTaken;
		int semsTaken;
		String yearsAndSemsTaken;
		String tmp = null;
		int numStudents = 0;
		
		for(Course crs:takenGivenCourse) {
			
			yearsTaken = crs.getyearTaken();
			semsTaken = crs.getsemesterCourseTaken();
			yearsAndSemsTaken = yearsTaken + "-" + semsTaken;
			
			if(numberOfCoursesInGivenYearSem.size() == 0) {
				numberOfCoursesInGivenYearSem.put(yearsAndSemsTaken, 1);
				tmp = yearsAndSemsTaken;
				continue;
			}
			
			numStudents = numberOfCoursesInGivenYearSem.containsKey(yearsAndSemsTaken) ? numberOfCoursesInGivenYearSem.get(yearsAndSemsTaken) : 0;
			numberOfCoursesInGivenYearSem.put(yearsAndSemsTaken, numStudents + 1);
		}


		int studentId = 0;
		int tempo = 0;
		int syear = 0, eyear = 0;
		
		try {
		syear = Integer.parseInt(startyear);
		eyear = Integer.parseInt(endyear);
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid number format");
			System.exit(0);
		}
		
		for(Course crs:courses) {
			yearsTaken = crs.getyearTaken();
			semsTaken = crs.getsemesterCourseTaken();
			studentId = crs.getstudentId();
			yearsAndSemsTaken = yearsTaken + "-" + semsTaken;
			
			if(yearsTaken < syear || yearsTaken > eyear) {
				continue;
			}
			
			if(numOfStudentsInGivenYear.size() == 0) {
				numOfStudentsInGivenYear.put(yearsAndSemsTaken, 1);
				tempo = studentId;
				tmp = yearsAndSemsTaken;
				continue;
			}
			
			if(tempo == studentId && tmp.contentEquals(yearsAndSemsTaken)) {
				continue;
			}
			numStudents = numOfStudentsInGivenYear.containsKey(yearsAndSemsTaken) ? numOfStudentsInGivenYear.get(yearsAndSemsTaken) : 0;
			numOfStudentsInGivenYear.put(yearsAndSemsTaken, numStudents + 1);
			tempo = studentId;
			tmp = yearsAndSemsTaken;
		}

		String selectYearSemForGivenCourse, selectYearSemForAllCourses;
		int numOfStudentsForGivenCourse, numOfStudentsForAllYears;
		float ratio;
		String year, semester, ratioFinalResult;
		ratio = 0;
		String stringRatio;
		
		ArrayList<String> ratioResult = new ArrayList<String>();
		String headerForRatioResult = "Year" + "," + "Semester" + "," + "CourseCode" + "," + "CourseName" + "," + "TotalStudents" + "," + "StudentsTaken" + "," + "Rate" + "\n";
		
		
		Iterator<Entry <String, Integer>> itr1 = numOfStudentsInGivenYear.entrySet().iterator();
		while(itr1.hasNext()) {
			Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) itr1.next();
			selectYearSemForGivenCourse = pair.getKey();
			numOfStudentsForGivenCourse = pair.getValue();
			if(!numberOfCoursesInGivenYearSem.containsKey(selectYearSemForGivenCourse)) {
				ratio  = 0;
				stringRatio = "0.0%";
				numOfStudentsForAllYears = 0;
				year = selectYearSemForGivenCourse.split("-")[0];
				semester = selectYearSemForGivenCourse.split("-")[1];
				ratioFinalResult = year + "," + semester + "," + givenCourse + "," + courseName + "," + numOfStudentsForGivenCourse + "," + numOfStudentsForAllYears + "," + stringRatio + "\n";
				ratioResult.add(ratioFinalResult);
				continue;
			}
			numOfStudentsForAllYears = numberOfCoursesInGivenYearSem.get(selectYearSemForGivenCourse);
			ratio = ((float)numOfStudentsForAllYears / numOfStudentsForGivenCourse) * 100;
			stringRatio = String.format("%.1f", ratio) + "%";
			year = selectYearSemForGivenCourse.split("-")[0];
			semester = selectYearSemForGivenCourse.split("-")[1];
			
			
			Collections.sort(ratioResult);
			
			ratioFinalResult = year + "," + semester + "," + givenCourse + "," + courseName + "," + numOfStudentsForGivenCourse + "," + numOfStudentsForAllYears + "," + stringRatio + "\n";
			
			ratioResult.add(ratioFinalResult);
		}
		
		Collections.sort(ratioResult);

		ratioResult.add(0, headerForRatioResult);

		Utils.writeAFile(ratioResult, output);
		
		
		}//end of if
		
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
		ArrayList<String> arr = new ArrayList<String>();
		String header = "StudentID" + "," + "TotalNumberOfSemestersRegistered" + "," + "Semester" + "," + "NumCoursesTakenInTheSemester" + "\n";
		header.replace("[","");
		arr.add(header);
		
		int finalNumberOfCoursesTaken = 0;
		int letstry = 0;
		int semsTakenInNthSem;
		int finalCoursesTaken = 0;
		Iterator<String> itr = sortedStudents.keySet().iterator();
		while(itr.hasNext()) { 
			String key = itr.next();
			
			finalCoursesTaken = sortedStudents.get(key).totalSemesterTaken();
			finalNumberOfCoursesTaken = sortedStudents.get(key).getTotalSemsTaken();
			
			for(int i = 0; i < finalCoursesTaken + 1; i++) {
				semsTakenInNthSem = sortedStudents.get(key).getNumCourseInNthSementer(i);
				int sid1 = Integer.parseInt(key);
				
				String line = sid1 + "," + finalNumberOfCoursesTaken + "," + i + "," + semsTakenInNthSem + "\n";
				if(i == 0) {
					continue;
				}
				arr.add(line);
			}
			
		}		
		return arr;
		// do not forget to return a proper variable.
	}
	

}
