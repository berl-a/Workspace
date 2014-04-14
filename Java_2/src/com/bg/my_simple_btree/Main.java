package com.bg.my_simple_btree;

import java.util.ArrayList;
import java.util.List;



public class Main {
	
	private static final int NUMBER_OF_THREADS = 100;
	private static final int NUMBER_OF_NUMBERS = 900000;
	static BTree<Integer> tree = new BTree<>(0);
	
	public static void main(String[] args) {
		
		
		TreeCompleter<Integer> completer = new TreeCompleter<>(tree);
		
		
		completer.completeWithRandomNumbers(NUMBER_OF_NUMBERS, false);
		
		//tree.forEach(new MyProcess<Integer>());
		
		long start = System.currentTimeMillis();
		
		final int r = tree.getLeftChild().getRightChild().getRightChild().getRightChild().getLeftChild().getRightChild().getLeftChild().getRightChild().getLeftChild().getRightChild().getLeftChild().getValue();
		
		//Перевірити на декількох потоках
		
		List<Thread> threads = new ArrayList<Thread>(NUMBER_OF_THREADS);
		for(Thread t : threads){
			t = new Thread(new Runnable(){
				public void run(){
					try{
						IBTree<Integer> foundTree = tree.findFJ(r);
						System.out.println("Found result: " + foundTree);
					}catch(NullPointerException exception){
						System.out.println("Element not found");
					}
				}
			});
		}
		for(Thread t: threads){
			t.start();
		}
		for(Thread t: threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(Thread t: threads){
			t.stop();
		}
		threads.clear();
		System.out.println("Found for the " + ((double)(System.currentTimeMillis() - start)/1000) + " secounds by cool search");
		
		start = System.currentTimeMillis();
		
		for(Thread t : threads){
			t = new Thread(new Runnable(){
				public void run(){
					IBTree<Integer> foundTree = tree.find(r);
					if(foundTree != null)
						System.out.println("Found result: " + foundTree);
					else
						System.out.println("Element not found");
				}
			});
		}
		for(Thread t: threads){
			t.start();
		}
		for(Thread t: threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(Thread t: threads){
			t.stop();
		}
		threads.clear();
		
		System.out.println("Found for the " + ((double)(System.currentTimeMillis() - start)/1000) + " secounds by not cool search");
		
		//tree.forEachFJ(new MyProcess<Integer>());
		
		
	}

}
