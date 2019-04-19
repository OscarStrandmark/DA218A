package Assignments.A2;

public class Writer extends Thread {
	
	private GUI gui;
	private CharacterBuffer buffer;
	private int size;
	private boolean synch;
	private String text;
	
	public Writer(GUI gui,CharacterBuffer buffer,int size, boolean synch, String text) {
		this.gui = gui;
		this.buffer = buffer;
		this.size = size;
		this.synch = synch;
		this.text = text;
	}
	
	public void run() {
		for (int i = 0; i < size; i++) {
			if(synch) {
				try {
					Thread.sleep(GUI.SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				buffer.setSyncCharacter(text.charAt(i));
			} else {
				try {
					Thread.sleep(GUI.SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				buffer.setCharacter(text.charAt(i));
			}
			gui.appendWritten(text.charAt(i));
		}
	}
}
