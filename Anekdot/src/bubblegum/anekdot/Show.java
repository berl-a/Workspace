package bubblegum.anekdot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Show {
	
	String s="";
	
	public String show(){
		
		Scanner scanner;
		
		File f;
		
		try {
			f=new File("D:/Anekdots.txt");
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			return "Error";
		}
		if(f.getTotalSpace()==0){
			scanner.close();
			return "Error";
		}
			
		while(scanner.hasNext()){
			s=s+scanner.next();
			s=s+" ";
		}

		
		if(s==null){
			scanner.close();
			return "Error";
		}
		
		
		
		s=s.replaceAll(String.valueOf(Add.LINE_SEP_SYMBOL), System.getProperty("line.separator"));
		
		String[] linesStrArr = s.split(System.getProperty("line.separator"));
		
		List<String> lines = new ArrayList<>();
		
		for(String s:linesStrArr){
			lines.add(s);
		}
		
		for(String line : lines)
			System.out.println(line);
		
		scanner.close();

		JOptionPane.showMessageDialog(new Read().field, s, "Анекдот", JOptionPane.INFORMATION_MESSAGE);
		
		return "OK";
	}
}
