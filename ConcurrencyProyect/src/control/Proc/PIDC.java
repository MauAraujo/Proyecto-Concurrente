package control.Proc;

public class PIDC {
	int CPID=-1;
	int LAST_PID=-1;
	public PIDC(int start){
		CPID=start;
		LAST_PID=CPID;
	}
	public int next() {
		return CPID++;
	}
}
