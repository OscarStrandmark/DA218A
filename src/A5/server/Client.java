package A5.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import A5.Disconnect;
import A5.Message;

public class Client implements Runnable {

	private Server server;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Client thisClass = this;
	
	private boolean alive = true;
	
	public Client(Server server, Socket socket) {
		this.server = server;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeTo(Object obj) {
		try {
			oos.writeObject(obj);
			oos.flush();
			oos.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(alive) {
			try {
				Object obj = ois.readObject();
				
				if(obj instanceof Message) {
					server.message((Message)obj);
				}
				
				if(obj instanceof Disconnect) {
					alive = false;
					server.remove(thisClass);
					writeTo(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
