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
	
	public synchronized void write(String s) {
		try {
			while(statusArr[writePos] != Status.Empty) { //while current status is not correct, wait for changes.
				wait();
			}
			//Write, set status and increase write position.
			storage[writePos] = s;
			statusArr[writePos] = Status.New;
			writePos = ((writePos + 1) % size);
			notifyAll(); //Signal that changes are made.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean check(String find, String replace) {
		boolean found = false;
		try {
			while(statusArr[checkPos] != Status.New) { //while current status is not correct, wait for changes.
				wait();
			}
			//If string contains text to find & replace, replace it.
			if(storage[checkPos].contains(find)) {
				storage[checkPos] = storage[checkPos].replace(find, replace);
				found = true;
			}
			statusArr[checkPos] = Status.Checked;
			checkPos = ((checkPos + 1) % size);
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
	
	public synchronized String read() {
		String s = null;
		try {
			while(statusArr[readPos] != Status.Checked) { //while current status is not correct, wait for changes.
				wait();
			}
			//Read, set status and increase read position.
			s = storage[readPos];
			statusArr[readPos] = Status.Empty;
			readPos = ((readPos + 1) % size);
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
