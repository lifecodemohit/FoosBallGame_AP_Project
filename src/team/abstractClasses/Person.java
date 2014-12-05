package team.abstractClasses;

import motion.MovingObject;

public abstract class Person extends MovingObject {

	String name;
	int age;
	String country;

	public Person(String name, int age, String country) {
		super();
		this.name = name;
		this.age = age;
		this.country = country;
	}

	public String getPName() {
		return name;
	}

	public void setPName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}