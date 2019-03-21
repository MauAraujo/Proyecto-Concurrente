package problem.event;

import control.Control;
import control.QTState;
import control.State;
import control.Proc.PIDC;
import control.concurency.Monitor;
import control.concurency.Mutex;
import control.concurency.MutexWithCondVar;
import control.concurency.Semaphore;
import matrix.DynamicLenghtMatrix;
import problem.Actor;
import problem.Event;
import problem.SharedResource;
import problem.actor.Consumer;
import problem.actor.Diner;
import problem.actor.Producer;
import problem.sharedresource.Fork;
import problem.sharedresource.Product;

public class ProducerConsumer implements Event {
	private SharedResource producto;
	private Control control;
	private Consumer consumer;
	private Producer producer;
	private int quantum; 
	PIDC PID;
	
	public ProducerConsumer() {
		
	}

	public ProducerConsumer(String ControlMethod,int quantum) { 
		this.quantum=quantum;

		switch(ControlMethod){
		case Control.MUTEX:
			producto = new Product(new Mutex()); 
		break;
		case Control.MUTEXCONVAR: 
			producto = new Product(new MutexWithCondVar(1, 2)); 
			break;
		case Control.SEMAPHORE: 
			producto = new Product(new Semaphore()); 
			break;
		case Control.MONITOR: 
			producto = new Product(new Monitor()); 
			break;
		}
		PID=new PIDC(1);
		this.consumer=new Consumer(PID.next(), producto, quantum);
		this.producer=new Producer(PID.next(), producto, quantum);		

	}

	@Override
	public void start() {
		consumer.start();
		producer.start(); 
	}

	@Override
	public QTState getstates() {

		QTState qstate = new QTState(2);
		 	
			qstate.add(0, new State(consumer.state,consumer.controlcase));
			qstate.add(1, new State(producer.state,producer.controlcase));
			
		return qstate;

	}
}
