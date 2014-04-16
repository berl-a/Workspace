package com.bg.my_simple_btree;

public class MyProcess<T extends Comparable<T>> implements IBTree.Process<T> {

	
	@Override
	public void process(IBTree<T> tree) {
		
		System.out.println(tree.getLeftChild() + " " + tree.getRightChild());
	}

	
}
