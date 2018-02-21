package com.intuit2.task2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * I am creating my budget for the next month.I want to eliminate some items in order to cut down my budget.
 * You are a developer at Mint. In order to help me manage my personal finance better, you are giving me suggestions of what items I should remove from my budget. What you are given is:
 * Ex. - Name: “Backpack”, amount: $55.00
 *    - Name: “Monitor”, amount: $100.00
 *   - Name: “Water bottle”, amount: $10.00
 *  - Name: “Tent”, amount: $150.00
 * - Name: “Headphone”, amount: $123.00
 *  current total budget: $1200.00
 *    target total budget: $1000.00
 *    returning pair: “Backpack”, “Tent”
 *    If I only want to remove 2 items to lower my budget to target budget, is it possible? If so, which 2 items should I remove?
 */
public class Budget {
		
	public static void main(String[] args)  {
		int[] vals = new int[] {55, 100, 10, 150, 123};
		//running time is nlog(n) for sort
		 Arrays.sort(vals);
		Map<Integer, String> pairs = new HashMap<Integer, String>();
		pairs.put(55, "Backpack");
		pairs.put(100, "Monitor");
		pairs.put(10, "Water bottle");
		pairs.put(150, "Tent");
		pairs.put(123, "Headphone");
		int currentBudget = 1200;
		int targetBudget = 1000;
		int target = currentBudget- targetBudget;
		calcTwo(vals, pairs,target);
	}
	
	public static String[] calcTwo(int[] arr, Map<Integer, String> pairs, int target) {
		//running time is n for this method
		
		int low = 0;
		int high = arr.length - 1;
		int smallest = Integer.MAX_VALUE;
		int l = 0;
		int r = arr.length-1;
		while(low < high) {
			if( target > arr[low] + arr[high] ) {
				low++;
			}else {
				if(smallest > arr[low] + arr[high]) {
					l = low;
					r = high;
					smallest = arr[low] + arr[high];
				}
				high--;
			}
			
		}
		

        String fir = pairs.get(arr[l]);
        String sec = pairs.get(arr[r]);
        System.out.println( fir + " : " + arr[l]  + " ; "+ sec + " : "+arr[r]);
		return new String[] {fir,sec};
	}
	
	

}
