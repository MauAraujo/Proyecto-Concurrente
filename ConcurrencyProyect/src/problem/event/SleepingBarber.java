package problem.event;

import control.Control;
import control.QTState;
import control.State;
import control.concurency.Monitor;
import control.concurency.Mutex;
import control.concurency.MutexWithCondVar;
import control.concurency.Semaphore;
import problem.Event;
import problem.actor.Barber;
import problem.actor.BarberCustomer;
import problem.actor.Reader;
import problem.actor.Writer;
import problem.sharedresource.Buffer;
import problem.sharedresource.Chair;

public class SleepingBarber implements Event {

	private Chair chair;
	private Barber barber ;
	private BarberCustomer[] customer = new BarberCustomer[5];
	private int quantum;
	
	public SleepingBarber(String ControlMethod,int quantum) {
		this.quantum = quantum;
		
		//Recurso compartido

		switch(ControlMethod){
		case Control.MUTEX:
			chair = new Chair(new Mutex());
		break;
		case Control.MUTEXCONVAR:  
			chair = new Chair(new MutexWithCondVar(1, 2)); 
			break;
		case Control.SEMAPHORE: 
			chair = new Chair(new Semaphore());
			break;
		case Control.MONITOR: 
			chair = new Chair(new Semaphore());
			break;
			
		}
		
		
		
		//Barbero
		barber = new Barber(0, chair, quantum);
		
		//Clientes
		customer[0] = new BarberCustomer(0, chair, quantum);
		customer[1] = new BarberCustomer(1, chair, quantum);
		customer[2] = new BarberCustomer(2, chair, quantum);
		customer[3] = new BarberCustomer(3, chair, quantum);
		customer[4] = new BarberCustomer(4, chair, quantum);
		
		
		
	}
	
	@Override
	public void start() {
		
		barber.start();
		
		customer[0].start();
		customer[1].start();
		customer[2].start();
		customer[3].start();
		customer[4].start();
		
	}

	@Override
	public QTState getstates() {
		
		QTState qstate = new QTState(6);
		for (int number = 0; number < 6; number++) {
			if(number == 0) {
				if (barber.state() == Barber.IS_ON_CHAIR) {
					qstate.add(number, new State(Barber.IS_ON_CHAIR,barber.controlcase));
				} else if (barber.state() == Barber.IS_CUTTING_HAIR) {
					qstate.add(number, new State(Barber.IS_CUTTING_HAIR,barber.controlcase));
				}			
			}
			else {
				if (customer[number - 1].state() == BarberCustomer.IS_WAITING) {
					qstate.add(number, new State(BarberCustomer.IS_WAITING,customer[number - 1].controlcase));
				} else if (customer[number - 1].state() == BarberCustomer.IS_ON_CHAIR) {
					qstate.add(number, new State(BarberCustomer.IS_ON_CHAIR,customer[number - 1].controlcase));
				} 
			}
		}
		return qstate;
	}
}
