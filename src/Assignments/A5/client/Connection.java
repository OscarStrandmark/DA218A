package Assignments.A5.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Assignments.A5.Buffer;
import Assignments.A5.Disconnect;
import Assignments.A5.Message;
import Assignments.A5.server.Server;

public class Connection {
	
	private Buffer<Object> buffer;
	private Controller controller;
	private Socket socket;
	
	private boolean alive = true;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private Sender sender;
	private Receiver receiver;
	public Connection(Controller controller) {
		this.controller = controller;
		this.buffer = new Buffer<Object>();
		
		try {
			socket = new Socket("localhost", Server.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		sender = new Sender();
		receiver = new Receiver();
		
	}

	public void disconnect() {
		buffer.put(new Disconnect());
	}
	
	public void sendMessage(String text) {
		buffer.put(new Message(text));
	}

	public void kill() {
		alive = false;
		receiver.interrupt();
		sender.interrupt();
		
	}
	private class Sender extends Thread {
		
		public Sender() {
			start();
		}

		public void run() {
			while(alive) {
				try {
					Object obj = buffer.get();
					oos.writeObject(obj);
					oos.flush();
					oos.reset();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class Receiver extends Thread {
		
		public Receiver() {
			start();
		}
		
		public void run() {
			while(alive) {
				try {
					Object obj =  ois.readObject();
					
					if(obj instanceof Message) {
						Message msg = (Message) obj;
						String txt = msg.getMessage();
						controller.appendMessageText(txt);
					}
					
					else
						
					if(obj instanceof Disconnect) {
						//die :(
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
