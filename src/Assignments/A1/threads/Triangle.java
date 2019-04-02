package Assignments.A1.threads;

import java.awt.Polygon;

import Assignments.A1.Controller;

public class Triangle extends Thread {

	private Controller controller;
	
	private boolean running = false;
	private long delay = 750; //How long in milliseconds the thread will sleep for each iteration. 

	public Triangle(Controller c) {
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
		}
	}

	@Override
	public void run() {
		Polygon p = new Polygon();
		
		Point m = new Point(100,100); //Creates a middle point in the middle of the panel;
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,0);
		Point p3 = new Point(0,0);
		
		
	}
}
