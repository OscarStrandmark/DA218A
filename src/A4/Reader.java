package A4;

public class Reader extends Thread {

	private Controller controller;
	private BoundedBuffer buffer;
	private String newText;
	private String replace;
	private int size;
	
	public Reader(BoundedBuffer buffer, Controller controller, int size, String replace) {
		this.controller = controller;
		this.replace = replace;
		this.buffer = buffer;
		this.newText = "";
		this.size = size;
		start();
	}
	
	public void run() {
		//Read from buffer
		for (int i = 0; i < size; i++) {
			newText += buffer.read() + "\n";
			controller.writeDestination(newText);
		}
		
		//Find amount of matches.
		String[] cnt = newText.split(" ");
		int count = 0;
		for(String s : cnt) {
			if(s.contains(replace)) {
				count++;
			}
		}
		
		//Write amount of matches in window.
		controller.setMatchingCount(count);
	}
}
