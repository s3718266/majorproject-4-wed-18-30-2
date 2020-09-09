package com.wed18302.majorproject.model;

public enum UserType {
	Customer(0),
	Worker(1),
	Administrator(2);
	
	UserType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	private final int value;
}
