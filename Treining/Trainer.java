package mathTrainer;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import mathTrainer.Student;

public class Trainer {

	
	private static final String DEFAULT_STUDENTS_NAME = "Default";
	private static final char SPESICAL_CHARACTER = '*';
	private static final String CONGRATS = "My congratulations";
	private static final String PERSENT_ROUNDING_CONFIGURATION = "#.#";
	private static final String TIME_ROUNDING_CONFIGURATION = "#.##";
	private static final String HELP_WORD = "help";
	private static final String EGG_WORD = "romanrules";
	private static final String DIFFICULTY_MEANINGS = "0 lvl - for the 1-st form"+System.getProperty("line.separator")+
													  "1 lvl - for the 3-rd form"+System.getProperty("line.separator")+
													  "2 lvl - for the 5-th form"+System.getProperty("line.separator")+
													  "3 lvl - for the 7-th form"+System.getProperty("line.separator")+
													  "4 lvl - for the 10-th form";
	private static final String EGG_WORDS = "YEAH YEAH HE'S THE BEST!!!111!!!";
	private static final int NUMBER_OF_TRYES = 2;
	private static int numberOfProbleM=0;
	private static long startTime;
	private static long finishTime;
	

	
	// 1-MinNumber; 2-MaxNumber: 3-Is-; 4-Is*; 5-NumberOfProblems 
	static int[][] courses={{0,10,0,0,5},{-10,50,1,0,7},{-50,100,1,0,10},{-20,30,1,1,10},{-50,50,1,1,15}};
	static boolean[] answers;
	
	

	public static void main(String[] args) {
		
		Student student=new Student();
		
		System.out.println("Print your name");
		
		if(student.setName(new Scanner(System.in).nextLine())==false){
			student.setName(DEFAULT_STUDENTS_NAME);
			System.out.println("Input error (name changed to default)");
		}
		System.out.println();
		
		System.out.println("Print a difficulty (or type ' " + HELP_WORD + " ')");
		
		
		boolean choosedDifficulty = false;
		
		while(choosedDifficulty == false){
			try{
				//Set difficulty or show help
				String tempS = new Scanner(System.in).next();
				if(tempS.equalsIgnoreCase(HELP_WORD)){
					System.out.println();
					System.out.println(DIFFICULTY_MEANINGS);
					System.out.println();
				}else if(tempS.equalsIgnoreCase(EGG_WORD)){
					System.out.println();
					System.out.println(EGG_WORDS);;
					System.out.println();
				}else if(student.setDifficulty(Integer.parseInt(tempS))){
					choosedDifficulty = true;
				}else{
					System.out.println("Input error (print from 0 to 4)");					
				}
			}catch(Exception e){
					System.out.println();
					System.out.println("Input error (print from 0 to 4)");
					System.out.println();
			}
		}
		
		
		
		System.out.println();
			
		setAttributesOfDifficulty(student);
		
		//Перед дачею прикладу починаємо відлік часу
		
		startTime = System.currentTimeMillis();
		
		for(int i=0;i<student.numberOfProblems;i++){
			giveAProblem(student);
		}
		
		//Після прикладів закінчуємо відлік часу
		
		finishTime = System.currentTimeMillis();
		
		long delayInSec = (finishTime - startTime) / 1000;
		
		double sec = delayInSec % 60;
		//(delayInSec - sec) - діляться  на хвилини без остачі 
		double min = (delayInSec - sec) / 60;
		
		System.out.println();
		printStars((CONGRATS + ", " + student.getName() + " !").length());
		System.out.println(CONGRATS + ", " + student.getName() + " !");
		System.out.println("On the difficulty # "+student.getDifficulty());
		System.out.println("Your percent or right answers is " + new DecimalFormat(PERSENT_ROUNDING_CONFIGURATION).format((double)student.numberOfRightAnswers / (double)student.numberOfProblems * 100) + "% !");
		System.out.println("You wasted " + (long) min + " minutes " + new DecimalFormat(TIME_ROUNDING_CONFIGURATION).format(sec) + " secounds");
		System.out.println("( " + new DecimalFormat(TIME_ROUNDING_CONFIGURATION).format( (double) delayInSec / student.numberOfProblems) + " secounds for the one problem )");
		writeToBase(student.getName(), student.getDifficulty(), student.numberOfRightAnswers, delayInSec);
	}

