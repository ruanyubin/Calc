package com.ruanyb.calc;

public class Stack<E> {
	
	private Object[] data ;
	
	private Integer top = -1;
	
	private Integer maxSize = 10;
	
	
	public Stack() {
		data = new Object[maxSize];
	}
	
	public Stack(int maxSize) {
		if(maxSize < 0) {
			throw new RuntimeException("初始化栈大小不能小于0");
		}
		this.maxSize = maxSize;
		data = new Object[maxSize];
	}
	
	public void push(E e) {
		top++;
		data[top] = e;
	}
	
	public E pop() {
		@SuppressWarnings("unchecked")
		E e = (E)data[top];
		top--;
		return e;
	}
	
	@SuppressWarnings("unchecked")
	public E getTop() {
		return (E) data[top];
	}
	
	public Boolean isEmpty() {
		return top == -1 ? true:false;
	}
}
