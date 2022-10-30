package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Szymon Sawczuk 260287
public class ThirdSim {

	private List<Processor> processors;
	private Random random;
	private int p,r;
	private boolean isLastProcess;
	private int timeToCheck;
	private final int maxTime = 20;
	private int time;
	
	public ThirdSim(int n, int p, int r) {
		
		processors = new ArrayList<Processor>();
		
		for(int i = 0; i < n; i++) {
			processors.add(new Processor());
		}
		
		this.random = new Random();
		this.p = p;
		this.isLastProcess = false;
		this.timeToCheck = maxTime;
		this.r = r;
	}
	
	public List<Processor> getProcessors() {
		return processors;
	}
	
	public void setLastProcess(boolean isLastProcess) {
		this.isLastProcess = isLastProcess;
	}
	
	public void tick() {
		
		time = Context.getInstance().getTime();
		
		if(timeToCheck == 0) {
			timeToCheck = maxTime;
			doTakingOver();
		}
		
		for(Processor processor : processors) {
			processor.tick();
			Context.getInstance().getThirdSimLoadList()[processors.indexOf(processor)] += processor.getLoad();
			
			if(!processor.isOverLoaded() && processor.getLoad() > 100) {
				processor.setOverLoaded(true);
				Context.getInstance().getAmountOfOverLoadsThirdSim()[processors.indexOf(processor)] += 1;
				Context.getInstance().getTimeOfOverLoadThirdSim()[processors.indexOf(processor)] -= time;
				
			}else if(processor.isOverLoaded() && processor.getLoad() < 100) {
				processor.setOverLoaded(false);
				Context.getInstance().getTimeOfOverLoadThirdSim()[processors.indexOf(processor)] += time;
			}
		}
		
		timeToCheck--;
	}
	
	private void doTakingOver() {
		
		int [] processorsChecked = new int[processors.size()];
		int amountOfChecked = 0;
		
		for(Processor processor : processors) {
			
			if(processor.getLoad() <= r) {
				amountOfChecked++;
				processorsChecked[processors.indexOf(processor)] = 1;
				doAsking(processor, processorsChecked, amountOfChecked);
			}
			
		}
		
	}
	
	private void doAsking(Processor processor, int [] processorsChecked, int amountOfChecked) {
		
		int amountOfAsked = amountOfChecked;
		boolean isFound = false;
		int askedIndex = random.nextInt(processors.size());
		int [] processorsAsked = Arrays.copyOf(processorsChecked, processorsChecked.length);
		
		while(amountOfAsked < processors.size() - 1 && !isFound) {
			
			while(processorsAsked[askedIndex] == 1) askedIndex = random.nextInt(processors.size());
			
			processorsAsked[askedIndex] = 1;
			Context.getInstance().setAmountOfAsksThirdSim(Context.getInstance().getAmountOfAsksThirdSim() + 1);
			
			if(processors.get(askedIndex).getLoad() > p) {
				isFound = true;
				break;
			}
			amountOfAsked++;
		}
		
		if(isFound) {
			
			while(processors.get(askedIndex).getLoad() > p && processor.getLoad() < p) {
				processor.addProcess(processors.get(askedIndex).getProcesses().get(0));
				processors.get(askedIndex).deleteProcess(processors.get(askedIndex).getProcesses().get(0));
				Context.getInstance().setAmountOfMigrationsThirdSim(Context.getInstance().getAmountOfMigrationsThirdSim() + 1);
			}
			
		}
	}
	
	public void addProcess(Process process, int processorIndex) {
		
		if(processors.get(processorIndex).getLoad() <= p) {
			processors.get(processorIndex).addProcess(process);
			return;
		}
		
		int amountOfAsked = 0;
		int askedIndex = random.nextInt(processors.size());
		boolean isFound = false;
		int [] processorsAsked = new int[processors.size()];
		processorsAsked[processorIndex] = 1;
		
		while(amountOfAsked < processors.size() - 1 && !isFound) {
			
			while(processorsAsked[askedIndex] == 1) askedIndex = random.nextInt(processors.size());
			
			processorsAsked[askedIndex] = 1;
			Context.getInstance().setAmountOfAsksThirdSim(Context.getInstance().getAmountOfAsksThirdSim() + 1);
			if(processors.get(askedIndex).getLoad() <= p) {
				isFound = true;
				processors.get(askedIndex).addProcess(process);
				Context.getInstance().setAmountOfMigrationsThirdSim(Context.getInstance().getAmountOfMigrationsThirdSim() + 1);
			}
			amountOfAsked++;
		}
		
		if(!isFound) processors.get(processorIndex).addProcess(process);
		
	}
	
	public boolean isDone() {
		
		boolean isEmpty = false;
		
		for(Processor processor : processors) {
			if(processor.getProcesses().isEmpty())isEmpty = true;
			else {
				isEmpty = false;
				break;
			}
		}
		
		return isLastProcess && isEmpty;
	}
	
	public void show() {
		
		for(Processor processor : processors) {
			for(Process process : processor.getProcesses()) {
				System.out.print(process.getTime() + " ");
			}System.out.println();
			
		}System.out.println();
		
		System.out.println(processors.isEmpty() + " " + isLastProcess);

	}
	
	public void showResults() {
		
		double sumOfLoads = 0;
		
		for(int i = 0; i < Context.getInstance().getThirdSimLoadList().length; i++) {
			sumOfLoads += Context.getInstance().getThirdSimLoadList()[i]/time;
		}
		
		double avarageOfLoads = sumOfLoads/Context.getInstance().getThirdSimLoadList().length;
		
		double deviation = 0;
		
		for(int i = 0; i < Context.getInstance().getThirdSimLoadList().length; i++) {
			deviation += Math.abs(Context.getInstance().getThirdSimLoadList()[i]/time - avarageOfLoads);
		}
		
		deviation /= Context.getInstance().getThirdSimLoadList().length;
		
		double timeOfOverLoads = 0;
		
		for(int i = 0; i < Context.getInstance().getTimeOfOverLoadThirdSim().length; i++) {
			
			if(Context.getInstance().getAmountOfOverLoadsThirdSim()[i] > 0)
				timeOfOverLoads += Context.getInstance().getTimeOfOverLoadThirdSim()[i]/Context.getInstance().getAmountOfOverLoadsThirdSim()[i];
			
		}
		
		timeOfOverLoads /= Context.getInstance().getTimeOfOverLoadThirdSim().length;
		
		System.out.println("--------------------Trzeci algorytm--------------------");
		System.out.printf("Srednie obciazenie: %.2f\n", avarageOfLoads);
		System.out.printf("Srednie odchylenie: %.2f\n", deviation);
		System.out.printf("Ilosc zapytan o obciazenie: %.2f\n", Context.getInstance().getAmountOfAsksThirdSim());
		System.out.printf("Ilosc migracji: %.2f\n", Context.getInstance().getAmountOfMigrationsThirdSim());
		System.out.printf("Sredni czas przeciazen procesorow: %.2f\n", timeOfOverLoads);
		System.out.println();
		
	}
	
}
