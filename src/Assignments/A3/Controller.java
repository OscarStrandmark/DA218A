package Assignments.A3;

import java.util.concurrent.Semaphore;

public class Controller {

	private FoodItem[] foods = new FoodItem[10];
	
	public Producer p1; //Scan
	public Producer p2; //Arla
	public Producer p3; //AxFood
	
	public static final long DELAY_PRODUCERS = 750;
	
	public Consumer c1; //ICA
	public Consumer c2; //COOP
	public Consumer c3; //CITY GROSS
	
	private Buffer<FoodItem> storage;
	public int storageMax = 50;
	
	private Semaphore semaphore;
	
	private GUI gui;
	
	public Controller() {
		this.gui = new GUI(this);
		initFoods();

		semaphore = new Semaphore(3, true);
		storage = new Buffer<FoodItem>();
		
		p1 = new Producer(foods,this);
		p2 = new Producer(foods,this);
		p3 = new Producer(foods,this);
		
		c1 = new Consumer("ICA" ,this,gui.chkIcaCont);
		c2 = new Consumer("COOP",this,gui.chkCoopCont);
		c3 = new Consumer("CG"  ,this,gui.chkCGCont);
		
	}
	
	private void initFoods() {
		foods[0] = new FoodItem("Water",1,1);
		foods[1] = new FoodItem("Flour",0.2,1);
		foods[2] = new FoodItem("Apple",1,1.5);
		foods[3] = new FoodItem("Salt",1.4,1.56);
		foods[4] = new FoodItem("Pepper",1.11,0.27);
		foods[6] = new FoodItem("Chicken",1.77,1.55);
		foods[7] = new FoodItem("Beef",1.8,2);
		foods[8] = new FoodItem("Nuts",4.1,2.5);
		foods[9] = new FoodItem("Milk",0.6,0.1);
	}
	
	public synchronized void updateStatusBar() {
		gui.updateStatusBar(storageMax, storage.size());
	}
	
	public synchronized void setValues(String name, int items, double weight, double volume, String status, String itemList) {
		gui.setValues(name, items, weight, volume, status, itemList);
	}
	
	public void storagePut(FoodItem food) {
		try {
			semaphore.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		storage.put(food);
		
		gui.updateStatusBar(storageMax, storage.size());
		semaphore.release();
	}
	
	public FoodItem storageGet() throws InterruptedException {
		semaphore.acquire();
		
		while(storage.size() == 0) {
			semaphore.release();
			Thread.sleep(3000);
			semaphore.tryAcquire();
			System.out.println("I sleep");
		}
		
		FoodItem food = storage.get();
		
		gui.updateStatusBar(storageMax, storage.size());
		semaphore.release();
		return food;
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}
