package Assignments.A1;

import java.awt.Polygon;

import Assignments.A1.threads.Display;
import Assignments.A1.threads.Game;
import Assignments.A1.threads.Music;
import Assignments.A1.threads.Triangle;

public class Controller {
	
	private GUI gui;
	
	public static final int MUSIC = 1;
	public static final int DISPLAY = 2;
	public static final int TRIANGLE = 3;
	public static final int GAME = 4;
	
	private Music threadMusic;
	private Display threadDisplay;
	private Triangle threadTriangle;
	private Game threadGame;
	
	public Controller() {
		gui = new GUI(this);
		gui.Start();
		
		initThreads();
	}
	
	private void initThreads() {
		threadDisplay = new Display(this);
		threadTriangle = new Triangle(this);
		threadMusic = new Music(this);
		threadGame = new Game(this);
	}
	
	public void open() {
		threadMusic.open();
	}
	
	public void setLoadedFile(String s) {
		gui.setLoadedFile(s);
	}
	
	public void start(int threadType) {
		switch (threadType) {
		case 1:
			threadMusic.play();
			break;
		case 2:
			threadDisplay.play();
			break;
		case 3:
			threadTriangle.play();
			break;
		case 4: 
			threadGame.play();
			break;
		}
	}
	
	public void stop(int threadType) {
		switch (threadType) {
		case 1:
			threadMusic.pause();
			break;
		case 2:
			threadDisplay.pause();
			break;
		case 3:
			threadTriangle.pause();
			break;
		case 4: 
			threadGame.pause();
			break;
		}
	}

	public void setDisplayText(String txt, int x, int y) {
		gui.setDisplayText(txt,x,y);
	}
	
	public void drawTriangle(Polygon p) {
		gui.drawTriangle(p);
	}

	public int getDifficulty() {
		return gui.getDifficulty();
	}
}
