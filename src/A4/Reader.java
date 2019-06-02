package A4;

public class Reader extends Thread {

	private Controller controller;
	private BoundedBuffer buffer;
	private String newText;
	private int size;
	
	public Reader(BoundedBuffer buffer, Controller controller, int size) {
		this.controller = controller;
		this.buffer = buffer;
		this.newText = "";
		this.size = size;
		start();
	}
	
	public void run() {
		for (int i = 0; i < size; i++) {
			newText += buffer.read() + "\n";
			controller.writeDestination(newText);
		}
	}
}
