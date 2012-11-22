package net.okjsp.cf;

import java.util.ArrayList;
import java.util.Collections;

public class ArraySortObject {
	public static void main(String[] args) {
		Person person1 = new Person(15, "서현");
		Person person2 = new Person(17, "효연");
		Person person3 = new Person(17, "제시카");
		Person person4 = new Person(16, "태연");
		
		ArrayList<Person> list = new ArrayList<Person>();
		
		list.add(person1);
		list.add(person2);
		list.add(person3);
		list.add(person4);
		
		for (Person person : list) {
			System.out.println(person.getAge() + ":" + person.getName());
		}
		
		Collections.sort(list);
		System.out.println("sorted-->");
		
		for (Person person : list) {
			System.out.println(person.getAge() + ":" + person.getName());
		}
		
		
	}
}
