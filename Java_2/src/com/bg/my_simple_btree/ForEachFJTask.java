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
		
		
		if(tree.getLeftChild() != null){
			ForEachFJTask<T> left = new ForEachFJTask<T>(tree.getLeftChild(), process);
			left.fork();
			left.join();
		}
		
		if(tree.getRightChild() != null){
			ForEachFJTask<T> right = new ForEachFJTask<T>(tree.getLeftChild(), process);
			right.fork();
			right.join();
		}
		//System.out.println(tree);
		process.process(tree.getValue());
		
		return tree.getValue();
	}
	
}
	
