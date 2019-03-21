package problem.event;

import control.Control;
import control.QTMatrix;
import control.QTState;
import control.State;
import control.Proc.PIDC;
import control.concurency.Monitor;
import control.concurency.Mutex;
import control.concurency.MutexWithCondVar;
import control.concurency.Semaphore;
import matrix.DynamicLenghtMatrix;
import problem.Event;
import problem.actor.Reader;
import problem.actor.Writer;
import problem.sharedresource.Buffer;
import problem.sharedresource.Product;

public class ReadersWriters implements Event {

	static final String WAITING = "0";
	static final String READING = "1";
	static final String WRITING = "1";
	Buffer buff;
	private Reader reader[] = new Reader[5];
	private Writer writer[] = new Writer[3];
	private int quantum;
	PIDC PID;
	public ReadersWriters(String ControlMethod,int quantum) { 
		this.quantum=quantum;
		
		

		switch(ControlMethod){
		case Control.MUTEX:
			buff = new Buffer(new Mutex());
		break;
		case Control.MUTEXCONVAR: 

			buff = new Buffer(new MutexWithCondVar(1, 2)); 
			break;
		case Control.SEMAPHORE: 
			buff = new Buffer(new Semaphore()); 
			break;
		case Control.MONITOR: 
			buff = new Buffer(new Monitor()); 
			break;
		}
		
		
		PID =new PIDC(1);
		reader[0] = new Reader(PID.next(),0, buff,quantum);
		reader[1] = new Reader(PID.next(),1, buff,quantum);
		reader[2] = new Reader(PID.next(),2, buff,quantum);
		reader[3] = new Reader(PID.next(),3, buff,quantum);
		reader[4] = new Reader(PID.next(),4, buff,quantum);
		
		writer[0] = new Writer(PID.next(),0, buff,quantum);
		writer[1] = new Writer(PID.next(),1, buff,quantum);
		writer[2] = new Writer(PID.next(),2, buff,quantum);

	}

	@Override
	public void start() {
		writer[0].start();
		writer[1].start();
		writer[2].start();
		
		reader[0].start();
		reader[1].start();
		reader[2].start();
		reader[3].start();
		reader[4].start();
	}

	@Override
	public QTState getstates() {

		QTState qstate = new QTState(8);
		
		for (int number = 0; number < 8; number++) {
			if(number < 5) {
				if (reader[number].state() == Reader.IS_WAITING) {
					qstate.add(number, new State(Reader.IS_WAITING,reader[number].controlcase));
				} else if (reader[number].state() == Reader.IS_READING) {
					qstate.add(number, new State(Reader.IS_READING,reader[number].controlcase));
				} 
			}
			else {
				if (writer[number - 5].state() == Writer.IS_WAITING) {
					qstate.add(number, new State(Writer.IS_WAITING,writer[number - 5].controlcase));
				} else if (writer[number - 5].state() == Writer.IS_WRITING) {
					qstate.add(number, new State(Writer.IS_WRITING,writer[number - 5].controlcase));
				}
			}
		}
		return qstate;
	}
}