package Assignments.A3;

import javax.swing.JCheckBox;

public class Consumer extends Thread {

	private int    itemsMax = 50;
	private double weightMax = 50;
	private double volumeMax = 50;
	
	private int    items;
	private double weight;
	private double volume;
	
	private boolean consuming = false;
	
	private JCheckBox continue_loading;

	private Controller controller;
	
	private String name;
	
	public Consumer(String name,Controller controller, JCheckBox chkBox) {
		this.continue_loading = chkBox;
		this.controller = controller;
		this.name = name;
		start();
	}

	public void startConsuming() {
		consuming = true;
		System.out.println("consuming");
	}
	
	public void stopConsuming() {
		consuming = false;
	}
	
	public void run() {
		FoodItem food = null;
		String itemList = "";
		String status = "";
		while(true) {
			while(consuming) {
				while ((items < itemsMax && weight < weightMax && volume < volumeMax)) { //not full
					try { food = controller.storageGet(); } catch (InterruptedException e) {e.printStackTrace();}
					controller.updateStatusBar();
					items++;
					weight += food.getWeight();
					volume += food.getVolume();
					itemList += food.getName() + "\n";
					controller.setValues(name, items, weight, volume, status, itemList);
				} 
				
				while(!(items < itemsMax && weight < weightMax && volume < volumeMax) && !continue_loading.isSelected()) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(continue_loading.isSelected()) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					items = 0;
					weight = 0;
					volume = 0;
					itemList = "";
					status = "Idle";
					controller.setValues(name, items, weight, volume, status, itemList);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
