package net.okjsp.cf;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySortString {
	public static void main(String[] args) {
		String str = "나,다,가";
		String[] arr = str.split(",");
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		str = Arrays.toString(arr);
		str = str.substring(1, str.length() - 1);
		System.out.println(str);
	}
}
