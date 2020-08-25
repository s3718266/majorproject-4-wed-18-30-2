package com.wed18302.majorproject.model;

public enum UserType {
	Customer(0),
	Administrator(1);
	
	UserType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	private final int value;
}
