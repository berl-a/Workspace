package com.bg.my_simple_btree;


public class Main {
	
	private static final int NUMBER_OF_NUMBERS = 10000;
	static BTree<Integer> tree = new BTree<>(0);
	
	public static void main(String[] args) {
		
		TreeCompleter<Integer> completer = new TreeCompleter<>(tree);
		completer.completeWithRandomNumbers(NUMBER_OF_NUMBERS, false);
		tree.forEachFJ(new MyProcess<Integer>());
	}

}
