package simulation;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	 public static void main(String[] args) {
		 
		 double p = 0.8;
		 Random rand = new Random();
		 Scanner scanner = new Scanner(System.in);
		 Fcfs fcfs;
		 Sjf sjf;
		 Rr rR;
		 Process process = null;
		 
		 System.out.print("Podaj liczbe procesow: ");
		 final long AMOUNTOFPROCESSES = scanner.nextLong();
		 Context.getInstance().setMaxAmountOfProcesses(AMOUNTOFPROCESSES);
		 
		 System.out.print("Podaj liczbe cyklow: ");
		 final long AMOUNTOFCYCLES = scanner.nextLong();
		 
		 System.out.print("Podaj maksymalna wartosc dlugosci procesu: ");
		 final int MAX = scanner.nextInt();
		 Context.setMaxDuration(MAX);

		 System.out.print("Podaj granice przeglodzenia: ");
		 final int LIMIT = scanner.nextInt(); 
		 Context.setHUNGRYPROCESSLIMIT(LIMIT);
		 
		 System.out.print("Podaj kwant czasu dla RR: ");
		 final int QUANT = scanner.nextInt();
		 
		 for(int i = 0; i<AMOUNTOFCYCLES;i++) {
			 
			 Context.getInstance().setTime(0);
			 Context.getInstance().setCurrentAmountOfProcessesForFCFS(0);
			 Context.getInstance().setCurrentAmountOfProcessesForSJF(0);
			 Context.getInstance().setCurrentAmountOfProcessesForRR(0);
			 Context.getInstance().setAmountOfProcesses(0);
			 
			 fcfs = new Fcfs();
			 sjf = new Sjf();
			 rR = new Rr(QUANT);
				
			 while(!sjf.isDone() || !fcfs.isDone() || !rR.isDone()) {
				 
				 Context.getInstance().setTime(Context.getInstance().getTime() + 1);
				 if(Context.getInstance().getAmountOfProcesses() != Context.getInstance().getMaxAmountOfProcesses()) {
					 
					 if(rand.nextDouble() >= p) {
						 
						 process = new Process(Context.getInstance().getTime());
//						 System.out.println(process.toString());
						 Context.getInstance().setAmountOfProcesses(Context.getInstance().getAmountOfProcesses() + 1);
						 
						 if(!fcfs.isDone()) {
							 Process processFCFS = new Process(Context.getInstance().getTime(), process);
							 fcfs.enqueue(processFCFS);
						 }
							
						 if(!sjf.isDone()) {
							 Process processSJF = new Process(Context.getInstance().getTime(), process);
							 sjf.add(processSJF);
						 }
						 
						 if(!rR.isDone()) {
							 Process processRR = new Process(Context.getInstance().getTime(), process);
							 rR.add(processRR);
						 }
							
						 p = 0.8;
						 
					 }else {
						 p-=0.01;
					 }
					 
				 }
				fcfs.fcfsTick();
				
				sjf.sjfTick();
				
				rR.rRTick();
				 
			 }
		 }

		 System.out.println();
		 
		 System.out.println("FCFS: ");
		 System.out.println("Sredni czas oczekiwania: " + Context.getInstance().getWaitingTimeOfFCFS()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Maksymalny czas oczekiwania: " + Context.getInstance().getMaxWaitingTimeOfFCFS());
		 System.out.println("Ilosc procesow zaglodzonych: " + Context.getInstance().getHungryProcessesOfFCFS());
		 System.out.println("Sredni czas wykonania procesu: " + Context.getInstance().getFinishingTimeOfFCFS()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Srednia liczba przelaczen procesora: " + Context.getInstance().getAmountOfSwitchesOfFCFS()/AMOUNTOFCYCLES);
		 
		 System.out.println();
		 
		 System.out.println("SJF: ");
		 System.out.println("Sredni czas oczekiwania: " + Context.getInstance().getWaitingTimeOfSJF()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Maksymalny czas oczekiwania: " + Context.getInstance().getMaxWaitingTimeOfSJF());
		 System.out.println("Ilosc procesow zaglodzonych: " + Context.getInstance().getHungryProcessesOfSJF());
		 System.out.println("Sredni czas wykonania procesu: " + Context.getInstance().getFinishingTimeOfSJF()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Srednia liczba przelaczen procesora: " + Context.getInstance().getAmountOfSwitchesOfSJF()/AMOUNTOFCYCLES);
		 
		 System.out.println();
		 
		 System.out.println("RR: ");
		 System.out.println("Sredni czas oczekiwania: " + Context.getInstance().getWaitingTimeOfRR()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Maksymalny czas oczekiwania: " + Context.getInstance().getMaxWaitingTimeOfRR());
		 System.out.println("Ilosc procesow zaglodzonych: " + Context.getInstance().getHungryProcessesOfRR());
		 System.out.println("Sredni czas wykonania procesu: " + Context.getInstance().getFinishingTimeOfRR()/(AMOUNTOFPROCESSES * AMOUNTOFCYCLES));
		 System.out.println("Srednia liczba przelaczen procesora: " + Context.getInstance().getAmountOfSwitchesOfRR()/AMOUNTOFCYCLES);
		 
		 scanner.close();
		 
		 
	}

}
