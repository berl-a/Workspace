package com.bg.test_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {

	File reading_file;
	Scanner s;
	
	LinkedList<LinkedList<String>> globalQuestAndAns;
	
	private String name;
	
	public Reader(File f){
		reading_file = f;
		
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {}
	}
	
	public LinkedList<LinkedList<String>> read(){
		
		globalQuestAndAns = new LinkedList<LinkedList<String>>();
		
		s.nextLine();
		String line = s.nextLine();
		
		if(line.contains("Title"))
			setName(line.substring(line.indexOf("=") + 1, line.length()));
		
		s.nextLine();
		line = s.nextLine();
		
		
		while(s.hasNext()){
			
			LinkedList<String> questAndAns = new LinkedList<String>();
			
			questAndAns.add(line.substring(line.indexOf("=") + 1, line.length()) + " ");
			for(int i = 0; i < 3; i++)
				line = s.nextLine();
			
			String[] vars = new String[5];
			boolean[] rightVars = new boolean[5];
			
			line = s.nextLine();
			
			for(int i = 0; i < vars.length; i++){
				vars[i] = line.substring(line.indexOf("=") + 1, line.length());
				line = s.nextLine();
			}
			
			for(int i = 0; i < rightVars.length; i++){
				if(line.contains("1"))
					rightVars[i] = true;	
				line = s.nextLine();
			}
			
			for(int i = 0; i < vars.length; i++){
				if(rightVars[i])
					questAndAns.add(vars[i]);
			}
			/*
			System.out.println();
			for(String s : questAndAns)
				System.out.println("Ãîòîâ³ â³äïîâ³ä³: " + s);
			System.out.println();
			*/
			globalQuestAndAns.add(questAndAns);
			
			if(s.hasNext())
				line = s.nextLine();
			
		}
		
		return globalQuestAndAns;
	}
	
	public LinkedList<String> search(String keyword){
		
		int indexOfQuestionWithAnswers = -1;
		boolean found = false;
		for(int i = 0; i < globalQuestAndAns.size(); i++){
			
			for(String s : globalQuestAndAns.get(i)){
				if(s.toLowerCase().contains(keyword.toLowerCase())){
					indexOfQuestionWithAnswers = i;
					found = true;
					break;
				}
			}
			
			if(found)
				break;
			
		}
		//System.out.println("Found? " + found);
		
		if(keyword.equals(""))
			return null;
		
		if(found)
			return globalQuestAndAns.get(indexOfQuestionWithAnswers);
		else
			return null;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
	
	
	
	
}
