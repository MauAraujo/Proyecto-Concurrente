package problem.actor;

import problem.Actor;
import problem.SharedResource;

public class Producer extends Thread implements Actor {
	private SharedResource product;
	public String state="0";
	public String controlcase="0";
	private volatile boolean running = true;
	public int quantum=100;
	int ID;
	public Producer(int ID,SharedResource product,int quantum) {
		this.ID=ID;
		this.quantum=quantum;
		this.product = product;
	}

	@Override
	public void run() {

		running = true;
		 
		while (true) {


			sleep();  
			controlcase="1";   
			product.use(ID); 
			state="1";   
			sleep();  
			controlcase="0";
			product.relase();  
			state="0";  
			
			while (!running) {
			}

		}

	}

	public void resumep() {
		this.running = true;
	}

	public void pause() {
		this.running = false;
	}
	
	 

}
