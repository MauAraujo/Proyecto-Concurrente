package problem.actor;

import java.util.ArrayList;

import problem.Actor;
import problem.sharedresource.Buffer;

public class Reader extends Thread implements Actor {

	public static final String IS_WAITING = "0";
	public static final String IS_READING = "1"; 
	
	public String controlcase = "0";
	 
	@SuppressWarnings("unchecked")
	ArrayList<String>[] threadst = new ArrayList[5];
	private Buffer buff;
	int quantum=100;
	private String state = IS_WAITING;

	public Reader(int PID,int chair, Buffer buff, int quantum) {
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
		while (true) {
			if(buff.isAvaliable()) {
				read();
				state = IS_READING;
				sleep();
			}
			sleep();
			state = IS_WAITING;
		}
	}
 
	public void read() {
		buff.getBuffer();
	}
 

}