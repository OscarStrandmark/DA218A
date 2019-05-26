
package A2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class GUI {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code
	 */
	public static final long SLEEP = 250;
	
	private JFrame frame; // The Main window
	private JLabel lblTrans; // The transmitted text
	private JLabel lblRec; // The received text
	private JRadioButton bSync; // The sync radiobutton
	private JRadioButton bAsync; // The async radiobutton
	private JTextField txtTrans; // The input field for string to transfer
	private JButton btnRun; // The run button
	private JButton btnClear; // The clear buttonsy
	private JPanel pnlRes; // The colored result area
	private JLabel lblStatus; // The status of the transmission
	private JTextArea listW; // The write logger pane
	private JTextArea listR; // The read logger pane

	private Writer w;
	private Reader r;
	
	private String written;
	private String recieved;
	
	private GUI thisClass = this;
	/**
	 * Constructor
	 */
	public GUI() {
		Start();
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Concurrent Read/Write");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First, create the static components
		// First the 4 static texts
		JLabel lab1 = new JLabel("Writer Thread Logger");
		lab1.setBounds(18, 29, 128, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Reader Thread Logger");
		lab2.setBounds(388, 29, 128, 13);
		frame.add(lab2);
		JLabel lab3 = new JLabel("Transmitted:");
		lab3.setBounds(13, 394, 100, 13);
		frame.add(lab3);
		JLabel lab4 = new JLabel("Received:");
		lab4.setBounds(383, 394, 100, 13);
		frame.add(lab4);
		// Then add the two lists (of string) for logging transfer
		listW = new JTextArea();
		listW.setBounds(13, 45, 197, 342);
		listW.setBorder(BorderFactory.createLineBorder(Color.black));
		listW.setEditable(false);
		frame.add(listW);
		listR = new JTextArea();
		listR.setBounds(386, 45, 183, 342);
		listR.setBorder(BorderFactory.createLineBorder(Color.black));
		listR.setEditable(false);
		frame.add(listR);
		// Next the panel that holds the "running" part
		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
		pnlTest.setBounds(220, 45, 155, 342);
		pnlTest.setLayout(null);
		frame.add(pnlTest);
		lblTrans = new JLabel("Transmitted string goes here"); // Replace with sent string
		lblTrans.setBounds(13, 415, 200, 13);
		frame.add(lblTrans);
		lblRec = new JLabel("Received string goes here"); // Replace with received string
		lblRec.setBounds(383, 415, 200, 13);
		frame.add(lblRec);

		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Syncronous Mode", false);
		bSync.setBounds(8, 37, 131, 17);
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Not-syncronous Mode", true);
		bAsync.setBounds(8, 61, 141, 17);
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);
		// then the label and textbox to input string to transfer
		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13);
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		pnlTest.add(txtTrans);
		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);
		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.GREEN);
		pnlTest.add(pnlRes);
		// also to this text
		lblStatus = new JLabel("Staus goes here");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);
		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 303, 75, 23);
		btnClear.setEnabled(false);
		pnlTest.add(btnClear);
		
		//Listener for buttons
		ButtonListener listener = new ButtonListener();
		btnRun.addActionListener(listener);
		btnClear.addActionListener(listener);
	}


	public void appendRead(char c) {
		listR.setText(listR.getText() + "Read character: " + c + "\n");
		recieved += c;
		lblRec.setText(recieved);
	}
	
	public void appendWritten(char c) {
		listW.setText(listW.getText() + "Written character: " + c + "\n");
		written += c;
		lblTrans.setText(written);
	}

	public void readWait() {
		listR.setText(listR.getText() + "No character to read, reader waiting" + "\n");
	}
	
	public void writeWait() {
		listW.setText(listW.getText() + "No character to read, writer waiting" + "\n");
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnRun) {
				
				//Disable buttons while running
				btnRun.setEnabled(false);
				btnClear.setEnabled(false);
				
				//Get string and create buffer
				String text = txtTrans.getText();
				CharacterBuffer buffer = new CharacterBuffer(thisClass);
				
				//Clear old text.
				listW.setText("");
				listR.setText("");
				lblTrans.setText("");
				lblRec.setText("");
				
				//Set progress indicator to "IN PROGRESS"
				pnlRes.setBackground(Color.YELLOW);
				lblStatus.setText("IN PROGRESS");
				
				//Reset strings
				written = "";
				recieved = "";
				
				//Create and run the Reader & Writer
				if(bSync.isSelected()) { //Synchonized
					w = new Writer(thisClass,buffer,text.length(),true,text);
					r = new Reader(thisClass,buffer,text.length(),true);
					w.start();
					r.start();
				} else { //Not synchronized
					w = new Writer(thisClass,buffer,text.length(),false,text);
					r = new Reader(thisClass,buffer,text.length(),false);
					w.start();
					r.start();
				}
			}

			else
			
			if (e.getSource() == btnClear) {
				listW.setText("");
				listR.setText("");
				lblTrans.setText("");
				lblRec.setText("");
				
				btnRun.setEnabled(true);
				btnClear.setEnabled(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}

	//Check if written and recieved string is same.
	public void check() {
		if(written.equals(recieved)) {
			pnlRes.setBackground(Color.GREEN);
			lblStatus.setText("SUCCESS");
		} else {
			pnlRes.setBackground(Color.RED);
			lblStatus.setText("FAILURE");
		}
		btnClear.setEnabled(true);
		System.out.println("W:" + written);
		System.out.println("R:" + recieved);
	}
}
