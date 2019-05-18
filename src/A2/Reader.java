package A2;

public class Reader extends Thread {

	private GUI gui;
	private CharacterBuffer buffer;
	private int size;
	private boolean synch;
	
	public Reader(GUI gui,CharacterBuffer buffer,int size, boolean synch) {
		this.gui = gui;
		this.buffer = buffer;
		this.size = size;
		this.synch = synch;
	}
	
	public void run() {
		char c;
		for (int i = 0; i < size; i++) {
			if(synch) { //Synchronized
				try {
					Thread.sleep(GUI.SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c = buffer.getSyncCharacter();
			} else { //Non-synchronized
				try {
					Thread.sleep(GUI.SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c = buffer.getCharacter();
			}
			gui.appendRead(c);
		}
		gui.check();
	}
}
