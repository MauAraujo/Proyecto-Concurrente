package problem.actor;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import problem.Actor;
import problem.sharedresource.Buffer;

public class Writer extends Thread implements Actor {

	public static final String IS_WAITING = "0";
	public static final String IS_WRITING = "1";
	
	public String controlcase = "0";
	 
	@SuppressWarnings("unchecked")
	ArrayList<String>[] threadst = new ArrayList[5];
	private Buffer buff;
	int quantum=100;
	private String state = IS_WAITING;
int ID;
	public Writer(int ID,int chair, Buffer buff, int quantum) {
		this.ID=ID;
		this.quantum=quantum;
		this.buff = buff;
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
		int rnd;
		while (true) {
			 rnd = (int)(Math.random() * 5);
			if(rnd == 1) {
				controlcase = "1";
				buff.use(ID);
				state = IS_WRITING;
				write();
				sleep();
				controlcase = "0";
				buff.relase();
				sleep();
				state = IS_WAITING;
			} else {
				sleep();
				state = IS_WAITING;
			}
			 rnd = (int)(Math.random() * 5);
		}
	}
	
	public void write() {
		buff.setBuffer("Writer: " + this.getId() + "using buffer");
	}
}