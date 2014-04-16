package com.bg.my_simple_btree;


import java.util.concurrent.RecursiveAction;

public class ForEachFJAction<T extends Comparable<T>> extends RecursiveAction {

	
	ForEachFJAction(IBTree<T> tree, com.bg.my_simple_btree.IBTree.Process<T> process){
		this.tree = tree;
		this.process = (MyProcess<T>) process;
	}
	
	private final IBTree<T> tree;
	private final MyProcess<T> process;

	@Override
	protected void compute() {
		
		if(tree.getLeftChild() != null){
			ForEachFJAction<T> left = new ForEachFJAction<T>(tree.getLeftChild(), process);
			left.fork();
			left.join();
		}
		
		if(tree.getRightChild() != null){
			ForEachFJAction<T> right = new ForEachFJAction<T>(tree.getRightChild(), process);
			right.fork();
			right.join();
		}
		
		process.process(tree);
		
		
	}
	
}
	
