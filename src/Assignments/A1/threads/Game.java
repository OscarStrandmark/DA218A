package Assignments.A1.threads;

import Assignments.A1.Controller;

public class Game extends Thread {

	private boolean running = false;
	
	private Controller controller;
	
	
	public Game(Controller c) {
		this.controller = c;
	}
	
	public void play() {
		if(running == false) {
			running = true;
		}
	}
	
	public void pause() {
		if(running == true) {
			running = false;
		}
	}
	

	public void run() {
		while(true) {			
			while(running) {
				

			}
		}
	}
}
