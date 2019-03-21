
package control.concurency;

import control.Control;

public class Monitor implements Control {
	boolean pase;

	public synchronized void lock(int ID){
		while(pase) {
			try {
				wait();
			}catch(InterruptedException e ){

				} 
			} 
		pase=true;
	}

	public synchronized void unlock() {
		pase = false;
		notify();
	}
 
 
}
 
