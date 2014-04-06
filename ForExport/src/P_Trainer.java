import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class P_Trainer {

	
	private static final String DEFAULT_STUDENTS_NAME = "Default";
	private static final char SPESICAL_CHARACTER = '*';
	private static final String CONGRATS = "My congratulations";
	private static final String PERSENT_ROUNDING_CONFIGURATION = "#.#";
	private static final String TIME_ROUNDING_CONFIGURATION = "#.##";
	private static final String HELP_WORD = "help";
	private static final String EGG_WORD = "romanrules";
	
	private static final String DIFFICULTY_MEANINGS = "0 lvl - for the 1-st form"+System.lineSeparator()+
													  "1 lvl - for the 3-rd form"+System.lineSeparator()+
													  "2 lvl - for the 5-th form"+System.lineSeparator()+
													  "3 lvl - for the 7-th form"+System.lineSeparator()+
													  "4 lvl - for the 10-th form";
	private static final String EGG_WORDS = "YEAH YEAH HE'S THE BEST!!!111!!!";
	private static final int NUMBER_OF_TRYES = 2;
	
	private static final String FILE_WAY = "D:/MathBase.txt";
	
	private static final int STRING_BUILDER_LENGTH = 120;
	private static final int POSITION_OF_NAME = 0;
	private static final int POSITION_OF_DIFFICULTY = 25;
	private static final int POSITION_OF_TIME = 50;
	private static final String TYPE_TO_SEE_THE_BASE = "base";
	
	private static int numberOfProbleM=0;
	private static long startTime;
	private static long finishTime;
	
	

	
	// 1-MinNumber; 2-MaxNumber: 3-Is-; 4-Is*; 5-NumberOfProblems 
	static int[][] courses={{0,10,0,0,5},{-10,50,1,0,7},{-50,100,1,0,10},{-20,30,1,1,10},{-50,50,1,1,15}};
	static boolean[] answers;
	
	

	public static void main(String[] args) {
		
		P_Student student=new P_Student();
		
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
					System.out.println("Input error (print from 0 to " + student.getMaxDifficulty() + ")");					
				}
			}catch(Exception e){
					System.out.println();
					System.out.println("Input error (print from 0 to " + student.getMaxDifficulty() + ")");	
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
		String textAboutMinutes = (long) min + " minutes ";
		if((long) min == 0){
			textAboutMinutes = "";
		}
		System.out.println("You wasted " + textAboutMinutes + new DecimalFormat(TIME_ROUNDING_CONFIGURATION).format(sec) + " secounds");
		System.out.println("( " + new DecimalFormat(TIME_ROUNDING_CONFIGURATION).format( (double) delayInSec / student.numberOfProblems) + " secounds for the one problem )");
		
		//Записуємо у базу
		if(student.numberOfProblems == student.numberOfRightAnswers){
			writeToBase(student.getName(), student.getDifficulty(), (long) min, (long) sec);
		}
		if(student.numberOfRightAnswers == student.numberOfProblems){
			System.out.println("Your result was writed to base");
		}
		
		if(student.numberOfRightAnswers == student.numberOfProblems){
			System.out.println("If you want to see the base, type ' " + TYPE_TO_SEE_THE_BASE + " '");
		}	
		if(student.numberOfRightAnswers == student.numberOfProblems){
			printStars(("If you want to see the base, type ' " + TYPE_TO_SEE_THE_BASE + " '").length());
			System.out.println();
		}else{
			printStars(("( " + new DecimalFormat(TIME_ROUNDING_CONFIGURATION).format( (double) delayInSec / student.numberOfProblems) + " secounds for the one problem )").length());
		}
		
		if(student.numberOfRightAnswers == student.numberOfProblems){
			if(new Scanner(System.in).next().equalsIgnoreCase(TYPE_TO_SEE_THE_BASE)){
				showBase(student.getName(), student.getDifficulty());
			}
		}
		
		
	}
		


	private static void showBase(String name, int difficulty) {
		
		Scanner scanner;
		String s = "";
		StringBuilder sb;
		
		try {
			scanner = new Scanner(new File(FILE_WAY));
			if(!(scanner.hasNext())){
				System.out.println("The base is not starter yet" + System.lineSeparator() + "Be the first!");
			}
			boolean starsPrinted = false;
			
			while(scanner.hasNext() ){
				
				s = scanner.nextLine();
				
				if(s.contains("Difficulty: "+difficulty)){
				
					int difficultyPlace = s.indexOf("Difficulty: ");
					//Видаляємо складність
					s = s.replaceAll(("Difficulty: " + difficulty), "");
					
					if(name != DEFAULT_STUDENTS_NAME){
						s = s.replace(name, "You" + addWhitespaces(name.length() , "You".length()));
					}
									
					if(!starsPrinted){
						System.out.println("              Base ot the level #" + difficulty);
						printStars(s.indexOf("Time") + 12);
						starsPrinted = true;
					}
					
					System.out.println(s);
					
					//Вставляємо складність
					s = new StringBuilder(s).insert(difficultyPlace, ("Difficulty: " + difficulty), 0, ("Difficulty: " + difficulty).length()).toString();
				}

			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("The base is not starter yet");
		}
		
		
	}
 
	private static String addWhitespaces(int nameLength, int youLength) {
		
		String whitespaces = "";
		int lengthDelay = nameLength - youLength;
		
		
		for(int i = 0; i < lengthDelay; i++){
			whitespaces += " ";
		}
		
		return whitespaces;
	}



	private static void writeToBase(String name, int difficulty, long min, long sec) {
		
		File file = new File(FILE_WAY);
		try {
			
			StringBuilder sb = new StringBuilder();
			
			/*
			sb.insert("Name:       ".length() , name, 0, name.length()); // Тому, що довжина починається не з '0', а з '1'
			sb.append(" Difficulty:       ");
			sb.insert(("Name:   	" + new Student().getMaxNameLength() + " Difficulty:   	" + String.valueOf(difficulty)).length(), String.valueOf(difficulty), 0, name.length());
			sb.append(" Time:  	 ");
			sb.insert(("Name: 	  " + new Student().getMaxNameLength() + " Difficulty: 	  " + String.valueOf(difficulty) + " Time: 	  ").length(), (min + " : " + sec), 0, name.length());
			*/
			
			sb.setLength(STRING_BUILDER_LENGTH);
			
			sb.insert(POSITION_OF_NAME, "Name: ", 0, "Name: ".length());
			sb.insert((POSITION_OF_NAME + "Name: ".length()), String.valueOf(name), 0, String.valueOf(name).length());
			
			sb.insert(POSITION_OF_DIFFICULTY, "Difficulty: ", 0, "Difficulty: ".length());
			sb.insert((POSITION_OF_DIFFICULTY + "Difficulty: ".length()), String.valueOf(difficulty), 0, String.valueOf(difficulty).length());
			
			sb.insert(POSITION_OF_TIME, "Time: ", 0, "Time: ".length());
			sb.insert((POSITION_OF_TIME + "Time: ".length()), (min + " : " + sec), 0, String.valueOf(min + " : " + sec).length());
			
			sb.append(System.lineSeparator());
			
			FileWriter filewriter = new FileWriter(file, true);
			filewriter.write(sb.toString());
			filewriter.close();
			
		} catch (IOException e) {
			
			System.out.println("Base file creating error!");
		}
		
	}

	private static void giveAProblem(P_Student student) {
		
		
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
				yourAnswer=Integer.valueOf(yourSAnswer).intValue();
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


	private static int[] generateAProblem(P_Student student) {
		
		Random r1=new Random();
		Random r=new Random(r1.nextLong());
		int firstNumber=r.nextInt(student.maxNumber-student.minNumber)+student.minNumber;
		int secoundNumber=r.nextInt(student.maxNumber-student.minNumber)+student.minNumber;

		int znak=0;;
		
		if(student.getDifficulty() == 0) znak=0;
		if(student.getDifficulty() == 1) znak=r.nextInt(2);
		if(student.getDifficulty() == 2) znak=r.nextInt(2);
		if(student.getDifficulty() == 3) znak=r.nextInt(3);
		if(student.getDifficulty() == 4) znak=r.nextInt(3);
		
		int[] i = {firstNumber, secoundNumber, znak};
		return i;
		
	}


	private static void setAttributesOfDifficulty(P_Student student) {
		
		int n=student.getDifficulty();
		
		student.minNumber=courses[n][0];
		student.maxNumber=courses[n][1];
		student.numberOfProblems=courses[n][4];
		
		student.isMinus = (courses[n][2] == 1)?true:false;
		student.isMultiplication = (courses[n][3] == 1)?true:false;
		answers=new boolean[student.numberOfProblems];
		
	}

}
