package simulation;

//Szymon Sawczuk 260287
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstSim {
	
	private List<Processor> processors;
	private Random random;
	private int p, z;
	private boolean isLastProcess;
	private int time;
	
	public FirstSim(int n, int p, int z) {
		
		processors = new ArrayList<Processor>();
		
		for(int i = 0; i < n; i++) {
			processors.add(new Processor());
		}
		
		this.random = new Random();
		this.p = p;
		this.z = z;
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
			Context.getInstance().getFirstSimLoadList()[processors.indexOf(processor)] += processor.getLoad();
			
			if(!processor.isOverLoaded() && processor.getLoad() > 100) {
				processor.setOverLoaded(true);
				Context.getInstance().getAmountOfOverLoadsFirstSim()[processors.indexOf(processor)] += 1;
				Context.getInstance().getTimeOfOverLoadFirstSim()[processors.indexOf(processor)] -= time;
				
			}else if(processor.isOverLoaded() && processor.getLoad() < 100) {
				processor.setOverLoaded(false);
				Context.getInstance().getTimeOfOverLoadFirstSim()[processors.indexOf(processor)] += time;
			}
		}

	}
	
	public void addProcess(Process process, int processorIndex) {
		
		int tmpZ = z;
		int askedIndex = random.nextInt(processors.size());
		boolean isFound = false;
		int [] processorsAsked = new int[processors.size()];
		processorsAsked[processorIndex] = 1;
		
		while(tmpZ > 0 && !isFound) {
			
			while(processorsAsked[askedIndex] == 1) askedIndex = random.nextInt(processors.size());
			
			processorsAsked[askedIndex] = 1;
			Context.getInstance().setAmountOfAsksFirstSim(Context.getInstance().getAmountOfAsksFirstSim() + 1);
			if(processors.get(askedIndex).getLoad() <= p) {
				isFound = true;
				processors.get(askedIndex).addProcess(process);
				Context.getInstance().setAmountOfMigrationsFirstSim(Context.getInstance().getAmountOfMigrationsFirstSim() + 1);
			}
			tmpZ--;
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
		
		for(int i = 0; i < Context.getInstance().getFirstSimLoadList().length; i++) {
			sumOfLoads += Context.getInstance().getFirstSimLoadList()[i]/time;
		}
		
		double avarageOfLoads = sumOfLoads/Context.getInstance().getFirstSimLoadList().length;
		
		double deviation = 0;
		
		for(int i = 0; i < Context.getInstance().getFirstSimLoadList().length; i++) {
			deviation += Math.abs(Context.getInstance().getFirstSimLoadList()[i]/time - avarageOfLoads);
		}
		
		deviation /= Context.getInstance().getFirstSimLoadList().length;
		
		double timeOfOverLoads = 0;
		
		for(int i = 0; i < Context.getInstance().getTimeOfOverLoadFirstSim().length; i++) {
			
			if(Context.getInstance().getAmountOfOverLoadsFirstSim()[i] > 0)
				timeOfOverLoads += Context.getInstance().getTimeOfOverLoadFirstSim()[i]/Context.getInstance().getAmountOfOverLoadsFirstSim()[i];
			
		}
		
		timeOfOverLoads /= Context.getInstance().getTimeOfOverLoadFirstSim().length;
		
		System.out.println("--------------------Pierwszy algorytm--------------------");
		System.out.printf("Srednie obciazenie: %.2f\n", avarageOfLoads);
		System.out.printf("Srednie odchylenie: %.2f\n", deviation);
		System.out.printf("Ilosc zapytan o obciazenie: %.2f\n", Context.getInstance().getAmountOfAsksFirstSim());
		System.out.printf("Ilosc migracji: %.2f\n", Context.getInstance().getAmountOfMigrationsFirstSim());
		System.out.printf("Sredni czas przeciazen procesorow: %.2f\n", timeOfOverLoads);
		System.out.println();
	}

}
