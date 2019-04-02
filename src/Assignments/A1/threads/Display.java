package Assignments.A1.threads;

import java.util.Random;

import Assignments.A1.Controller;

public class Display extends Thread {

	private Controller controller;
	
	private boolean running = false;
	private long delay = 750; //How long in milliseconds the thread will sleep for each iteration. 

	public Display(Controller c) {
		this.controller = c;
		start();
	}
	
	public void play() {
		if(running == false) {
			running = true;
		}
	}
	
	public void pause() {
		if(running == true) {
			running = false;
			controller.setDisplayText("", 0, 0);
		}
	}
	
	public void run() {
		Random rand = new Random();
		int x;
		int y;
		while(true) {

			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(running) {
				x = rand.nextInt(141) + 10;
				y = rand.nextInt(191) + 10;
				//Randomize text position
				controller.setDisplayText("Display text",x,y); //Display the text at the randomized position. 
			}
		}
	}
}
