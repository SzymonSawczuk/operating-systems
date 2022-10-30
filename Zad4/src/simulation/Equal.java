package simulation;

import java.util.ArrayList;
import java.util.Random;

public class Equal {

	private ArrayList<Process> processes;
	private ArrayList<Page> frames;
	private int amountOfFrames;
	private Random rnd = new Random();
	private Lru lru = new Lru();
	private int pos;
	private int scuffle;
	private int amountOfErrorsInTime;
	
	
	public Equal(ArrayList<Process> processes, int amountOfFrames) {
		
		this.processes = processes;
		this.amountOfFrames = amountOfFrames;
		this.frames = new ArrayList<>();
		this.pos = 0;
		this.scuffle = 0;
		this.amountOfErrorsInTime = 0;
		for(int i = 0; i < amountOfFrames; i++) frames.add(null);
		
	}
	
	public void allocate() {
		
		int f = amountOfFrames/processes.size();
		if(f == 0)f=1;
		int i = 0;
		
		while(amountOfFrames > 0) {
			
			if(f >= amountOfFrames) {
				processes.get(i).setAmountOfFrames(processes.get(i).getAmountOfFrames() + f);
				amountOfFrames -= f;
			}
			else {
				processes.get(i).setAmountOfFrames(processes.get(i).getAmountOfFrames() + 1);
				amountOfFrames -= 1;
			}
			
			i++;
			
			if(i >= processes.size()) i = 0;
		
		}
		
		int beginIndex = 0;
		for(Process process : processes) {
			process.setStartingPos(beginIndex);
//			System.out.println(process.getAmountOfFrames());
			beginIndex+=process.getAmountOfFrames();
		}
		
	}
	
	public void simulate() {
		
		Process currentProcess;
		int scuffleTime = Context.getInstance().getScuffleTime();
		
		while(!areAllDone()) {
			
			if(scuffleTime == 0) {
				scuffleTime = Context.getInstance().getScuffleTime();
				if(amountOfErrorsInTime >= Context.getInstance().getScufflePercentage() * Context.getInstance().getScuffleTime()/100) scuffle++;
				amountOfErrorsInTime = 0;
			}
			
			currentProcess = null;
			
			while(currentProcess == null || currentProcess.isDone()) currentProcess = processes.get(rnd.nextInt(processes.size()));
			
//			System.out.println(currentProcess.getSize());
//			showFrames();
			if(lru.tick(currentProcess, currentProcess.getPages().get(currentProcess.getPos()), frames, pos)) amountOfErrorsInTime++;

			pos++;
			currentProcess.setPos(currentProcess.getPos() + 1);
			
			if(currentProcess.getPos() >= currentProcess.getLength()) currentProcess.setIsDone(true);
			scuffleTime--;
			
		}
		
		int result = 0;
		
		for(Process process : processes) {
			result += process.getAmountOfErrors();
		}
		
		Context.getInstance().setResultEqual(Context.getInstance().getResultEqual() + result);
		Context.getInstance().setScuffleEqual(Context.getInstance().getScuffleEqual() + scuffle);
		
	}
	
//	private void showFrames() {
//		for(Page page : frames) {
//			System.out.print(page != null ? page.getValue() + " ": null + " ");
//		}
//		System.out.println();
//	}
//	
	private boolean areAllDone() {
		
		for(Process process : processes) {
			if(!process.isDone())return false;
		}
		return true;
		
	}
	
	public void showStatistics() {
		
		
		
		System.out.println("Statystyki dla przydzialu rownego:");
		System.out.println("Ilosc bledow ogolnie: " + Context.getInstance().getResultEqual()/Context.getInstance().getAMOUNTOFCYCLES());
		System.out.println("Ilosc wystapien szamotan: " + Context.getInstance().getScuffleEqual()/Context.getInstance().getAMOUNTOFCYCLES());
		
//		for(int i = 0; i < processes.size(); i++) {
//			System.out.println("Ilosc bledow dla procesu nr " + (i + 1) + ": " + processes.get(i).getAmountOfErrors());
//		}
		
	}
	
	public void reset() {
		for(Process process : processes) {
			process.reset();
		}
	}
	
	
	
}