	private static void giveAProblem(Student student) {
		
		
		int[] problemInArray = generateAProblem(student);
		
		int firstNumber=problemInArray[0];
		int secoundNumber=problemInArray[1];
		
		String znak="";
		
		int result =0;
		
		if(problemInArray[2] == 0){
			znak="+"; 
			result = firstNumber+secoundNumber;
		}
		if(problemInArray[2] == 1){
			znak="-";
			result = firstNumber-secoundNumber;
		}
		if(problemInArray[2] == 2){
			znak="*";
			result = firstNumber*secoundNumber;
		}
		
		String firstDugka="";
		String secoundDugka="";
		
		if(secoundNumber < 0){
			firstDugka="(";
			secoundDugka=")";
		}
		
		
		System.out.println();
		System.out.println("Problem # " + (++numberOfProbleM));
		printStars((firstNumber+znak+firstDugka+secoundNumber+secoundDugka).length());
		System.out.println(firstNumber+znak+firstDugka+secoundNumber+secoundDugka);
		printStars((firstNumber+znak+firstDugka+secoundNumber+secoundDugka).length());
		System.out.println();
		
		
		if(getTheAnswer(result, student.getDifficulty()) == true){
			answers[numberOfProbleM-1]=true;
			student.numberOfRightAnswers++;
		}else{
			answers[numberOfProbleM-1]=false;
		}
		
		firstDugka="";
		secoundDugka="";
		
	}


	private static boolean getTheAnswer(int result, int difficulty) {

		System.out.println("Your answer is...");
		
		String yourSAnswer;
		int yourAnswer;
		int numberOfTheAnswer=0;
		
		while(numberOfTheAnswer<NUMBER_OF_TRYES)	{
			
			try{
				yourSAnswer = new Scanner(System.in).next();
				numberOfTheAnswer++;
				yourAnswer=Integer.valueOf(yourSAnswer);
			}catch(Exception e){
				System.out.println("Answer input error");
				yourAnswer = result+1;
			}
				
			if(yourAnswer == result){
				System.out.println("Right!");
				System.out.println();
				return true;
			}
			System.out.println("Not right");
			if((difficulty == 2) || (difficulty == 3) || (difficulty == 4)){
				break;
				//На високому рівні складності тільки 1 спроба
			}
														
			
		}
		System.out.println();
		System.out.println();
		return false;
				
	}


	private static void printStars(int length) {
		for(int i=0;i<length;i++){
			System.out.print(SPESICAL_CHARACTER);
		}
		System.out.println();
		
	}


	private static int[] generateAProblem(Student student) {
		
		Random r=new Random();
		int firstNumber=r.nextInt(student.maxNumber-student.minNumber)+student.minNumber;
		int secoundNumber=r.nextInt(student.maxNumber-student.minNumber)+student.minNumber;

		int znak=0;;
		
		if(student.getDifficulty() == 0) znak=0;
		if(student.getDifficulty() == 1) znak=r.nextInt(2);
		if(student.getDifficulty() == 2) znak=r.nextInt(2);
		if(student.getDifficulty() == 3) znak=r.nextInt(3);
		
		int[] i = {firstNumber, secoundNumber, znak};
		return i;
		
	}


	private static void setAttributesOfDifficulty(Student student) {
		
		int n=student.getDifficulty();
		
		student.minNumber=courses[n][0];
		student.maxNumber=courses[n][1];
		student.numberOfProblems=courses[n][4];
		
		student.isMinus = (courses[n][2] == 1)?true:false;
		student.isMultiplication = (courses[n][3] == 1)?true:false;
		answers=new boolean[student.numberOfProblems];
		
		
	}

}
