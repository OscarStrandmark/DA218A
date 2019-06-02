package A3;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

public class Buffer<T> {
	
	private static final int MAX_SIZE = 50;
	
	private LinkedList<T> buffer;
	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore amount = new Semaphore(0, true);
	private Semaphore slotsLeft = new Semaphore(MAX_SIZE, true);
	
	public Buffer() {
		buffer = new LinkedList<T>();
	}
	
	public void put(T item) {
		try {
			//Counters
			amount.release();
			slotsLeft.acquire();
			//mutex get.
			mutex.acquire();
			buffer.add(item);
			mutex.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public T get() {
		T item = null;
		try {
			//counters
			amount.acquire();
			slotsLeft.release();
			//mutex get.
			mutex.acquire();
			item = buffer.removeFirst();
			mutex.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	public int size() {
		return buffer.size();
	}
}
