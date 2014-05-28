package com.bg.inform_quest;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import com.bg.file_incryptor.Incrypt;

public class MainHolder {
	
	private static JFileChooser chooser;
	public static Reader reader = new Reader();
	public static ReaderFromStupid readerFS = new ReaderFromStupid();
	public static LinkedList<LinkedHashMap<String, boolean[]>> globalQuestAndAnswers = new LinkedList<LinkedHashMap<String, boolean[]>>();
	
	public static User user;
	
	public static Frame frame;
	
	public static JTextArea textArea;
	
	
	public static Panel panel = new Panel();
	private static boolean hasToBeDecoded;

	public static void main(String[] args) throws FileNotFoundException {
		
		chooser = new JFileChooser();
		chooser.setApproveButtonText("Цей тест");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(new myFilter());
		chooser.setCurrentDirectory(new File("").getAbsoluteFile());
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			
			if(chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().lastIndexOf(".") + 1).equals("rtest")){
				setHasToBeDecoded(true);
				if(decryprString(new Scanner(chooser.getSelectedFile()).nextLine(), +1).contains("[General]"))
					globalQuestAndAnswers = readerFS.read(chooser.getSelectedFile());
				else
					globalQuestAndAnswers = reader.read(chooser.getSelectedFile());
			}else if(chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().lastIndexOf(".") + 1).equals("tst")){
				globalQuestAndAnswers = readerFS.read(chooser.getSelectedFile());
			}
			
		}else
			JOptionPane.showMessageDialog(null, "Ви не обрали тест");
		
		user = new User(globalQuestAndAnswers);
		
		frame = new Frame();
		
	}
	
	public static String decryprString(String nextLine, int delay) {
		
		char[] cryptChars = nextLine.toCharArray();
		
		for(int i = 0; i < cryptChars.length; i++)
			cryptChars[i] = (char) (cryptChars[i] + delay);
		
		return String.valueOf(cryptChars);
	}

	
	public static boolean isHasToBeDecoded() {
		return hasToBeDecoded;
	}

	public static void setHasToBeDecoded(boolean hasToBeDecoded) {
		MainHolder.hasToBeDecoded = hasToBeDecoded;
	}


	private static class myFilter extends FileFilter{

		@Override
		public boolean accept(File e) {
			
			if(e.isDirectory())
				return true;
			if(e.getName().endsWith("rtest"))
				return true;
			if(e.getName().endsWith("tst"))
				return true;
			
			return false;
		}

		@Override
		public String getDescription() {
			return null;
		}
		
	}

}
