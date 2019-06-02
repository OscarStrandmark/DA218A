package A4;

public class BoundedBuffer {

	private String[] storage;
	private Status[] statusArr;

	private int size;

	private int writePos = 0;
	private int checkPos = 0;
	private int readPos = 0;
	
	private enum Status {
		Empty, Checked, New
	}

	public BoundedBuffer(int size) {
		this.size = size;
		
		storage = new String[size];
		statusArr = new Status[size];
		
		for (int i = 0; i < statusArr.length; i++) {
			statusArr[i] = Status.Empty;
		}
	}
	
	//TODO: Skriv om alla metoder så dem följer mönstret nedan;
	//TODO: Ta bort for-loop i trådar. Dem förstör allting
	/*
	 * while(status är inte rätt){
	 * 	wait();
	 * } 
	 * gör saken
	 * 
	 */

	public synchronized boolean write(String s) {
		boolean ret = false;
		try {
			if (statusArr[writePos] == Status.Empty) {
				storage[writePos] = s;
				statusArr[writePos] = Status.New;
				writePos = ((writePos + 1) % size);
				ret = true;
			} else {
				wait();
			}
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public synchronized void check(String find, String replace) {
		try {
			if(statusArr[checkPos] == Status.New) {
				if(storage[checkPos].contains(find)) {
					storage[checkPos] = storage[checkPos].replace(find, replace);
				}
				statusArr[checkPos] = Status.Checked;
				checkPos = ((checkPos + 1) % size);
			} else {
				wait();
			}
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String read() {
		String s = null;
		try {
			if(statusArr[readPos] == Status.Checked) {
				s = storage[readPos];
				statusArr[readPos] = Status.Empty;
				readPos = ((readPos + 1) % size);
			} else {
				wait();
			}
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
