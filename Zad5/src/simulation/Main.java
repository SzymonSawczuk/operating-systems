package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Szymon Sawczuk 260287
public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Podaj ilosc procesorow: ");
		final int N = scanner.nextInt();
		
		System.out.print("Podaj ilosc procesow: ");
		final int LENGTHOFPROCESSES = scanner.nextInt();
		
		System.out.print("Podaj prog p (prog migracji): ");
		final int P = scanner.nextInt();
		
		System.out.print("Podaj prog z (minimalny prog przejecia procesow): ");
		final int Z = scanner.nextInt();
		
		System.out.print("Podaj r (ilosc zapytan w pierwszym algorytmie): ");
		final int R = scanner.nextInt();
		
		System.out.print("Podaj gorna granice udzialu pojedynczego procesu: ");
		final int MAXLOAD = scanner.nextInt();
		System.out.println();
		
		Context.getInstance().setFirstSimLoadList(new int[N]);
		Context.getInstance().setSecondSimLoadList(new int[N]);
		Context.getInstance().setThirdSimLoadList(new int[N]);
		Context.getInstance().setAmountOfOverLoadsFirstSim(new int[N]);
		Context.getInstance().setAmountOfOverLoadsSecondSim(new int[N]);
		Context.getInstance().setAmountOfOverLoadsThirdSim(new int[N]);
		Context.getInstance().setTimeOfOverLoadFirstSim(new int[N]);
		Context.getInstance().setTimeOfOverLoadSecondSim(new int[N]);
		Context.getInstance().setTimeOfOverLoadThirdSim(new int[N]);
		
		
		Random random = new Random();
		double p = 0.8;
		FirstSim firstSim = new FirstSim(N, P, R);
		SecondSim secondSim = new SecondSim(N, P);
		ThirdSim thirdSim = new ThirdSim(N, P, Z);
		int processorIndex;
		
		List<Process> processes = new ArrayList<Process>();
		
		for (int i = 0; i < LENGTHOFPROCESSES; i++) {
			
			processes.add(new Process(random.nextInt(MAXLOAD - 1) + 2, random.nextInt(400 - 50) + 50));
			
		}
		
		while(!firstSim.isDone() || !secondSim.isDone() || !thirdSim.isDone()) {
			
			Context.getInstance().setTime(Context.getInstance().getTime() + 1);
			
			if(!firstSim.isDone())firstSim.tick();
			if(!secondSim.isDone())secondSim.tick();
			if(!thirdSim.isDone())thirdSim.tick();
			
			if(random.nextDouble() >= p && !processes.isEmpty()) {
				
				processorIndex = random.nextInt(N - 1) + 1;
				
				if(!firstSim.isDone()) {
					Process processToFirstSim = new Process(processes.get(0).getLoadInPercent(), processes.get(0).getTime());
					firstSim.addProcess(processToFirstSim, processorIndex);
				}
				
				if(!secondSim.isDone()) {
					Process processToSecondSim= new Process(processes.get(0).getLoadInPercent(), processes.get(0).getTime());
					secondSim.addProcess(processToSecondSim, processorIndex);
				}
				
				if(!thirdSim.isDone()) {
					Process processToThirdSim= new Process(processes.get(0).getLoadInPercent(), processes.get(0).getTime());
					thirdSim.addProcess(processToThirdSim, processorIndex);
				}
				
				processes.remove(0);
				
				if(processes.isEmpty()) {
					firstSim.setLastProcess(true);
					secondSim.setLastProcess(true);
					thirdSim.setLastProcess(true);
				}
				p = 0.8;
				
			}else if(random.nextDouble() < p && !processes.isEmpty()) p-=0.2;
			
//			firstSim.show();
			
		}
		
		firstSim.showResults();
		secondSim.showResults();
		thirdSim.showResults();
		
		scanner.close();
		
	}

}
