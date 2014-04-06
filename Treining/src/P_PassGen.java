import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.Scanner;


public class P_PassGen {
	
	private static final int MAX_ASCII_NUMBER=80;
	private static final int MAX_LENGTH_OF_THE_PASSWORD=100;
	private static final char SPESICAL_CHARACTER='*';
	private static final char MIN_ASCII_NUMBER = 30;

	
	public static void main(String[] args) {
			
		System.out.println("Print the length of the password");
		String password="";
		int numberOfCharacters;
		Scanner sc=new Scanner(System.in);
		while(true){
			try{
				numberOfCharacters=sc.nextInt();
				if(numberOfCharacters>MAX_LENGTH_OF_THE_PASSWORD){
					System.out.println("Print the number under " + MAX_LENGTH_OF_THE_PASSWORD);
					continue;
				}
				sc.close();
				break;	
			}catch(Exception e){
				System.out.println("Print the number of characters");
				password="";
				numberOfCharacters=0;
				continue;
			}
		}
		for(int i=0;i<numberOfCharacters;i++){
			password=random(password);
		}
		System.out.println("Your password:");
		showBeautiful(password.length(), 0);
		System.out.println(password);
		showBeautiful(password.length(), 1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(password), null);
		System.out.println("Password is copied to your clipboard");
				
	}

	private static void showBeautiful(int length, int i) {
		
		if(i==0){
			
			for(int starCounter=0;starCounter<length;starCounter++){
				System.out.print(SPESICAL_CHARACTER);
			}
			System.out.println();
			System.out.println();
			
		}else if(i==1){
			
			System.out.println();
			for(int starCounter=0;starCounter<length;starCounter++){
				System.out.print(SPESICAL_CHARACTER);
			}
			System.out.println();
			
		}
		
	}
	
	
	private static String random(String s) {
		
		Random r=new Random();
		return s.concat(String.valueOf( (char) (r.nextInt(MAX_ASCII_NUMBER)+MIN_ASCII_NUMBER)));
			
	}

}
