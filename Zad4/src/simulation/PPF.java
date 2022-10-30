package simulation;

import java.util.ArrayList;
import java.util.Random;

public class PPF {

	private ArrayList<Process> processes;
	private ArrayList<Page> frames;
	private int amountOfFrames;
	private Random rnd = new Random();
	private Lru lru = new Lru();
	private int pos;
	private int scuffle;
	private int amountOfErrorsInTime;
	
	
	public PPF(ArrayList<Process> processes, int amountOfFrames) {
		
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
		
//		while(i < processes.size() && amountOfFrames > 3) {
//
//			for(Page page : processes.get(i).getPages()) {
//				
//				if(getIndex(page, tmp) == -1) {
//					tmp.add(page);
//				}
//				
//			}
//			
//			s = tmp.size();
//			tmp.clear();
//			
//			f = s * amountOfFrames/all;
//			
//			if(f == 0) f = 1;
//			if(amountOfFrames < f) f = amountOfFrames - 3;
//			
//			processes.get(i).setAmountOfFrames(processes.get(i).getAmountOfFrames() + f);
//			amountOfFrames -= f;
//			
//			
//			
//			i++;
//		
//		}
		
		while(amountOfFrames > 0) {
			
			for(Page page : processes.get(i).getPages()) {
				
				if(getIndex(page, tmp) == -1) {
					tmp.add(page);
				}
				
			}
			
			s = tmp.size();
			tmp.clear();
			
			f = s * amountOfFrames/all;

			if(amountOfFrames >= f) {

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
		
		int k = 0;
		
		if(i < processes.size() && amountOfFrames == 0) {
			processes.get(i).setAmountOfFrames(processes.get(i).getAmountOfFrames() + 1);
			processes.get(k).setAmountOfFrames(processes.get(k).getAmountOfFrames() - 1);
			i++;k++;
		}
		
		updateStartingPosition();
		
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
		int ppfTime = Context.getInstance().getPpfTime();
		
		while(!areAllDone()) {
			
			if(scuffleTime == 0) {
				scuffleTime = Context.getInstance().getScuffleTime();
				if(amountOfErrorsInTime >= Context.getInstance().getScufflePercentage() * Context.getInstance().getScuffleTime()/100) scuffle++;
				amountOfErrorsInTime = 0;
			}
			
			if(ppfTime == 0) {
				calculatePPF();
//				for(Process process : processes) {
//					System.out.println(process.getAmountOfFrames() + " " + process.getStartingPos());
//				}
				ppfTime = Context.getInstance().getPpfTime();
			}
			
			currentProcess = null;
			
			while(currentProcess == null || currentProcess.isDone() || currentProcess.isFreezed()) currentProcess = processes.get(rnd.nextInt(processes.size()));
			
//			System.out.println(currentProcess.getSize());
//			showFrames();
			if(lru.tick(currentProcess, currentProcess.getPages().get(currentProcess.getPos()), frames, pos)) amountOfErrorsInTime++;

			pos++;
			currentProcess.setPos(currentProcess.getPos() + 1);
			
			if(currentProcess.getPos() >= currentProcess.getLength()) {
				currentProcess.setIsDone(true);
				
				for(int i = currentProcess.getStartingPos(); i < currentProcess.getStartingPos() + currentProcess.getAmountOfFrames(); i++) {
					frames.remove(i);
					frames.add(null);
					amountOfFrames++;
					
				}
				currentProcess.setAmountOfFrames(0);
				currentProcess.setSize(0);
				currentProcess.setStartingPos(frames.size() + 1);
				updateStartingPosition();
			}
			scuffleTime--;
			ppfTime--;
			
		}
		
		int result = 0;
		
		for(Process process : processes) {
			result += process.getAmountOfErrors();
		}
		
		Context.getInstance().setResultPPF(Context.getInstance().getResultPPF() + result);
		Context.getInstance().setScufflePPF(Context.getInstance().getScufflePPF() + scuffle);
		
	}
	
	private void calculatePPF() {
		
		for(Process process : processes) {
			
			if(!process.isDone()) {
				process.setPpf((double)process.getPpfErrors()/(double)Context.getInstance().getPpfTime());
				
				if(process.getPpf() >= Context.getInstance().getHighPPF()) {
					
					if(amountOfFrames>0) {
						frames.add(process.getStartingPos() + process.getAmountOfFrames(), null);
						frames.remove(frames.size() - 1);
						amountOfFrames--;
						process.setAmountOfFrames(process.getAmountOfFrames() + 1);
						process.setFreezed(false);
//						process.setPpfErrors(0);
						
						
					}else process.setFreezed(true);
					
				}else if(process.getPpf() <= Context.getInstance().getLowPPF()) {
					
					if(process.getAmountOfFrames() > 3) {
						
						frames.remove(process.getStartingPos() + process.getAmountOfFrames() - 1);
						process.setAmountOfFrames(process.getAmountOfFrames() - 1);
						frames.add(null);
						amountOfFrames++;
						updateStartingPosition();
						process.setSize(process.getAmountOfFrames() - countNull(process));
						
						
					}
					process.setFreezed(false);
//					process.setPpfErrors(0);
					
				}else {
					process.setFreezed(false);
//					process.setPpfErrors(0);
				}
				
				
				updateStartingPosition();
				process.setPpfErrors(0);
			}
		}
		
	}
	
	private int countNull(Process process) {
		int result = 0;
		for(int i = process.getStartingPos(); i < process.getStartingPos() + process.getAmountOfFrames(); i++) {
			if(frames.get(i) == null)result++;
		}
		
		return result;
	}
	
	private void updateStartingPosition() {
		int beginIndex = 0;
		for(Process process : processes) {
			if(!process.isDone()) {
				process.setStartingPos(beginIndex);
//				System.out.println(process.getAmountOfFrames() + " " + beginIndex);
				beginIndex+=process.getAmountOfFrames();
			}
			
		}
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
		
		System.out.println("Statystyki dla sterowania czestoscia bledow strony:");
		System.out.println("Ilosc bledow ogolnie: " + Context.getInstance().getResultPPF()/Context.getInstance().getAMOUNTOFCYCLES());
		System.out.println("Ilosc wystapien szamotan: " + Context.getInstance().getScufflePPF()/Context.getInstance().getAMOUNTOFCYCLES());
		
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
