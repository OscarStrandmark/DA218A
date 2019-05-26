package Assignments.A5.server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Assignments.A5.Message;

/**
 * The GUI for assignment 5
 */
public class GUIChatServer {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code
	 */
	private JFrame frame; // The Main window
	private JTextField txt; // Input for text to send
	private JButton btnSend; // Send text in txt
	private JTextArea lstMsg; // The logger listbox

	private Server server;
	/**
	 * Constructor
	 */
	public GUIChatServer(Server server) {
		this.server = server;
		Start();
		btnSend.addActionListener(new Listener());
	}

	public void append(String s) {
		lstMsg.append(s + "\n");
	}
	
	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Server"); // Change to "Multi Chat Server" on server part and vice versa
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		txt = new JTextField();
		txt.setBounds(13, 13, 177, 23);
		frame.add(txt);
		btnSend = new JButton("Send");
		btnSend.setBounds(197, 13, 75, 23);
		frame.add(btnSend);
		lstMsg = new JTextArea();
		lstMsg.setEditable(false);
		JScrollPane pane = new JScrollPane(lstMsg);
		pane.setBounds(12, 51, 260, 199);
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(pane);
	}
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSend) {
				String s = txt.getText();
				if(s.length() > 0) {
					server.message(new Message(s));
					txt.setText("");
				}
			}
		}
	}
}