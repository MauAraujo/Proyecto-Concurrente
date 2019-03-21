package problem;

import control.QTMatrix;
import matrix.DynamicLenghtMatrix;

public class Problem{

	public final static String PRODUCER_CONSUMER = "PRODUCER_CONSUMER";
	public final static String PHILOSOPHERS_DINNER = "PHILOSOPHERS_DINNER";
	public final static String READERS_WRITERS = "READERS_WRITERS";
	public final static String SLEEPING_BARBER = "SLEEPING_BARBER";
	public final static String SMOKERS = "SMOKERS";


	public EventRunner event;
	public QTMatrix qtmatrix;  
	public int[] CScounter = new int[10];
	
	public Problem(String problem, String control, int qtmlenght,int quatum) {
		switch (problem){
		case PRODUCER_CONSUMER:
			qtmatrix=new QTMatrix(2);
			event = new EventRunner(qtmatrix ,PRODUCER_CONSUMER ,control ,qtmlenght ,quatum,CScounter);
			break;
		case PHILOSOPHERS_DINNER:
			qtmatrix=new QTMatrix(5);
			event = new EventRunner(qtmatrix, PHILOSOPHERS_DINNER, control,qtmlenght,quatum,CScounter);
			break;
		case READERS_WRITERS:
			qtmatrix=new QTMatrix(8);
			event = new EventRunner(qtmatrix, READERS_WRITERS, control,qtmlenght,quatum,CScounter);
			break;
		case SLEEPING_BARBER:
			qtmatrix=new QTMatrix(6);
			event = new EventRunner(qtmatrix, SLEEPING_BARBER, control,qtmlenght,quatum,CScounter);
			break;
		case SMOKERS:
			qtmatrix=new QTMatrix(4);
			event = new EventRunner(qtmatrix, SMOKERS, control,qtmlenght,quatum,CScounter);
			break;
		default:

		}
	}


	public void start() {
		event.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		event.stop();
	}

}
