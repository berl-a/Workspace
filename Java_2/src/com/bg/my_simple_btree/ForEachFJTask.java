package com.bg.my_simple_btree;

<<<<<<< HEAD
import java.util.concurrent.RecursiveTask;

public class ForEachFJTask<T extends Comparable<T>> extends RecursiveTask<T> {

	private static final long serialVersionUID = 1L;


	ForEachFJTask(IBTree<T> tree, Process process){
		this.tree = tree;
		this.process = process;
	}
	
	private final IBTree<T> tree;
	private final Process process;

	@Override
	protected T compute() {
		
		ForEachFJTask<T> left;
		ForEachFJTask<T> right;
		
		if(tree.getLeftChild() != null)
			left = new ForEachFJTask<T>(tree.getLeftChild());
		
		if(tree.getRightChild() != null)
			right = new ForEachFJTask<T>(tree.getLeftChild());
		
		process.process(tree.getValue());
		
		return tree.getValue();
	}
	
=======
public class ForEachFJTask {

>>>>>>> b22a175... orgCom
}
