
public class P_Student {
	
	private static final int MIN_NAME_LENGTH = 3;
	private static final int MAX_NAME_LENGTH = 20;
	
	private static final int MIN_DIFFICULTY = 0;
	private static final int MAX_DIFFICULTY = 4;
	
	private static final String ROMAN_IS_CHANGED_TO = "Great Roman";
	private static final String YASYA_IS_CHANGED_TO = "ίρÿ Οσοκ³ν";


	private String name;
	
	private int difficulty;
	
	int minNumber;
	int maxNumber;
	int numberOfProblems;
	int numberOfRightAnswers;
	boolean isMinus, isMultiplication;
	

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		
		//Egg
		if(name.equalsIgnoreCase("roman")){
			setName(ROMAN_IS_CHANGED_TO);
			return true;
		}
		if(name.equalsIgnoreCase("ÿρÿ")){
			setName(YASYA_IS_CHANGED_TO);
			return true;
		}
		
		if((name.length() >= MIN_NAME_LENGTH) && ( difficulty <= MAX_NAME_LENGTH )){
			this.name = name;
			return true;
		}
		System.out.println("Name error");
		return false;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	public int getMaxDifficulty(){
		return MAX_DIFFICULTY;
		
	}
	
	public int getMaxNameLength(){
		return MAX_NAME_LENGTH;
		
	}

	public boolean setDifficulty(int difficulty) {
		if((difficulty >= MIN_DIFFICULTY) && ( difficulty <= MAX_DIFFICULTY )){
			this.difficulty = difficulty;
			return true;
		}
		return false;
	}
}
