package control;

public interface Control {
	static final String MUTEX = "MUTEX"; 
	static final String MUTEXCONVAR = "MUTEXCONVAR"; 
	static final String SEMAPHORE = "SEMAPHORE"; 
	static final String MONITOR = "MONITOR";

	boolean flag = false;

	public void lock(int PID);

	public void unlock();

	default void doNotify() {

		try {
			this.notify();
		} catch (IllegalMonitorStateException e) {
		}
	}
	

	default void doWait() throws IllegalMonitorStateException {
		try {
			this.wait();
		} catch (InterruptedException e) {
		}
	}
}