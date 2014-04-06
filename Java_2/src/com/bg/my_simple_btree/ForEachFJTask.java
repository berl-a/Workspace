package com.bg.my_simple_btree;


import java.util.concurrent.RecursiveTask;

public class ForEachFJTask<T extends Comparable<T>> extends RecursiveTask<T> {

	private static final long serialVersionUID = 1L;

	//It doesn't work((

	ForEachFJTask(IBTree<T> tree, com.bg.my_simple_btree.IBTree.Process<T> process){
		this.tree = tree;
		this.process = (MyProcess<T>) process;
	}
	
	private final IBTree<T> tree;
	private final MyProcess<T> process;

	@Override
	protected T compute() {
		
		
		ForEachFJTask<T> left;
		ForEachFJTask<T> right;
		
		if(tree.getLeftChild() != null){
			System.out.println("Go to left - " + tree.getLeftChild().getValue());
			left = new ForEachFJTask<T>(tree.getLeftChild(), process);
			left.fork();
			left.join();
		}
		
		if(tree.getRightChild() != null){
			System.out.println("Go to right - " + tree.getRightChild().getValue());
			right = new ForEachFJTask<T>(tree.getLeftChild(), process);
			right.fork();
			right.join();
		}
		
		process.process(tree.getValue());
		
		return tree.getValue();
	}
	
}
	
