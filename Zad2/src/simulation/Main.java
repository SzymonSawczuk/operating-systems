package simulation;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		double p = 0.8;
		Random rand = new Random();
		Scanner scanner = new Scanner(System.in);
		Task task;
		
		System.out.print("Podaj ilosc cykli: ");
		final int AMOUNTOFCYCLES = scanner.nextInt();
		
		System.out.print("Podaj wielokosc dysku: ");
		final int MAXOFDISK = scanner.nextInt();
		Context.getInstance().setMax(MAXOFDISK);
		
		System.out.print("Podaj pozycje startowa: ");
		final int STARTPOSITION = scanner.nextInt();
		Context.getInstance().setStart(STARTPOSITION);
		
		System.out.print("Podaj ilosc zgloszen: ");
		final int AMOUNTOFTASKS = scanner.nextInt();
		Context.getInstance().setMaxAmountOfTasks(AMOUNTOFTASKS);
		
		System.out.print("Podaj procent zgloszen real-time: ");
		final int PROCENTOFREALTIME = scanner.nextInt();
		Context.getInstance().setPrioritypercent(PROCENTOFREALTIME);
		
		int counterToPriority = Context.getInstance().getPrioritypercent() != 0?100/Context.getInstance().getPrioritypercent():-1;
		
		Context.getInstance().setPriorityAmount(AMOUNTOFTASKS * PROCENTOFREALTIME / 100);
		
		
		Fcfs fcfs;
		Sstf sstf;
		Scan scan;
		CScan cscan;
		Fcfs_EDF fcfs_edf;
		Sstf_EDF sstf_edf;
		Scan_EDF scan_edf;
		CScan_EDF cscan_edf;
		
		Context.getInstance().setDistanceFCFS(0);
		Context.getInstance().setDistanceSSTF(0);
		Context.getInstance().setDistanceSCAN(0);
		Context.getInstance().setDistanceCSCAN(0);
		Context.getInstance().setDistanceFCFS_EDF(0);
		Context.getInstance().setDistanceSSTF_EDF(0);
		Context.getInstance().setDistanceSCAN_EDF(0);
		Context.getInstance().setDistanceCSCAN_EDF(0);
		 
		for(int i = 0; i<AMOUNTOFCYCLES; i++) {
			
			Context.getInstance().setAmountOfDoneTasksFCFS(0);
			Context.getInstance().setAmountOfDoneTasksSSTF(0);
			Context.getInstance().setAmountOfDoneTasksSCAN(0);
			Context.getInstance().setAmountOfDoneTasksCSCAN(0);
			Context.getInstance().setAmountOfDoneNormalTasksFCFS_EDF(0);
			Context.getInstance().setAmountOfDonePriorityTasksFCFS_EDF(0);
			Context.getInstance().setAmountOfDoneNormalTasksSSTF_EDF(0);
			Context.getInstance().setAmountOfDonePriorityTasksSSTF_EDF(0);
			Context.getInstance().setAmountOfDoneNormalTasksSCAN_EDF(0);
			Context.getInstance().setAmountOfDonePriorityTasksSCAN_EDF(0);
			Context.getInstance().setAmountOfDoneNormalTasksCSCAN_EDF(0);
			Context.getInstance().setAmountOfDonePriorityTasksCSCAN_EDF(0);
			
			Context.getInstance().setCurrentPositionSSTF(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionSCAN(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionCSCAN(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionFCFS_EDF(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionSSTF_EDF(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionSCAN_EDF(Context.getInstance().getStart());
			Context.getInstance().setCurrentPositionCSCAN_EDF(Context.getInstance().getStart());
			
			fcfs = new Fcfs();
			sstf = new Sstf();
			scan = new Scan();
			cscan = new CScan();
			fcfs_edf = new Fcfs_EDF();
			sstf_edf = new Sstf_EDF();
			scan_edf = new Scan_EDF();
			cscan_edf = new CScan_EDF();
			
			while(!fcfs.isDone() || !sstf.isDone() || !scan.isDone() || !cscan.isDone() || !fcfs_edf.isDone() || !sstf_edf.isDone() || !scan_edf.isDone() || !cscan_edf.isDone()) {
				
				if(rand.nextDouble() >= p) {
					
					counterToPriority--;
					
					if(counterToPriority == 0) {
						task = new Task(rand.nextInt(Context.getInstance().getMax()) + Context.getInstance().getMin(), true, rand.nextInt(Context.getInstance().getMax()) + Context.getInstance().getMin()); 
						counterToPriority = 100/Context.getInstance().getPrioritypercent();
					}
					else {
						task = new Task(rand.nextInt(Context.getInstance().getMax()) + Context.getInstance().getMin(), false, rand.nextInt(Context.getInstance().getMax()) + Context.getInstance().getMin()); 
					}
					
					fcfs.enqueue(new Task(task));
					sstf.enqueue(new Task(task));
					scan.enqueue(new Task(task));
					cscan.enqueue(new Task(task));
					fcfs_edf.enqueue(new Task(task));
					sstf_edf.enqueue(new Task(task));
					scan_edf.enqueue(new Task(task));
					cscan_edf.enqueue(new Task(task));
					
					p = 0.8;
					 
				}else {
					 p-=0.01;
				}
				
				fcfs.fcfsTick();
				sstf.sstfTick();
				scan.scanTick();
				cscan.cscanTick();
				fcfs_edf.fcfs_edfTick();
				sstf_edf.sstf_edfTick();
				scan_edf.scan_edfTick();
				cscan_edf.cscan_edfTick();
			}
			
		}
		
		System.out.println("----------------------------------------------");
		System.out.println("Algorytmy bez uwzglednienia zgloszen real-time");
		System.out.println("---------------------------------------------- ");

		System.out.println("FCFS: " + Context.getInstance().getDistanceFCFS()/AMOUNTOFCYCLES);
		System.out.println("SSTF: " + Context.getInstance().getDistanceSSTF()/AMOUNTOFCYCLES);
		System.out.println("SCAN: " + Context.getInstance().getDistanceSCAN()/AMOUNTOFCYCLES);
		System.out.println("C_SCAN: " + Context.getInstance().getDistanceCSCAN()/AMOUNTOFCYCLES);
		
		System.out.println("----------------------------------------------");
		System.out.println("Algorytmy z uwzglednieniem zgloszen real-time");
		System.out.println("---------------------------------------------- ");
		
		System.out.println("FCFS_EDF: "+ Context.getInstance().getDistanceFCFS_EDF()/AMOUNTOFCYCLES);
		System.out.println("SSTF_EDF: " + Context.getInstance().getDistanceSSTF_EDF()/AMOUNTOFCYCLES);
		System.out.println("SCAN_EDF: " + Context.getInstance().getDistanceSCAN_EDF()/AMOUNTOFCYCLES);
		System.out.println("C_SCAN_EDF: " + Context.getInstance().getDistanceCSCAN_EDF()/AMOUNTOFCYCLES);
		scanner.close();
		
		
	}

}
