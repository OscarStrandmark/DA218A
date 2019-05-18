package A3;

import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

import javax.swing.JCheckBox;

public class Controller {

	private FoodItem[] items = { new FoodItem("Milk", 1, 1), new FoodItem("Cream", 0.6, 0.1),
			new FoodItem("Youghurt", 1.1, 0.5), new FoodItem("Butter", 2.34, 0.66), new FoodItem("Flour", 3.4, 1.2),
			new FoodItem("Sugar", 3.7, 1.8), new FoodItem("Salt", 1.55, 0.27), new FoodItem("Almonds", 0.6, 0.19),
			new FoodItem("Bread", 0.75, 1.98), new FoodItem("Donuts", 1.4, 0.5), new FoodItem("Jam", 1.3, 1.5),
			new FoodItem("Ham", 4.1, 2.5), new FoodItem("Chicken", 6.8, 3.9), new FoodItem("Salad", 0.87, 0.55),
			new FoodItem("Orange", 2.46, 0.29), new FoodItem("Apple", 2.44, 0.4), new FoodItem("Pear", 1.3, 0.77),
			new FoodItem("Soda", 2.98, 2.0), new FoodItem("Beer", 3.74, 1.5), new FoodItem("Hotdogs", 2.0, 1.38) };

	private Producer p1; // SCAN
	private Producer p2; // ARLA
	private Producer p3; // AXFOOD

	private Consumer c1; // ICA
	private Consumer c2; // COOP
	private Consumer c3; // CITY GROSS

	private Semaphore mutex;

	private Buffer<FoodItem> storage;
	private static final int MAX_SIZE = 50;
	private GUI gui;

	public Controller() {
		gui = new GUI(this, MAX_SIZE);
		mutex = new Semaphore(1, true);
		storage = new Buffer<FoodItem>();

		p1 = new Producer(this, "SCAN", items);
		p2 = new Producer(this, "ARLA", items);
		p3 = new Producer(this, "AXFOOD", items);

		JCheckBox[] boxes = gui.getCheckBoxes();
		
		c1 = new Consumer(this, "ICA", boxes[0]);
		c2 = new Consumer(this, "COOP", boxes[1]);
		c3 = new Consumer(this, "CITY GROSS", boxes[2]);
	}

	public void storagePut(FoodItem item) throws InterruptedException {
		try {
			mutex.acquire();
			storage.put(item);
			gui.setStored(storage.size());
		} finally {
			mutex.release();
		}
	}

	public FoodItem storageGet() throws InterruptedException, NoSuchElementException {
		FoodItem item;
		try {
			mutex.acquire();
			item = storage.get();
			gui.setStored(storage.size());
		} finally {
			mutex.release();
		}
		return item;
	}
	
	public void updateLabels(String name, int items, double weight, double volume, String status, String listString) {
		gui.setConsumerLbls(name, items, weight, volume, status, listString);
	}

	public void startProducer(int i) {
		switch (i) {
		case 1:
			p1.startProducing();
			break;

		case 2:
			p2.startProducing();
			break;

		case 3:
			p3.startProducing();
			break;
		}
	}

	public void stopProducer(int i) {
		switch (i) {
		case 1:
			p1.stopProducing();
			break;

		case 2:
			p2.stopProducing();
			break;

		case 3:
			p3.stopProducing();
			break;
		}
	}

	public void startConsumer(int i) {
		switch (i) {
		case 1:
			c1.startConsuming();
			break;
		case 2:
			c2.startConsuming();
			break;
		case 3:
			c3.startConsuming();
			break;
		}
	}

	public void stopConsumer(int i) {
		switch (i) {
		case 1:
			c1.stopConsuming();
			break;
		case 2:
			c2.stopConsuming();
			break;
		case 3:
			c3.stopConsuming();
			break;
		}
	}
}
