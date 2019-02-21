package problem.sharedresource;

import control.Control;
import problem.SharedResource;

public class Chair implements SharedResource{

	int available = 0;
	Control control;
	
	public Chair(Control control) {
		super();
		this.control = control;
	}

	@Override
	public void use() {
		available++;
		control.lock();
		
	}

	@Override
	public void relase() {
		if (available > 0) {
			available--;
		}
		control.unlock();
	}

	public boolean isAvaliable() {
		if (available == 0) {
			return true;
		} else {
			return false;
		}
	}

}
