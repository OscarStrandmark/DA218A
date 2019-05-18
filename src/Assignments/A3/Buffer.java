package Assignments.A3;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Buffer<T> {
	
	private LinkedList<T> buffer;
	
	public Buffer() {
		buffer = new LinkedList<T>();
	}
	
	public void put(T e) {
		buffer.add(e);
	}
	
	public T get() {
		if(buffer.size() == 0) {
			return null;
		}
		return buffer.removeFirst();
	}

	public int size() {
		return buffer.size();
	}
}
