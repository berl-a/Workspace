package bubblegum.anekdot;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Add {

	public static final char LINE_SEP_SYMBOL = '&';
	
	public static ArrayList<String> strings = new ArrayList<>();
	
	
	public static void writeToFile(){
		
		File f=new File("D:/Anekdots.txt");
		
		try {
			FileWriter fw=new FileWriter(f);
			for(String s : strings){
				fw.write(s);
				fw.write(LINE_SEP_SYMBOL);
			}
			fw.close();
			return;
			
		}catch(Exception e){}
	}
	
	

}
