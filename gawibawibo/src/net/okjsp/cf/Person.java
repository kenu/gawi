package net.okjsp.cf;

public class Person implements Comparable<Person> {
	private int age;
	private String name;

	public Person() {}
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Person o) {
		return this.age > o.age ? -1 : (this.age == o.age ? 0 : 1);
	}
}
