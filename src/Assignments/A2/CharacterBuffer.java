package Assignments.A2;

public class CharacterBuffer {

	private char character;
	
	private boolean HasCharacter = false;

	public synchronized char getSyncCharacter() throws InterruptedException {
		if(!HasCharacter) {
			wait();
		}
		HasCharacter = false;
		return character;
	}
	
	public synchronized void setSyncCharacter(char character) {
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
