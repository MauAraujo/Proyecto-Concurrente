package problem.event;

import control.Control;
import control.QTMatrix;
import control.QTState;
import control.State; 
import control.Proc.PIDC;

import control.concurency.*; 
import matrix.DynamicLenghtMatrix;
import problem.Event;
import problem.actor.Diner;
import problem.sharedresource.Fork;

public class Dinner implements Event {

	static final String BEINGHUNGRY = "0";
	static final String EATING = "1";
	static final String THINKING = "2";
	Fork fork[] = new Fork[5];
	private Diner philosopher[] = new Diner[5];
	private int quantum;
	PIDC PID;
	public Dinner(String ControlMethod,int quantum) { 
		this.quantum=quantum;
		switch(ControlMethod){
		case Control.MUTEX:
			fork[0] = new Fork(new Mutex());
			fork[1] = new Fork(new Mutex());
			fork[2] = new Fork(new Mutex());
			fork[3] = new Fork(new Mutex());
			fork[4] = new Fork(new Mutex());
		break;
		case Control.MUTEXCONVAR:

			fork[0] = new Fork(new MutexWithCondVar(1,5));
			fork[1] = new Fork(new MutexWithCondVar(1,5));
			fork[2] = new Fork(new MutexWithCondVar(1,5));
			fork[3] = new Fork(new MutexWithCondVar(1,5));
			fork[4] = new Fork(new MutexWithCondVar(1,5)); 
			break;
		case Control.SEMAPHORE:

			fork[0] = new Fork(new Semaphore());
			fork[1] = new Fork(new Semaphore());
			fork[2] = new Fork(new Semaphore());
			fork[3] = new Fork(new Semaphore());
			fork[4] = new Fork(new Semaphore());
			break;
		case Control.MONITOR:

			fork[0] = new Fork(new Monitor());
			fork[1] = new Fork(new Monitor());
			fork[2] = new Fork(new Monitor());
			fork[3] = new Fork(new Monitor());
			fork[4] = new Fork(new Monitor());
			break;
		}
		

		PID=new PIDC(1);
		philosopher[0] = new Diner(PID.next(),0, fork,quantum);
		philosopher[1] = new Diner(PID.next(),1,fork,quantum);
		philosopher[2] = new Diner(PID.next(),2, fork,quantum);
		philosopher[3] = new Diner(PID.next(),3,fork,quantum);
		philosopher[4] = new Diner(PID.next(),4, fork,quantum);

	}

	@Override
	public void start() {
		philosopher[0].start();
		philosopher[1].start();
		philosopher[2].start();
		philosopher[3].start();
		philosopher[4].start();
	}

	@Override
	public QTState getstates() {

		QTState qstate = new QTState(5);
		
		for (int number = 0; number < 5; number++) {
			
			if (philosopher[number].state() == Diner.IS_HUNGRY) {
				qstate.add(number, new State(Diner.IS_HUNGRY,philosopher[number].controlcase));
			} else if (philosopher[number].state() == Diner.IS_EATING) {
				qstate.add(number, new State(Diner.IS_EATING,philosopher[number].controlcase));
			} else if (philosopher[number].state() == Diner.IS_THINKING) {
				qstate.add(number, new State(Diner.IS_THINKING,philosopher[number].controlcase));
			}
			
		}
		return qstate;

	}
 

}
