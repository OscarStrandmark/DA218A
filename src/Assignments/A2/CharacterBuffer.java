package Assignments.A2;

public class CharacterBuffer {

	private char character;
	
	private boolean HasCharacter = false;
	private GUI gui;
	
	public CharacterBuffer(GUI gui) {
		this.gui = gui;
	}
	
	public synchronized char getSyncCharacter() {
		if(!HasCharacter) {
			try {
				gui.readWait();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		HasCharacter = false;
		notifyAll();
		return character;
	}
	
	public synchronized void setSyncCharacter(char character) {
		if(HasCharacter) {
			try {
				gui.writeWait();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.character = character;
		HasCharacter = true;
		notifyAll();
	}
	
	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}
	
}
