package com.bubblegum.class_object;

public class Student {

	public String name = "Default name";
	
	public int age = 15;
	
	public Course course = new Course();
	
	public void sayHello() {
		
		System.out.println("Hello!");
		System.out.println("My name is " + name);
		System.out.println("My age is " + age);
		course.aboutCourse();
	}
	
}
