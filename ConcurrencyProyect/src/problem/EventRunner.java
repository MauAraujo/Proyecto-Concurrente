package problem;

import control.QTMatrix;
import control.QTState;
import matrix.DynamicLenghtMatrix;
import problem.event.*;

public class EventRunner extends Thread {
	public Event event; 
	public QTMatrix qtmatrix;
	int qtmlenght;
	private volatile boolean running = false;
	public int[] CScounter = new int[10];
	
	EventRunner(QTMatrix qtmatrix, String event, String control,int qtmlenght, int quatum,int[] CScounter) { 
		this.CScounter=CScounter;
		this.qtmatrix=qtmatrix;
		this.qtmlenght=qtmlenght;
		switch (event) {
		case Problem.PRODUCER_CONSUMER:
			this.event = new ProducerConsumer(control,quatum);
			break;
		case Problem.PHILOSOPHERS_DINNER:
			this.event = new Dinner(control,quatum);
			break;
		case Problem.READERS_WRITERS:
			this.event = new ReadersWriters(control,quatum);
			break;
		case Problem.SLEEPING_BARBER:
			this.event = new SleepingBarber(control,quatum);
			break;
		case Problem.SMOKERS:
			this.event = new Smokers(quatum);
			break;
		default:

		}
	}

	@Override
	public void run() {
		running = true;
		event.start();
		while (true) {
			while (running && qtmatrix.lenght() < qtmlenght) {
				logrevision(event.getstates());
			}

		}
	}

	private void logrevision(QTState Threadstates) {
		if (!qtmatrix.isLastQT(Threadstates)) {

			qtmatrix.print();

			CSanalyze();
			qtmatrix.updateQTMatrix(Threadstates); 

		}
	}

	private void CSanalyze() { 
		for(int r=0;r<qtmatrix.height();r++) {  
			CScounter[r]=0;
		} 
		for(int r=0;r<qtmatrix.height();r++) {// N    PROCESO  
			for(int c=1;c<qtmatrix.lenght();c++) {
					if((qtmatrix.get(r,c).state=="1") && (qtmatrix.get(r,c-1).state!="1") ) {
						CScounter[r]++;
					}
			}
		}
		for(int r=0;r<qtmatrix.height();r++) {  
				System.out.println(CScounter[r]); 
		} 
	}
	
}
