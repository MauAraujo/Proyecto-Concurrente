package problem;

import control.Proc.PIDC;

public interface Actor {
	
	String controlcase ="0";
	String state = null;
	int quantum = 100;
	int ID =0;
	PIDC pid=null;
	
	public default void sleep(){
		try {
			Thread.sleep(quantum + (int) (Math.random() * quantum * .10));
		} catch (InterruptedException e) {
		}
	}

	public default String state() {
		return Actor.state;
	}

	public default boolean is(String state) {
		if (Actor.state == state) {
			return true;
		} else {
			return false;
		}
	}
 

	public void run();

	
	public void start();

}
