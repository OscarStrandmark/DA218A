package A4;

public class Modifier extends Thread {

	private String replace;
	private String find;
	
	private Controller controller;
	
	private BoundedBuffer buffer;
	private int size;
	
	public Modifier(BoundedBuffer buffer, Controller controller, int size, String find, String replace) {
		
		this.controller = controller;
		
		this.replace = replace;
		this.find = find;
		
		this.buffer = buffer;
		this.size = size;
		start();
	}
	
	public void run() {
		for (int i = 0; i < size; i++) {
			buffer.check2(find, replace);
		}
	}
}
