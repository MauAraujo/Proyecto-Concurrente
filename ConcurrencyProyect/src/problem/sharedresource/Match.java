package problem.sharedresource;

import control.Control;
import problem.SharedResource;

public class Match implements SharedResource {
	int available = 0;
	Control control;

	public Match(Control control) {
		this.control = control;
	}

	@Override
	public void use(int PID) {
		available++;
		control.lock(PID);
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
