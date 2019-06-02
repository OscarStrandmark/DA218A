package A4;

public class Writer extends Thread {

	private BoundedBuffer buffer;
	private String[] textToWrite;
	
	public Writer(BoundedBuffer buffer, String[] text ) {
		this.buffer = buffer;
		this.textToWrite = text;
		start();
	}
	
	public void run() {
		for(String s : textToWrite) {
			buffer.write(s);
		}
	}
}
