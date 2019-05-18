package Assignments.A3;

import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	
	public void put(T obj) {
		buffer.add(obj);
	}
	
	public T get() {
		return buffer.removeFirst();
	}
	
	public int size() {
		return buffer.size();
	}
}