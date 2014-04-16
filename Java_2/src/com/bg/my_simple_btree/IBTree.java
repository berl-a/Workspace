package com.bg.my_simple_btree;

import java.util.concurrent.atomic.AtomicInteger;

public interface IBTree<T extends Comparable<T>> {
	
	AtomicInteger count = null;

	public IBTree<T> getLeftChild();
	
	public IBTree<T> getRightChild();
	
	
	public int getCount();
	
	public T getValue();
	
	public void forEach(final Process<T> p, IBTree<T> tree);
	public void forEachFJ(final Process<T> t);
	
	public void add(T value);   
	
	public IBTree<T> find(T value);
	public IBTree<T> findFJ(T value);
	
	public interface Process<T extends Comparable<T>>{

		public void process(IBTree<T> tree);
	}
	
}
