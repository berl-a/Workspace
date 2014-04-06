import java.util.Scanner;


public class ifParne {

	static int i;
	static String s;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(i!=-10000){
	
		System.out.println("Give me your number");
		
		while(true){
			s = checkNumber(sc.next());
			if(s!="Error"){
				i = Integer.parseInt(s);
				break;
			}else{
				System.out.println("No right number!");
				//while(sc.hasNext()) sc.next();
			}
		}

		
		if( i%2 == 0){
			System.out.println("Parne");
		}else{
			System.out.println("Neparne");
		}
		}
		sc.close();

	}
	private static String checkNumber(String next) {
		try{
			int i = Integer.parseInt(next);
			return String.valueOf(i);
		}catch(Exception e){}
		
		return "Error";
	}

}
