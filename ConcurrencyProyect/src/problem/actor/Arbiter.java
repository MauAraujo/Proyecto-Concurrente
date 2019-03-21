package problem.actor;

import problem.Actor;
import problem.SharedResource; 	
import java.util.Random;

public class Arbiter extends Thread implements Actor {
 
	public String state="0";
	public String controlcase="0";
	private volatile boolean running = true;
	public int quantum=100;
	Random rand = new Random();
	public Arbiter(Smoker smoker0,Smoker smoker1,Smoker smoker2) { 
		this.smoker0=smoker0; 
		this.smoker1=smoker1; 
		this.smoker2=smoker2;
	}

	Smoker smoker0,smoker1,smoker2; 
	
	int s1,s2,s3;
	public void run() {
		running = true;

		while (true) {

			state="0";
			s1=rand.nextInt(3);  
			sleep();
			do {
				s2=rand.nextInt(3); 
			}while(s1!=s2);

			state="1";
			sleep(); 
			state="0";
			s3=((s1+s2+1)%3);
			switch(s3) {
			case 0:
				smoker0.smoke();
				break;
			case 1:
				smoker1.smoke();
				break;
			case 2: 
				smoker2.smoke();
				break;
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
