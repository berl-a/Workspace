package Both;
import java.util.Random;
import java.util.Scanner;




public class Two_game {
	
	char[][] filledArray;
	
	int numberOfTurn=0;
	
	int whoseTurn;
	
	char playersZnak;
	char computersZnak;
	
	Scanner scanner=new Scanner(System.in);
	String str;
	int number;
	
	Random r=new Random();

	public char[][] fillTheField(char[][] field) {
		for(int i0 = 0;i0 < Two_cycle.FIELD_SIZE;i0++){
			for(int i1 = 0;i1 < Two_cycle.FIELD_SIZE;i1++){
				field[i0][i1]=Two_cycle.DEFAULT_MEANING_OF_THE_FIELD;
			}
		}
		return field;
	}	
	
	public void newGame() {
		
			int random_n=r.nextInt(4);
			if(random_n==0){
				whoseTurn=0;
				playersZnak=Two_cycle.FIRST_ZNAK;
				computersZnak=Two_cycle.SECOUND_ZNAK;
			}
			if(random_n==1){
				whoseTurn=0;
				playersZnak=Two_cycle.SECOUND_ZNAK;
				computersZnak=Two_cycle.FIRST_ZNAK;
			}
			if(random_n==2){
				whoseTurn=1;
				playersZnak=Two_cycle.FIRST_ZNAK;
				computersZnak=Two_cycle.SECOUND_ZNAK;
			}
			if(random_n==3){
				whoseTurn=1;
				playersZnak=Two_cycle.SECOUND_ZNAK;
				computersZnak=Two_cycle.FIRST_ZNAK;
			}
			
			System.out.println();
			char znak;
			
			if(whoseTurn==0){
				znak=playersZnak;
			}else{
				znak=computersZnak;
			}
			
	}
		
		public void turn(){
			if(whoseTurn==0){
				System.out.println("Player 1: place "+playersZnak);
				while(true){
					str=scanner.next();
					if(str.length()!=1){
						continue;
					}
					try{
						number=Integer.parseInt(str);
					}catch(Exception e){
						System.out.println("Just one znak");
						continue;
					}
					break;					
				}
				
				int i=0;
				while(i<30){
					if(placeTurn(number)=="Ok"){
						numberOfTurn++;
						whoseTurn=1;
						return;
					}else{
						System.out.println("There's smth");
						System.out.println("I'll place it where I want!");
						number=r.nextInt();
						i++;					
					}
				}
			}
			
			if(whoseTurn==1){
				System.out.println("Player 2: place "+computersZnak);
				while(true){
					str=scanner.next();
					if(str.length()!=1){
						continue;
					}
					try{
						number=Integer.parseInt(str);
					}catch(Exception e){
						System.out.println("Just one znak");
						continue;
					}
					break;					
				}
				
				int i=0;
				while(i<30){
					if(placeTurn(number)=="Ok"){
						numberOfTurn++;
						whoseTurn=0;
						return;
					}else{
						System.out.println("There's something!");
						System.out.println("I'll put it where I want");
						number=r.nextInt();
						i++;					
					}
				
			}}
			
			return;
			
		}
			
	private String placeTurn(int position){
		
		int posX=0, posY=0;
		
		// Çâåðõó âíèç, çë³âà íàïðàâî
		
		if(position==1){
			posX=2;
			posY=0;
		}
		if(position==2){
			posX=2;
			posY=1;
		}
		if(position==3){
			posX=2;
			posY=2;
		}
		if(position==4){
			posX=1;
			posY=0;
		}
		if(position==5){
			posX=1;
			posY=1;
		}
		if(position==6){
			posX=1;
			posY=2;
		}
		if(position==7){
			posX=0;
			posY=0;
		}
		if(position==8){
			posX=0;
			posY=1;
		}
		if(position==9){
			posX=0;
			posY=2;
		}
		if(position==0){
			return "Invalid number";
		}
		
		if(Two_cycle.field[posX][posY]==Two_cycle.DEFAULT_MEANING_OF_THE_FIELD){
			if(whoseTurn==0){
				Two_cycle.field[posX][posY]=playersZnak;
			}
			else if(whoseTurn==1){
				Two_cycle.field[posX][posY]=computersZnak;
			}
			return "Ok";
			
		}
		return "No";
		
	}
	
	public void showField(){
		for(int i0 = 0;i0 < Two_cycle.FIELD_SIZE;i0++){
			for(int i1 = 0;i1 < Two_cycle.FIELD_SIZE;i1++){
				System.out.print(Two_cycle.FIRST_PART_OF_THE_FIELD+Two_cycle.field[i0][i1]+Two_cycle.SECOUND_PART_OF_THE_FIELD);
			}
			System.out.println();
			
		}
		System.out.println();
	}
	
	public char[][] restart(){
		
		numberOfTurn=0;
		char[][] array=new char[3][3];
		System.out.println("Let's start everything agane! I'll put it wherever I wanted to live my life");
		System.out.println("---------------------------------------------------------------------------");
		return array;
		
	}
	
	//ÄÓÆÅ òóïà ïåðåâ³ðêà

	public String didSmbWin() {
		
		int[][] intField=new int[3][3];
		
		
		for(int i1=0;i1<3;i1++){
			for(int i2=0;i2<3;i2++){
				if(Two_cycle.field[i1][i2]==this.playersZnak){
					intField[i1][i2]=0;
				}
				if(Two_cycle.field[i1][i2]==this.computersZnak){
					intField[i1][i2]=1;
				}
				if(Two_cycle.field[i1][i2]==Two_cycle.DEFAULT_MEANING_OF_THE_FIELD){
					intField[i1][i2]=6;
				}
			}
		}
		
		
		for(int i=0;i<3;i++){
		
			if((intField[0][i]==intField[1][i])&&(intField[0][i]==intField[2][i])){
				
				if(intField[0][i]==0){
					return "Player";
				}
				
				if(intField[0][i]==1){
					return "Computer";
				}
			}
			
			if((intField[i][0]==intField[i][1])&&(intField[i][0]==intField[i][2])){
				
				if(intField[i][0]==0){
					return "Player";
				}
				
				if(intField[i][0]==1){
					return "Computer";
				}
			}
			
			//ÄÓÆÅ ÒÓÏÎ
			
			if((intField[0][0]==intField[1][1])&&(intField[0][0]==intField[2][2])){
				
				if(intField[0][0]==0){
					return "Player";
				}
				
				if(intField[0][0]==1){
					return "Computer";
				}
			}

			if((intField[0][2]==intField[1][1])&&(intField[0][2]==intField[2][0])){
	
				if(intField[0][2]==0){
					return "Player";
				}
	
				if(intField[0][2]==1){
					return "Computer";
				}
			}	
		}
	
		return "Nobody";
		
	}
}