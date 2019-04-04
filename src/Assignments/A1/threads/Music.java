package Assignments.A1.threads;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Assignments.A1.Controller;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music extends Thread {

	private Controller controller;

	private boolean running = false;
		
	private File file;
	
	public Music(Controller c) {
		this.controller = c;
	}
	
	public void play() {
		if(running == false) {
			running = true;
			start();
		}
	}
	public void pause() {
		if(running == true) {
			running = false;
		}
	}
	
	public void open() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3-files", "mp3");
		jfc.setFileFilter(filter);
		int btn = jfc.showOpenDialog(null);
		if(btn == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			controller.setLoadedFile(file.getPath());
		}
	}
	
	public void run() {
		
	}
}

