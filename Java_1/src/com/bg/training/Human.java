package com.bg.training;

public class Human {

	
	private int age;
	
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws AgeSettingException {
		if((age > 0) && (age <= 200)){
			this.age = age;
		}
		else{
			throw new AgeSettingException(age);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NameSettingException {
		if((name.length() >= 3) && (name.length() <= 20)){
			this.name = name;
		}else{
			throw new NameSettingException(name);
		}
	}
	

}
