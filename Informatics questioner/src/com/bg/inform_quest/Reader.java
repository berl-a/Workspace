package com.bg.inform_quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Reader {

	private LinkedList<LinkedHashMap<String, boolean[]>> globalQuestAndAns;
	
	Scanner s;
	
	String line;

	private String name;
	
	public LinkedList<LinkedHashMap<String, boolean[]>> read(File f){
		
		setGlobalQuestAndAns(new LinkedList<LinkedHashMap<String, boolean[]>>());
		
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File reading error!");
		}
		
		if(MainHolder.isHasToBeDecoded())
			setName(MainHolder.decryprString(s.nextLine(), +1));
		else
			setName(s.nextLine());
		
		if(MainHolder.isHasToBeDecoded())
			line = MainHolder.decryprString(s.nextLine(), +1);
		else
			line = s.nextLine();
		
		while(s.hasNextLine()) {
			
			LinkedHashMap<String, boolean[]> questAndAns = new LinkedHashMap<String, boolean[]>();
			questAndAns.put(line, new boolean[]{true});
			
			while(s.hasNextLine()) {
				
				if(MainHolder.isHasToBeDecoded())
					line = MainHolder.decryprString(s.nextLine(), +1);
				else
					line = s.nextLine();
				
				if(line.charAt(0) == '1')
					questAndAns.put(line.substring(2, line.length()), new boolean[]{true});
				else if(line.charAt(0) == '0')
					questAndAns.put(line.substring(2, line.length()), new boolean[]{false});
				else
					break;
			}
			getGlobalQuestAndAns().add(questAndAns);
			
		}
		s.close();
		
		return getGlobalQuestAndAns();
	}

	private LinkedList<LinkedHashMap<String, boolean[]>> getGlobalQuestAndAns() {
		return globalQuestAndAns;
	}

	private void setGlobalQuestAndAns(LinkedList<LinkedHashMap<String, boolean[]>> globalQuestAndAns) {
		this.globalQuestAndAns = globalQuestAndAns;
	}

	public String getNameOfTest() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
