package Assignments.A1;

import java.awt.Polygon;

import Assignments.A1.threads.Display;
import Assignments.A1.threads.Music;

public class Controller {
	
	private GUI gui;
	
	public static final int MUSIC = 1;
	public static final int DISPLAY = 2;
	public static final int TRIANGLE = 3;
	public static final int GAME = 4;
	
	private Music threadMusic;
	private Display threadDisplay;
	private Class threadTriangle; //TODO: ÄNDRA TILL RÄTT KLASS
	private Class threadGame; //TODO: ÄNDRA TILL RÄTT KLASS
	
	public Controller() {
		gui = new GUI(this);
		gui.Start();
		
		initThreads();
	}
	
	private void initThreads() {
		threadDisplay = new Display(this);
	}
	
	public void start(int threadType) {
		switch (threadType) {
		case 1:
			
			break;
		case 2:
			threadDisplay.play();
			break;
		case 3:
			
			break;
		case 4: 
			
			break;
		}
	}
	
	public void stop(int threadType) {
		switch (threadType) {
		case 1:
			
			break;
		case 2:
			threadDisplay.pause();
			break;
		case 3:
			
			break;
		case 4: 
			
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
