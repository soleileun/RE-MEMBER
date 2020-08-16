package com.ssafy.test.model.dto;

public class Two<O,T> {
	private O first;
	private T second;
	public O getFirst() {
		return first;
	}
	public void setFirst(O first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	public Two(O first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	public Two() {
		super();
	}
	
	
}
