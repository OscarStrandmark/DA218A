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
	
	public void readFile(File file) {
		
		this.file = file;
		
		ArrayList<String> list = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			String read;
			while((read = br.readLine()) != null) {
				list.add(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String text = "";
		for(String s : list) {
			text += s + "\n";
		}
		gui.setText(text);
	}

	public void replace(String text, String find, String replace, JTextPane destination) {
		this.destination = destination;
				
		String[] splitText = text.split("\n"); //Separate by line.
				
		buffer = new BoundedBuffer((int)splitText.length / 2);
		
		w = new Writer(buffer, splitText);
		m = new Modifier(buffer, this, splitText.length,find,replace);
		r = new Reader(buffer, this, splitText.length);

	}

	public void writeToFile(String[] arr) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int opt = jfc.showSaveDialog(null);
		if(opt == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			
			try {
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
	
	public void writeDestination(String newText) {
		destination.setText(newText);
	}
}
