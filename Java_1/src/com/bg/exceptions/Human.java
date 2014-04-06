package com.bg.exceptions;

import exceptions.IllegalHumanNameException;
import exceptions.IllegalMoneyException;

public class Human {
	
	public static String name;
	
	public static int money;
	
	private Human(String name) {
		Human.name = name;
	}

	private Human(String name, int money) {
		Human.name = name;
		Human.money = money;
		
	}

	public static Human createInstance(String name) throws IllegalHumanNameException {
		 if(name.length() < 2){
		    throw new IllegalHumanNameException(name);
		 }else{
			 return new Human(name);
		 }
	}
	
	public static Human createInstance(int money) throws IllegalHumanNameException, IllegalMoneyException {
		 if(money < 0){
		    throw new IllegalMoneyException();
		 }else{
			 return new Human("Default", money);
		 }
	}
}
