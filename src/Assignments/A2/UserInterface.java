package Assignments.A2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserInterface extends JFrame {

	private JList<String> listW;
	private JList<String> listR;
	
	private JPanel pnlLeft;
	private JPanel pnlMidd;
	private JPanel pnlRght;
	
	private JScrollPane spnlW;
	private JScrollPane spnlR;
	
	private JLabel lblW = new JLabel();
	private JLabel lblR = new JLabel();
	
	private JRadioButton btnSync;
	private JRadioButton btnNoSync;
	private ButtonGroup btnGroup;
	
	private JTextField theString;
	
	private JButton btnRun;
	private JButton btnClear;
	
	public UserInterface() {
		
		init();
		
	}
	
	private void init() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,3));
		setVisible(true);
		setBounds(1920, 0, 800, 600);
		
		btnSync = new JRadioButton("Synchronous");
		btnNoSync = new JRadioButton("Non-Synchronous");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(btnSync);
		btnGroup.add(btnNoSync);
		
		listW = new JList<String>();
		listR = new JList<String>();
		
		pnlLeft = new JPanel();
		pnlMidd = new JPanel();
		pnlRght = new JPanel();
		
		spnlW = new JScrollPane();
		spnlR = new JScrollPane();
		
		theString = new JTextField();
		theString.setColumns(20);
		theString.setSize(200, 50);
		
		spnlW.setViewportView(listW);
		spnlR.setViewportView(listR);
		
		btnRun = new JButton("Run");
		btnClear = new JButton("Clear");
		
		//LEFT
		pnlLeft.add(new JLabel("Writer Thread Logger"));
		pnlLeft.add(spnlW);
		pnlLeft.add(new JLabel("Transmitted:"));
		pnlLeft.add(lblW);
		add(pnlLeft);
		
		
		//MIDDLE
		pnlMidd.add(btnSync);
		pnlMidd.add(btnNoSync);
		pnlMidd.add(new JLabel("String to transfer"));
		pnlMidd.add(theString);
		pnlMidd.add(btnRun);
		pnlMidd.add(btnClear);
		add(pnlMidd);
		
		//RIGHT
		pnlRght.add(new JLabel("Reader Thread Logger"));
		pnlRght.add(spnlR);
		pnlRght.add(new JLabel("Transmitted:"));
		pnlRght.add(lblR);
		add(pnlRght);
		
		
		
		
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}