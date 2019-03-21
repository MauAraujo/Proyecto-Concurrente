package problem.actor;

import problem.Actor;
import problem.sharedresource.Chair;

public class Barber extends Thread implements Actor{

	public static final String IS_ON_CHAIR = "0";
	public static final String IS_CUTTING_HAIR = "1"; 
	
	public String controlcase = "0";
	private Chair chair;
	int quantum=100;
	private String state = IS_ON_CHAIR;
	int ID = 0;
	
	public Barber(int ID, Chair sharedResource, int quantum) {
		this.ID = ID;
		this.chair = sharedResource;
		this.quantum = quantum;
	}
	
	
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
				sleep(); 
			}
			else {
				
				state = IS_CUTTING_HAIR; 
				sleep(); 
			}
			

		}
	}

	public void sitOnChair() {

		chair.use(ID);
	}
	
	public void standUp() {
		chair.relase();
	}
}
