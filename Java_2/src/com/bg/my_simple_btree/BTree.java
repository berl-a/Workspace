package com.bg.my_simple_btree;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;


public class BTree<T extends Comparable<T>> implements IBTree<T>{
	
	
	BTree(T value){
		this.value = value;
	}
	
	private final T value;

	private IBTree<T> leftChild = null;
	private IBTree<T> rightChild = null;
		
	private AtomicInteger count = new AtomicInteger(1);
	
	public T getValue(){
		return this.value;
	}
	
	public int getCount(){
		return count.get();
	}
	
	private synchronized void setLeftChild(IBTree<T> child){
		if(!child.equals(null)){
			this.leftChild = child;
		}
	}
	
	private synchronized void setRightChild(IBTree<T> child){
		if(!child.equals(null)){
			this.rightChild = child;
		}
	}
	
	public synchronized IBTree<T> getLeftChild(){
		return this.leftChild;
	}
	
	public synchronized IBTree<T> getRightChild(){
		return this.rightChild;
	}
	
	
	public synchronized void add(T newValue){
		
		int compareResult = newValue.compareTo(getValue());
		
		if(compareResult == 0)
			count.incrementAndGet();
		else if(compareResult > 0)
			addToRight(newValue);
		else
			addToLeft(newValue);
		
	}


	public void addToLeft(T newValue) {
		if(getLeftChild() == null)
			setLeftChild(new BTree<T>(newValue));
		else
			getLeftChild().add(newValue);
		
	}

	public void addToRight(T newValue) {
		if(getRightChild() == null)
			setRightChild(new BTree<T>(newValue));
		else
			getRightChild().add(newValue);
		
	}
	
	IBTree<T> foundTree = null;
	
	public IBTree<T> find(T findValue){
		foundTree = null;
				
		int compareResult = findValue.compareTo(getValue());
		
		if(compareResult == 0){
			return this;
		}else if(compareResult > 0){
			if(getRightChild() != null)
				foundTree = getRightChild().find(findValue);
		}else{
			if(getLeftChild() != null)
				foundTree = getLeftChild().find(findValue);
		}
		
		if(foundTree != null)
			return foundTree;
		
		return null;
	}	

	private static final ForkJoinPool F_J_POOL = new ForkJoinPool();
	
	@Override
	public IBTree<T> findFJ(T value) {
		
		FindFJTask<T> rootTask = new FindFJTask<T>(this, value);
		F_J_POOL.submit(rootTask);
		return rootTask.join();
	}
	
	public void forEach(final Process<T> process, final IBTree<T> tree){
		
		new Thread(){
			public void run(){
				new MyProcess<T>().process(tree);
			}
		}.start();
		
		if(getLeftChild() != null)
			getLeftChild().forEach(new MyProcess<T>(), getLeftChild());
		
		if(getRightChild() != null)
			getRightChild().forEach(new MyProcess<T>(), getRightChild());
		
	}
	
	public static Integer sumOfValues;
	
	
	@Override
	public void forEachFJ(final Process<T> process) {

		ForkJoinPool fjp = new ForkJoinPool();
		ForEachFJAction<T> fjTask = new ForEachFJAction<T>(this, process);
		fjp.submit(fjTask);
		fjTask.join();
	
	}

	
}