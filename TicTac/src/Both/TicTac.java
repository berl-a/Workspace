package Both;

import java.util.Scanner;

public class TicTac {

	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println();
		System.out.println("Hey, it's TicTac game!");
		System.out.println("One player or two players?");
		System.out.println("Just print 'one' or 'two'");
		System.out.println();
		
				
		while(true){
			
			String s=sc.next();
			if(s.equalsIgnoreCase("One")){
				new One_cycle().pvc();
				break;
			}
			else if(s.equalsIgnoreCase("Two")){
				new Two_cycle().pvp();
				break;
			}
			else if(s.equalsIgnoreCase("Romanrules")){
			
			System.out.println();
			System.out.println("Yeah, it's TRUTH!");
			System.out.println("He's NUMBER ONE");
			System.out.println();
			
			}
			System.out.println("Print 'one' or 'two'");
		}

	}

}
