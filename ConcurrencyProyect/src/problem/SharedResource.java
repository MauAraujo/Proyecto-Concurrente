package problem;

import control.Control;

public interface SharedResource {
	public int isAviable = 0;
	public Control control = null; 

	public void use();

	public void relase();
}
