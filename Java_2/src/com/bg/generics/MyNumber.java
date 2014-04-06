package com.bg.generics;

public class MyNumber<T extends Number> {
	
	T value;
	
	MyNumber(T value){
		this.value = value;
	}


	public Double getDouble(){
		return value.doubleValue();
	}
	
	public Integer getInteger(){
		return value.intValue();
	}
	
	public Number sum(MyNumber<? extends Number> a, MyNumber<? extends Number> b){
		return (Number)(a.value.doubleValue() + b.value.doubleValue());
		
	}
	
	public String getType(){
		return value.getClass().getName();
	}

}
