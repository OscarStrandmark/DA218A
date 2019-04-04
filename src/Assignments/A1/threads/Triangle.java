package Assignments.A1.threads;

import java.awt.Polygon;

import Assignments.A1.Controller;

public class Triangle extends Thread {

	private Controller controller;
	
	private boolean running = false;
	private long delay = 10; //How long in milliseconds the thread will sleep for each iteration. 

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
		
		Point m = new Point(100,100); //Creates a middle point in the middle of the panel;
		Point p1, p2, p3;
		Polygon p;
		
		int r = 50; //Radius
		int increase = 1; //How many degrees the triangle will rotate with every iteration.
		
		//Starting angles for points.
		double t1 = Math.toRadians(0);
		double t2 = Math.toRadians(120);
		double t3 = Math.toRadians(240);
		/*
		 * The triangle is drawn based on 3 points on a cricle. 
		 * The sum of angles in a circle is 360 deg, with this information we can pick three points on the circle to create a triangle.
		 * We have the middle point of the circle and the radius of it. With these we can calculate a point on the circle at a given angle.
		 * We use the formula below to calculate the coordinates of the point each iteration. 
		 * 
		 * Formula:
		 * x = r * cos(t) + h
		 * y = r * sin(t) + k
		 * (h,k) = point of circle center
		 * t = angle in radians
		 */
		while(true) {
			
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(running) {
				
				t1 += Math.toRadians(increase);
				t2 += Math.toRadians(increase);
				t3 += Math.toRadians(increase);
				
				t1 = t1 % Math.toRadians(360);
				t2 = t2 % Math.toRadians(360);
				t3 = t3 % Math.toRadians(360);
				
				//Calc new points
				p1 = new Point((int)(r * Math.cos(t1) + m.getX()),(int)(r * Math.sin(t1) + m.getY()));
				p2 = new Point((int)(r * Math.cos(t2) + m.getX()),(int)(r * Math.sin(t2) + m.getY()));
				p3 = new Point((int)(r * Math.cos(t3) + m.getX()),(int)(r * Math.sin(t3) + m.getY()));
				
				//Create polygon based on points for the draw-method to use. 
				int[] xpoints = {p1.getX(),p2.getX(),p3.getX()};
				int[] ypoints = {p1.getY(),p2.getY(),p3.getY()};			
				p = new Polygon(xpoints, ypoints, 3);
				
				//Calls draw method in the GUI.
				controller.drawTriangle(p);
			}
		}
		
		
	}
}
