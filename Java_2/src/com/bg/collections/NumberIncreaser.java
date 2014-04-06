package com.bg.collections;

public class NumberIncreaser<T extends Number> {
	
	public double increace(T number){
		
		int i = number.intValue();
		i++; 
		return i;
	}
	
	

}
