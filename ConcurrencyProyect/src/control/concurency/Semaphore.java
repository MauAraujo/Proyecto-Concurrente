package control.concurency;

import java.util.LinkedList;
import java.util.Queue;

import control.Control;

public class Semaphore extends Mutex implements Control{  
 
		Queue<Integer> parray = new LinkedList<>(); 
		private boolean flag;
		
		public void lock(int PID) {
			 
			parray.add(PID); 
			while(parray.peek()!=PID) {  
			}
			parray.remove();
			while (flag) {
				try {
					doWait();
				} catch (IllegalMonitorStateException e) {
				}
			}
			flag = true;
		}

		public void unlock() {

			flag = false;
			doNotify();

		}

}
