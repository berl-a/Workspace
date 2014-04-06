package com.bg.my_simple_btree;

public class MyProcess<T> implements IBTree.Process<T>{

	
	@Override
	public void process(T value) {
		
		System.out.println(value);
	}
	
}
