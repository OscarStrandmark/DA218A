package A4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;

public class Controller {

	private GUI gui;
	
	private BoundedBuffer buffer;
	private Writer w;
	private Modifier m;
	private Reader r;
	
	private JTextPane destination;
	
	private File file;
	
	public Controller() {
		gui = new GUI(this);
	}
	
	/**
	 * Read the target file into the program.
	 * 
	 * @param file The file to be read
	 */
	public void readFile(File file) {
		
		this.file = file;
		
		ArrayList<String> list = new ArrayList<String>();
		
		//Read contents to list
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			String read;
			while((read = br.readLine()) != null) {
				list.add(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Add newline markers
		String text = "";
		for(String s : list) {
			text += s + "\n";
		}
		
		//Set text in source.
		gui.setText(text);
	}

	//Start replacement and write it to destination.
	public void replace(String text, String find, String replace, JTextPane destination) {
		this.destination = destination;
				
		String[] splitText = text.split("\n"); //Separate by line.
		
		//Create buffer at size half of amount of strings
		buffer = new BoundedBuffer((int)splitText.length / 2);
		
		//Create the threads. 
		w = new Writer(buffer, splitText);
		m = new Modifier(buffer, this, splitText.length,find,replace);
		r = new Reader(buffer, this, splitText.length,replace);

	}

	//Write destination to file.
	public void writeToFile(String[] arr) {
		//Open a JFileChooser
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int opt = jfc.showSaveDialog(null);
		
		//When user presses OK
		if(opt == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			
			try {
				//Write to new file
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(String s : arr) {
					System.out.println(s);
					bw.write(s);
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//Write to destination window.
	public void writeDestination(String newText) {
		destination.setText(newText);
	}
	
	//Set amount of matches
	public void setMatchingCount(int i) {
		gui.setFoundCount(i);
	}
}
