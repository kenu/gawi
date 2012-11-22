package net.okjsp.cf;

import java.util.HashMap;

public class MapTest {
	public static void main(String[] args) {
		HashMap<String, Person> map = new HashMap<String, Person>();
		map.put("태연", new Person(15, "태연"));
		map.put("태연", new Person(15, "태연2"));
		map.put("서현", new Person(14, "서현"));
		map.put("제시카", new Person(15, "제시카"));
		
		System.out.println("size : "+map.size());
		
		System.out.println(map.get("태연").getName());
		System.out.println(map.get("제시카").getAge());
	}
}
