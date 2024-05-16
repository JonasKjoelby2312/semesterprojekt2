package model;

public class Dog {
	private int dogID;
	private String name;
	private String description;
	
	public Dog(int dogID, String name, String description) {
		this.dogID = dogID;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDogID() {
		return dogID;
	}

	public void setDogID(int dogID) {
		this.dogID = dogID;
	}
}
