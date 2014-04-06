package com.bg.my_simple_btree;

import java.util.Random;


public class Main {
	
	private static final int NUMBER_OF_NUMBERS = 3000000;
	static BTree<Integer> tree = new BTree<>(0);
	
	public static void main(String[] args) {
		
		
		TreeCompleter<Integer> completer = new TreeCompleter<>(tree);
		
		
		completer.completeWithRandomNumbers(NUMBER_OF_NUMBERS, false);
		
		//tree.forEach(new MyProcess<Integer>());
		
		long start = System.currentTimeMillis();
		
		int r = new Random().nextInt();
		
		try{
			IBTree<Integer> foundTree = tree.findFJ(r);
			System.out.println("Found result: " + foundTree);
		}catch(NullPointerException exception){
			System.out.println("Element not found");
		}
		
		System.out.println("Found for the " + ((double)(System.currentTimeMillis() - start) / 1000) + " secounds by cool search");
		
		start = System.currentTimeMillis();
		
		
		IBTree<Integer> foundTree = tree.find(r);
		if(foundTree != null)
			System.out.println("Found result: " + foundTree);
		else
			System.out.println("Element not found");
		
		
		System.out.println("Found for the " + ((double)(System.currentTimeMillis() - start) / 1000) + " secounds by not cool search");
		
	}

}
