package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Szymon Sawczuk 260287
public class SecondSim {

	private List<Processor> processors;
	private Random random;
	private int p;
	private boolean isLastProcess;
	private int time;
	
	public SecondSim(int n, int p) {
		
		processors = new ArrayList<Processor>();
		
		for(int i = 0; i < n; i++) {
			processors.add(new Processor());
		}
		
		this.random = new Random();
		this.p = p;
		this.isLastProcess = false;
	}
	
	public List<Processor> getProcessors() {
		return processors;
	}
	
	public void setLastProcess(boolean isLastProcess) {
		this.isLastProcess = isLastProcess;
	}
	
	public void tick() {
		
		time = Context.getInstance().getTime();
		
		for(Processor processor : processors) {
			processor.tick();
			Context.getInstance().getSecondSimLoadList()[processors.indexOf(processor)] += processor.getLoad();
			
			if(!processor.isOverLoaded() && processor.getLoad() > 100) {
				processor.setOverLoaded(true);
				Context.getInstance().getAmountOfOverLoadsSecondSim()[processors.indexOf(processor)] += 1;
				Context.getInstance().getTimeOfOverLoadSecondSim()[processors.indexOf(processor)] -= time;
				
			}else if(processor.isOverLoaded() && processor.getLoad() < 100) {
				processor.setOverLoaded(false);
				Context.getInstance().getTimeOfOverLoadSecondSim()[processors.indexOf(processor)] += time;
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
			Context.getInstance().setAmountOfAsksSecondSim(Context.getInstance().getAmountOfAsksSecondSim() + 1);
			if(processors.get(askedIndex).getLoad() <= p) {
				isFound = true;
				processors.get(askedIndex).addProcess(process);
				Context.getInstance().setAmountOfMigrationsSecondSim(Context.getInstance().getAmountOfMigrationsSecondSim() + 1);
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
		
		for(int i = 0; i < Context.getInstance().getSecondSimLoadList().length; i++) {
			sumOfLoads += Context.getInstance().getSecondSimLoadList()[i]/time;
		}
		
		double avarageOfLoads = sumOfLoads/Context.getInstance().getSecondSimLoadList().length;
		
		double deviation = 0;
		
		for(int i = 0; i < Context.getInstance().getSecondSimLoadList().length; i++) {
			deviation += Math.abs(Context.getInstance().getSecondSimLoadList()[i]/time - avarageOfLoads);
		}
		
		deviation /= Context.getInstance().getSecondSimLoadList().length;
		
		double timeOfOverLoads = 0;
		
		for(int i = 0; i < Context.getInstance().getTimeOfOverLoadSecondSim().length; i++) {
			
			if(Context.getInstance().getAmountOfOverLoadsSecondSim()[i] > 0)
				timeOfOverLoads += Context.getInstance().getTimeOfOverLoadSecondSim()[i]/Context.getInstance().getAmountOfOverLoadsSecondSim()[i];
			
		}
		
		timeOfOverLoads /= Context.getInstance().getTimeOfOverLoadSecondSim().length;
		
		System.out.println("--------------------Drugi algorytm--------------------");
		System.out.printf("Srednie obciazenie: %.2f\n", avarageOfLoads);
		System.out.printf("Srednie odchylenie: %.2f\n", deviation);
		System.out.printf("Ilosc zapytan o obciazenie: %.2f\n", Context.getInstance().getAmountOfAsksSecondSim());
		System.out.printf("Ilosc migracji: %.2f\n", Context.getInstance().getAmountOfMigrationsSecondSim());
		System.out.printf("Sredni czas przeciazen procesorow: %.2f\n", timeOfOverLoads);
		System.out.println();
		
	}
	
}
