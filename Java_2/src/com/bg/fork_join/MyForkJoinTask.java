package com.bg.fork_join;

import java.util.concurrent.RecursiveTask;


public class MyForkJoinTask extends RecursiveTask<Integer>{
	
	private static final int STOPID_ALGORYTM_N_OF_NUMBERS = 200;

	private final int[] array;
	
	private final int firstElem;
	
	private final int lastElem;
	
	MyForkJoinTask(int[] array, int firstElem, int lastElem){
		this.array = array;
		this.firstElem = firstElem;
		this.lastElem = lastElem;
	}
	
	MyForkJoinTask(int[] array){
		this(array, 0, array.length);
	}

	@Override
	protected Integer compute() {
		
		if(lastElem - firstElem == 1)
			return array[firstElem];
		
		if(lastElem - firstElem <= STOPID_ALGORYTM_N_OF_NUMBERS){
			
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < (lastElem - firstElem); i++){
				if(array[firstElem + i] > max)
					max = array[firstElem + i];
			}
			return max;
		}
		
		
		int midElem = (firstElem + lastElem) / 2;

		MyForkJoinTask left = new MyForkJoinTask(array, firstElem, midElem);
		MyForkJoinTask right = new MyForkJoinTask(array, midElem, lastElem);
		
		left.fork();
		right.fork();
		
		int leftResult = left.join();
		int rightResult = right.join();
		
		if(leftResult > rightResult)
			return leftResult;
		else
			return rightResult;
		
		
	}

	
	
}
