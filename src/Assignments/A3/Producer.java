package Assignments.A3;

import java.util.Random;

public class Producer extends Thread {

	private FoodItem[] foods;
	private boolean producing = false;
	private Controller controller;
	private Random rand = new Random();
	
	public Producer(FoodItem[] arr, Controller controller) {
		this.foods = arr;
		this.controller = controller;
		start();
	}
	
	public void startProducing() {
		producing = true;
	}
	
	public void stopProducing() {
		producing = false;
	}
	
	public void run() {
		int i;
		while(true) {
			if(producing) {
				try { Thread.sleep(Controller.DELAY_PRODUCERS); } catch (Exception e) {}
				i = rand.nextInt(foods.length);
				controller.storagePut(foods[i]);
			} else {
				try { Thread.sleep(Controller.DELAY_PRODUCERS); } catch (Exception e) {}
			}
		}
	}
}
