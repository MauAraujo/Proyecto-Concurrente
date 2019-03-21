package problem.actor;

import problem.Actor;
import problem.sharedresource.Chair;


public class BarberCustomer extends Thread implements Actor {

	public static final String IS_ON_CHAIR = "0";
	public static final String IS_WAITING = "1"; 
	int ID;
	
	public BarberCustomer(int ID, Chair chair, int quantum) {
		
		this.ID = ID;
		this.chair = chair;
		this.quantum = quantum;
	}

	public String controlcase = "0";
	private Chair chair;
	int quantum=100;
	private String state = IS_WAITING;

	@Override
	public boolean is(String state) {
		if (this.state == state) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String state() {
		return this.state;
	}

	@Override
	public void run() {
		sleep();
		while (true) {

			if (chair.isAvaliable()) {
				controlcase="1";
				sitOnChair();
				state = IS_ON_CHAIR; 
				sleep(); 
				
				controlcase="0";
				standUp();
			}
			
				
				state = IS_WAITING; 
				sleep(); 
			 
		}
	}

	public void sitOnChair() {

		chair.use(ID);
	}
	
	public void standUp() {
		chair.relase();
	}

}
