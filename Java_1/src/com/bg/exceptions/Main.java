package com.bg.exceptions;

import exceptions.IllegalHumanNameException;
import exceptions.IllegalMoneyException;

public class Main {

	
	public static void main(String[] args) {
		
		try{
			Human human = Human.createInstance("r");
			System.out.println("Human's name is '" + human.name + "'");
		}catch(IllegalHumanNameException exception){
			System.out.println("Name error: " + exception.getMessage());
		}
		
		try{
			Human human = Human.createInstance("Alexey");
			System.out.println("Human's name is '" + human.name + "'");
			int i = 12;
		}catch(IllegalHumanNameException exception){
			System.out.println("Human creation error");
		}catch(Exception e){
			System.out.println("ERROR!");
		}finally{
			System.out.println("FI!!" + System.lineSeparator() + "NAL!" + System.lineSeparator() + "LY!!");
		}
		
		try{
			Human human = Human.createInstance(122);
			System.out.println("Human's name is '" + human.name + "'");
			int i = 12;
		}catch(IllegalHumanNameException | IllegalMoneyException exception){
			System.out.println("Human creation error");
		}catch(Exception e){
			System.out.println("ERROR!");
		}finally{
			System.out.println("FI!!" + System.lineSeparator() + "NAL!" + System.lineSeparator() + "LY!!");
		}
		
		try{
			Human human = Human.createInstance(null);
			System.out.println("Human's name is '" + human.name + "'");
			int i = 12;
		}catch(IllegalHumanNameException exception){
			System.out.println("Human creation error");
		}catch(Exception e){
			System.out.println("ERROR!");
		}finally{
			System.out.println("FI!!" + System.lineSeparator() + "NAL!" + System.lineSeparator() + "LY!!");
		}
		
	}

}
