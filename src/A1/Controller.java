package A1;

import java.awt.Polygon;

import A1.Threads.Display;
import A1.Threads.Triangle;

public class Controller {
	
	private GUI gui;
	
	public static final int DISPLAY = 2;
	public static final int TRIANGLE = 3;
	
	private Display threadDisplay;
	private Triangle threadTriangle;
	
	public Controller() {
		gui = new GUI(this);
		gui.Start();
		initThreads();
	}
	
	private void initThreads() {
		threadDisplay = new Display(this);
		threadTriangle = new Triangle(this);

	}
	
	public void start(int threadType) {
		switch (threadType) {
		case DISPLAY:
			threadDisplay.play();
			break;
		case TRIANGLE:
			threadTriangle.play();
			break;
		}
	}
	
	public void stop(int threadType) {
		switch (threadType) {
		case DISPLAY:
			threadDisplay.pause();
			break;
		case TRIANGLE:
			threadTriangle.pause();
			break;
		}
	}

	public void setDisplayText(String txt, int x, int y) {
		gui.setDisplayText(txt,x,y);
	}
	
	public void drawTriangle(Polygon p) {
		gui.drawTriangle(p);
	}
}
