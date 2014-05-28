package com.bg.file_incryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class Incrypt {

	private static JFileChooser chooser;
	private static File file;
	
	public static void main(String[] args) {

		chooser = new JFileChooser();
		chooser.setApproveButtonText("Перекодувати");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(new myFilter());
		chooser.setCurrentDirectory(new File("").getAbsoluteFile());
		
		for(int i = 0; i < 2; i++){
			
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				
				if(chooser.getSelectedFile().getName().endsWith("tst")){
					
					file = chooser.getSelectedFile();
					System.out.println("OK");
					break;
					
				}else if(chooser.getSelectedFile().getName().endsWith("txt")){
					
					file = chooser.getSelectedFile();
					System.out.println("OK");
					break;
					
				}
				
			}else
				JOptionPane.showMessageDialog(null, "Оберіть щось!");
			
		}
		
		incrypt(file, -1);
		
	}
	
	public static void incrypt(File file, int delay) {
		
		File encFile = new File(file.getPath().replace(file.getName(), "") + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".rtest");
		
		Scanner s = null;
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(encFile);
		} catch (IOException e1) {}
		
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {}
		
		try {
			while(s.hasNextLine()){
				fw.write(cryprString(s.nextLine(), delay));
				if(s.hasNextLine())
					fw.write(System.lineSeparator());
			}
			fw.close();
		}catch(Exception e){}
		
		s.close();
	}

	private static char[] cryprString(String nextLine, int delay) {
		
		char[] cryptChars = nextLine.toCharArray();
		
		for(int i = 0; i < cryptChars.length; i++)
			cryptChars[i] = (char) (cryptChars[i] + delay);
		
		return cryptChars;
	}

	private static class myFilter extends FileFilter{

		@Override
		public boolean accept(File e) {
			
			if(e.isDirectory())
				return true;
			if(e.getName().endsWith("tst"))
				return true;
			if(e.getName().endsWith("txt"))
				return true;
			
			return false;
		}

		@Override
		public String getDescription() {
			return null;
		}
		
	}

}
