package problem.actor;

import java.util.ArrayList;

import problem.Actor;
import problem.sharedresource.Fork;

public class Diner extends Thread implements Actor {

	public static final String IS_HUNGRY = "0";
	public static final String IS_EATING = "1";
	public static final String IS_THINKING = "2"; 
	
	public String controlcase = "0";
	 
	@SuppressWarnings("unchecked")
	ArrayList<String>[] threadst = new ArrayList[5];
	private Fork fork[];
	private int chair;
	int quantum=100;
	private String state = IS_HUNGRY;
	int ID;
	
	public Diner(int ID,int chair, Fork[] fork, int quantum) {
		this.ID=ID;
		this.quantum=quantum;
		this.fork = fork;
		this.chair = chair;
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

			controlcase="1";
			takerightfork(); 
			takeleftfork(); 
			state = IS_EATING; 
			sleep(); 
			state = IS_THINKING;  
			controlcase="0";
			relaseleftfork();  
			relaserightfork();    
			sleep(); 
			state = IS_HUNGRY;

		}
	}

	public void takerightfork() {

		fork[chair].use(ID);
	}

	public void takeleftfork() {
		if (chair == 0) {
			fork[4].use(ID);
		} else {
			fork[chair - 1].use(ID);
		}
	}

	public void relaserightfork() {

		fork[chair].relase();
	}

	public void relaseleftfork() {
		if (chair == 0) {
			fork[4].relase();
		} else {
			fork[chair - 1].relase();
		}
	}
 
 

}
