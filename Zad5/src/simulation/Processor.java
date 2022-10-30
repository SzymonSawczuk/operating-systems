package simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Szymon Sawczuk 260287
public class Processor {
	
	private int load;
	private List<Process> processes;
	private boolean isOverLoaded;
	
	public Processor() {
		this.processes = new ArrayList<Process>();
		this.load = 0;
		this.isOverLoaded = false;
	}

	public List<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}
	
	public int getLoad() {
		return load;
	}
	
	public void setLoad(int load) {
		this.load = load;
	}
	
	public void addProcess(Process process) {
		processes.add(process);
		load += process.getLoadInPercent();
		
		if(load > 100)overloadOfProcessor();
	}
	
	private void overloadOfProcessor() {
		
		for(Process process : processes) {
			process.setTime(process.getTime() + process.getTime() * ((load - 100)/100));
		}
		
	}
	
	public void tick() {
		
		if(processes.isEmpty()) return;
		
		Iterator<Process> iterator = processes.iterator();
		
		while(iterator.hasNext()) {
			Process process = iterator.next();
			process.tick();
			if(process.isDone()) {
				iterator.remove();
				load-=process.getLoadInPercent();
			}
		}
		
	}
	
	public void deleteProcess(Process process) {
		processes.remove(process);
		load-=process.getLoadInPercent();
	}
	
	public boolean isEmpty() {
		return processes.isEmpty();
	}

	public boolean isOverLoaded() {
		return isOverLoaded;
	}

	public void setOverLoaded(boolean isOverLoaded) {
		this.isOverLoaded = isOverLoaded;
	}
	
	

}
