package control.concurency;

import control.Control;

public class Mutex implements Control {

	private boolean flag;

	@Override
	public void lock(int PID) {

		while (flag) {
			try {
				doWait();
			} catch (IllegalMonitorStateException e) {
			}
		}
		flag = true;
	}

	@Override
	public void unlock() {

		flag = false;
		doNotify();

	}
 
}
