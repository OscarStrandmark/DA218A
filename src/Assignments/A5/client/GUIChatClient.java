package Assignments.A5.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

/**
 * The GUI for assignment 5
 */
public class GUIChatClient {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code
	 */
	private JFrame frame; // The Main window
	private JTextField txt; // Input for text to send
	private JButton btnSend; // Send text in txt
	private JTextArea lstMsg; // The logger listbox

	private Controller controller;
	/**
	 * Constructor
	 */
	public GUIChatClient(Controller controller) {	
		this.controller = controller;
		Start();
		Listener l = new Listener();
		btnSend.addActionListener(l);
		frame.addWindowListener(l);
	}
	
	public void appendText(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				lstMsg.append(message + "\n");
			}
		});
	}
	
	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Client"); // Change to "Multi Chat Server" on server part and vice versa
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

	private class Listener implements ActionListener,WindowListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSend) {
				String s = txt.getText();
				if(s.length() > 0) {
					controller.sendMessage(s);
					txt.setText("");
				}
			}
		}

		public void windowClosing(WindowEvent e) {
			controller.disconnect();
		}

		public void windowOpened(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}
}