package net.okjsp.cf;

import java.util.Arrays;

public class ArraySortInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] ages= {25,32,19,27,24};
		for (int i = 0; i < ages.length; i++) {
			System.out.print(ages[i] + " ");
		}
		
		Arrays.sort(ages);
		System.out.println("\nsorted---->");

		for (int i = 0; i < ages.length; i++) {
			System.out.print(ages[i] + " ");
		}
	}

}
