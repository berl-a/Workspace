package Both;



public class One_cycle {
	
	public static final int FIELD_SIZE = 3;
	
	public static final int MIN_TURNS_TO_WIN=FIELD_SIZE*2;
	
	public static final char DEFAULT_MEANING_OF_THE_FIELD = ' ';
																																	
	public static final String FIRST_PART_OF_THE_FIELD = "{";

	public static final String SECOUND_PART_OF_THE_FIELD = "}";

	public static final char FIRST_ZNAK = 'X';
	public static final char SECOUND_ZNAK = 'O';
	
	static char[][] field=new char[FIELD_SIZE][FIELD_SIZE];
	
	public void pvc(){
		
		One_game yt=new One_game();
		
		
		while(true){
			
			field=yt.fillTheField(field);
			
			yt.newGame();
			
			yt.turn();
			
			yt.showField();
			
			yt.turn();
			
			yt.showField();
			
			yt.turn();
			
			
			String s=yt.didSmbWin();

						
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE!");
			}
			
			
			yt.showField();
			
			
			yt.turn();
			
			s=yt.didSmbWin();

			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE!");
			}
			
			yt.showField();
			
			yt.turn();
			
			s=yt.didSmbWin();
			
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
				field=yt.restart();
				continue;
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE");
				field=yt.restart();
				continue;
			}
			
			
			yt.showField();
			
			yt.turn();
			
			s=yt.didSmbWin();
			
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
				field=yt.restart();
				continue;
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE");
				field=yt.restart();
				continue;
			}
			
			
			yt.showField();
			
			yt.turn();
			
			s=yt.didSmbWin();
			
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
				field=yt.restart();
				continue;
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE");
				field=yt.restart();
				continue;
			}
			
			
			yt.showField();
			
			yt.turn();
			
			s=yt.didSmbWin();
			
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
				field=yt.restart();
				continue;
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE");
				field=yt.restart();
				continue;
			}
			
			
			yt.showField();
			
			yt.turn();

			s=yt.didSmbWin();
			
			if(s=="Player"){
				yt.showField();
				System.out.println("YOU WON!");
				field=yt.restart();
				continue;
			}else if(s=="Computer"){
				yt.showField();
				System.out.println("YOU LOSE");
				field=yt.restart();
				continue;
			}
			
			
			yt.showField();
			
			field=yt.restart();
		}
		
	}
}