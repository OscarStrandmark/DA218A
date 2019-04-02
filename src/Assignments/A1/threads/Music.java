package Assignments.A1.threads;

import Assignments.A1.Controller;

public class Music extends Thread {

	private Controller controller;

	private boolean running = false;
	
	private long delay = 20; //How long the thread will sleep for each iteration. 
	
	public Music(Controller c) {
		this.controller = c;
	}
	
	public void play() {
		running = true;
	}
	public void pause() {
		running = false;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(running) {
				
			}
		}
	}
}
