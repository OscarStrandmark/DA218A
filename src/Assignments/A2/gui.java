package Assignments.A2;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class gui extends JFrame {

	private JList<String> listW;
	private JList<String> listR;
	
	private JScrollPane spnlLeft;
	private JScrollPane spnlRght;
	
	public gui() {
		
		init();
	}
	
	private void init() {
		
		setLayout(new GridLayout(1,3));
		setVisible(true);
		
		listW = new JList<String>();
		listR = new JList<String>();
	}
}