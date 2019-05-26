package A5.server;

import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import A5.Message;

public class Server extends Thread {

	public static final int PORT = 720;
	public static final int POOL_MAX = 20;
	
	private ExecutorService pool  = Executors.newFixedThreadPool(POOL_MAX);
	
	private ArrayList<Client> clientList = new ArrayList<Client>();
	
	private GUIChatServer gui;
	
	public Server() {
		this.gui = new GUIChatServer(this);
		start();
	}
	
	public void message(Message msg) {
		for(Client c : clientList) {
			c.writeTo(msg);
		}
		gui.append(msg.getMessage());
	}
	

	public void remove(Client c) {
		clientList.remove(c);
	}
	
	public void run() {
		try(ServerSocket ss = new ServerSocket(PORT)){
			System.out.println("server started");
			while(true) {
				try {
					System.out.println("awaiting connection");
					Socket socket = ss.accept();
					System.out.println("accepted connection");
					Client client = new Client(this,socket);
					clientList.add(client);
					pool.execute(client);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (BindException be) {
			JOptionPane.showMessageDialog(null, "Server already running");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
