package com.cjburkey.configedit;

public class Config<T> {
	
	private String name;
	private T value;
	
	public Config(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public T getValue() {
		return value;
	}
	
}