package com.bg.lection6;

public class Human {

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}
	
	public String getMax() {
		return "Max";
	}

	public void setName(String name) {
		if((name.length() >=3) && (name.length() <=20)){
			this.name = name;
		}else{
			System.out.println("Name setting error!");
		}
	}
	
	public void setName() {
		name = "Default";
	}
	
	public void setName(String name, int fromWhatCharacter) {
		if((name.substring(fromWhatCharacter).length() >=3) && (name.substring(fromWhatCharacter).length() <=20)){
			this.name = name.substring(fromWhatCharacter);
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if((age > 0) && (age <= 150)){
			this.age = age;;
		}else{
			System.out.println("Age setting error!");
			age = 16;
		}
	}
	

}
