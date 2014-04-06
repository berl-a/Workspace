package com.bubblegum.class_object;

import javax.accessibility.*;

import javax.security.auth.callback.ConfirmationCallback;


public class Main {


	public static void main(String[] args) {
		
		//Просто так
		javax.swing.Timer timer;

		Student studentBob = new Student();
		studentBob.name = "Bob";
		studentBob.course.hours = 12;
		studentBob.course.name = "Java course";
		studentBob.sayHello();
		
		System.out.println();

		Student studentAlex = new Student();
		studentAlex.sayHello();
		
	}

}
