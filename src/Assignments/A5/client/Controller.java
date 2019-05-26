package Assignments.A5.client;

public class Controller {
	
	private Connection conn;
	private GUIChatClient gui;
	
	public Controller() {
		conn = new Connection(this);
		gui = new GUIChatClient(this);
		System.out.println("client started");
	}

	public void appendMessageText(String message) {
		gui.appendText(message);
	}

	public void disconnect() {
		conn.disconnect();
	}
	public void sendMessage(String text) {
		System.out.println("");
		conn.sendMessage(text);
	}
}
