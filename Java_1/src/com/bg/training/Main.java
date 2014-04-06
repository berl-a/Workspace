package com.bg.training;

public class Main {


	public static void main(String[] args) {
		
		int age = 12;
		String name = "b";
				
		try{
			Human ben = new Human();
			ben.setAge(age);
			ben.setName(name);
		}catch(AgeSettingException e){
			//System.out.println("Age setting exception!");
		}catch(NameSettingException e){
			//System.out.println("Name setting exception!");
		}

	}

}
