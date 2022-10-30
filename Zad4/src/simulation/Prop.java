package simulation;

import java.util.ArrayList;
import java.util.Random;

public class Prop {

	private ArrayList<Process> processes;
	private ArrayList<Page> frames;
	private int amountOfFrames;
	private Random rnd = new Random();
	private Lru lru = new Lru();
	private int pos;
	private int scuffle;
	private int amountOfErrorsInTime;
	
	
	public Prop(ArrayList<Process> processes, int amountOfFrames) {
		
		this.processes = processes;
		this.amountOfFrames = amountOfFrames;
		this.frames = new ArrayList<>();
		this.pos = 0;
		this.scuffle = 0;
		this.amountOfErrorsInTime = 0;
		for(int i = 0; i < amountOfFrames; i++) frames.add(null);
		
	}
	
	public void allocate() {
		
		int f = 0;
		int i = 0;
		int s, all;
		ArrayList<Page> tmp = new ArrayList<Page>();
		
		for(Process process : processes) {
			
			for(Page page : process.getPages()) {
				
				if(getIndex(page, tmp) == -1) {
					tmp.add(page);
				}
				
			}
			
		}
		all = tmp.size();
		tmp.clear();
		
		while(amountOfFrames > 0) {

			if(amountOfFrames >= f) {
				
				for(Page page : processes.get(i).getPages()) {
					
					if(getIndex(page, tmp) == -1) {
						tmp.add(page);
					}
					
				}
				
				s = tmp.size();
				tmp.clear();
				
				f = s * amountOfFrames/all;
				
				if(f == 0) f = 1;
				if(f == amountOfFrames) f = amountOfFrames;
				
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
//			System.out.println(process.getAmountOfFrames() + " " + beginIndex);
			beginIndex+=process.getAmountOfFrames();
		}
		
	}
	
	private int getIndex(Page page, ArrayList<Page> pages) {
		
		for(int i = 0; i < pages.size(); i++) {
			if(pages.get(i).getValue() == page.getValue()) return i;
		}
		
		return -1;
		
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
		
		Context.getInstance().setResultProp(Context.getInstance().getResultProp() + result);
		Context.getInstance().setScuffleProp(Context.getInstance().getScuffleProp() + scuffle);
		
	}
	
//	private void showFrames() {
//		for(Page page : frames) {
//			System.out.print(page != null ? page.getValue() + " ": null + " ");
//		}
//		System.out.println();
//	}
	
	private boolean areAllDone() {
		
		for(Process process : processes) {
			if(!process.isDone())return false;
		}
		return true;
		
	}
	
	public void showStatistics() {
		
		System.out.println("Statystyki dla przydzialu proporcjonalnego:");
		System.out.println("Ilosc bledow ogolnie: " + Context.getInstance().getResultProp()/Context.getInstance().getAMOUNTOFCYCLES());
		System.out.println("Ilosc wystapien szamotan: " + Context.getInstance().getScuffleProp()/Context.getInstance().getAMOUNTOFCYCLES());
		
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
