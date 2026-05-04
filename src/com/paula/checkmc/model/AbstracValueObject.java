package com.paula.checkmc.model;

public abstract class AbstracValueObject {

	public AbstracValueObject() {
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
