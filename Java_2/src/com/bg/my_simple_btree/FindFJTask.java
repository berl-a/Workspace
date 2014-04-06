package com.bg.my_simple_btree;

import java.util.concurrent.RecursiveTask;

public class FindFJTask<T extends Comparable<T>> extends RecursiveTask<IBTree<T>>{
	
	private static final long serialVersionUID = 1L;


	FindFJTask(IBTree<T> tree, T searchValue){
		this.tree = tree;
		this.searchValue = searchValue;
	}
	
	private final T searchValue;
	
	private final IBTree<T> tree;
	
	
	@Override
	protected IBTree<T> compute() {
		
		int compResult = searchValue.compareTo(tree.getValue());
		
		FindFJTask<T> deeper;
		
		if(compResult < 0){
			deeper = new FindFJTask<T>(tree.getLeftChild(), searchValue);
		}else if(compResult > 0){
			deeper = new FindFJTask<T>(tree.getRightChild(), searchValue);
		}else{
			return tree;
		}
		
		deeper.fork();
		return deeper.join();
		
	}

	
}
