package com.bg.my_simple_btree;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TreeCompleter<T extends Comparable<T>> {
	
	volatile IBTree<T> tree;
	
	private final int N_OF_THREADS = Runtime.getRuntime().availableProcessors();
	
	
	private final ExecutorService EXECUTOR = Executors.newFixedThreadPool(N_OF_THREADS);
	
	private final Random RANDOM = new Random();
	
	public TreeCompleter(IBTree<T> tree){
		this.tree = tree;
	}
	
	@SuppressWarnings("unchecked")
	
	public IBTree<T> completeWithRandomNumbers(int numberOfNumbers, boolean areFractional){
		
		if(areFractional){
			for(int i = 0 ; i < numberOfNumbers; i++){
				tree.add((T) new Double(RANDOM.nextDouble()));
			}
		}
		if(!areFractional){
			for(int i = 0 ; i < numberOfNumbers; i++){
				tree.add((T) new Integer(RANDOM.nextInt()));
			}
		}
		return tree;
	}
	
	public IBTree<T> completeWithRandomNumbersMultiThreaded(final int numberOfNumbers, boolean areFractional){
		
		ArrayList<Future> futures = new ArrayList<>();
		
		
		if(areFractional){
			for(int i = 0; i < numberOfNumbers; i++){
				futures.add(EXECUTOR.submit(new Thread(){
					@SuppressWarnings("unchecked")
					public void run(){
						tree.add((T) new Double(RANDOM.nextDouble()));
					}
				}));
			}
		}
		
		if(!areFractional){
			for(int i = 0; i < numberOfNumbers; i++){
				futures.add(EXECUTOR.submit(new Thread(){
					@SuppressWarnings("unchecked")
					public void run(){
						tree.add((T) new Integer(RANDOM.nextInt()));
					}
				}));
			}
		}
		
		for(Future future : futures){
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		EXECUTOR.shutdown();
		return tree;
				
	}

	public void completeWithNumber(Integer number) {
		tree.add((T) number);
		
	}

}
