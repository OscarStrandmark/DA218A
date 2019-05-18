package A3;

import java.util.Random;

public class Producer extends Thread {

	private Controller controller;	
	private FoodItem[] items;
	
	private static final long SLEEP = 1000;
	
	private boolean producing;

	public Producer(Controller controller, String name, FoodItem[] items) {
		this.controller = controller;
		this.items = items;
		producing = false;
		start();
		setName(name);
	}
	
	public void startProducing() {
		producing = true;
	}
	
	public void stopProducing() {
		producing = false;
	}

	public void run() {
		Random rand = new Random();
		while(true) {
			try {
				Thread.sleep(SLEEP);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(producing) {
				try {
					int i = rand.nextInt(items.length);
					controller.storagePut(items[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
