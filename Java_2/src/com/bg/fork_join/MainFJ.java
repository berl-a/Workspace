package com.bg.fork_join;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MainFJ {

	
	private static final ForkJoinPool F_J_POOL = new ForkJoinPool();
	
	private static int[] array = new int[3000000];
	
	public static void main(String[] args) {
		
		Random r = new Random();
		
		for(int i = 0; i < array.length; i++){
			array[i] = r.nextInt();
		}
		
		long start = System.currentTimeMillis();
		MyForkJoinTask root = new MyForkJoinTask(array);
		F_J_POOL.submit(root);
		System.out.println("Result is " + root.join());
		System.out.println("Time is " + (double)(System.currentTimeMillis() - start)/1000 + " secounds");
	}

}
