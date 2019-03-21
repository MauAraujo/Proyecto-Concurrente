package problem.sharedresource;

import control.Control;
import problem.SharedResource;

public class Buffer implements SharedResource {
	int available = 0;
	Control control;
	String buffer = "";

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public Buffer(Control control) {
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
