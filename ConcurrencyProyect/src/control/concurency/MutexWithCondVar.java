package control.concurency;

import control.Control;

public class MutexWithCondVar extends Mutex implements Control{

	private boolean flag;
	public int ID_PASS;
	
	private int FirstID,LastID;
	
	public MutexWithCondVar(int FID,int LID){
		FirstID=FID;
		LastID=LID;
		ID_PASS=FirstID;
	}
	@Override
	public void lock(int PID) {

		WaitforCondition(PID);

		System.out.println("condition");
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
 
 
	private void WaitforCondition(int PID) {
		while(PID!=ID_PASS) {  

			try {
				doWait();
			} catch (IllegalMonitorStateException e) {
			}
		}   
		if(ID_PASS++==LastID) {
			ID_PASS=FirstID;
		} 
	}
}
