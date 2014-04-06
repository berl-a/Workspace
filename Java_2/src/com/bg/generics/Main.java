package com.bg.generics;

public class Main {

	
	public static void main(String[] args) {
		
		MyNumber<Long> longNumber= new MyNumber<>(12L);
		MyNumber<Double> doubleNumber= new MyNumber<>(132.0);
		
		System.out.println(longNumber.getDouble());
		System.out.println(longNumber.getInteger());
		System.out.println(longNumber.sum(longNumber, doubleNumber));
		System.out.println(longNumber.sum(longNumber, longNumber));
		System.out.println(longNumber.getType());

	}

}
