package A3;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

public class Consumer extends Thread {

	private static final long SLEEP = 1000;

	//LIMITS 
	private static final int     MAX_ITEMS = 50; 
	private static final double MAX_WEIGHT = 100;
	private static final double MAX_VOLUME = 75;
	
	private static final long TAKE_DELAY = 1000;
	private static final long FULL_SLEEP = 5000;
	//CURRENT VALUES
	private int     items = 0;
	private double weight = 0;
	private double volume = 0;
	
	private JCheckBox box;
	
	private Controller controller;
	
	private boolean consuming;
	
	private ArrayList<FoodItem> list;
	
	public Consumer(Controller controller, String name, JCheckBox box) {
		this.list = new ArrayList<FoodItem>();
		this.controller = controller;
		this.box = box;
		consuming = false;
		setName(name);
		start();
	}
	
	public void startConsuming() {
		consuming = true;
	}
	
	public void stopConsuming() {
		consuming = false;
	}
	
	public void run() {
		String listString = "";
		while(true) {
			try {
				Thread.sleep(SLEEP);
			} catch (Exception e) {
				e.printStackTrace();
			}
			while(consuming) {
				list = new ArrayList<FoodItem>();
				controller.updateLabels(getName(), 0, 0, 0, "", "");
				while((items < MAX_ITEMS) && (weight < MAX_WEIGHT) && (volume < MAX_VOLUME)) { //while not full on items, weight or volume
					try {
						FoodItem item = controller.storageGet();
						
						if(item != null) {
							list.add(item);
							
							listString += item.getName() + "\n";
							volume += item.getVolume();
							weight += item.getWeight();
							items++;
							controller.updateLabels(getName(), items, weight, volume, "", listString);	//Borde hanteras av UI-tråd, men jag är lat.								
						}
						Thread.sleep(TAKE_DELAY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}				
				try {
					Thread.sleep(FULL_SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				while(!box.isSelected()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
