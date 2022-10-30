package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class Sjf{
	
	private ArrayList<Process> processes;
	private boolean isDone;
	
	public Sjf() {
		
		this.processes = new ArrayList<Process>();
		this.isDone = false;
	}
	
	public boolean isDone() {
		
		if(!this.isDone && Context.getInstance().getCurrentAmountOfProcessesForSJF() == Context.getInstance().getMaxAmountOfProcesses()) {
			Context.getInstance().setFullTimeOfSJF(Context.getInstance().getFullTimeOfSJF() + Context.getInstance().getTime());
			this.isDone = true;
		}
		return this.isDone;
	}

	public void add(Process process) {
		
		if(!this.isDone) {
			
			processes.add(process);
			
		}
		
		
	}
	
	public Process remove() {
		
		Process tmp = processes.get(0);
		
		Context.getInstance().setFinishingTimeOfSJF(Context.getInstance().getFinishingTimeOfSJF() + tmp.getFinishTime() - tmp.getArriveTime());
		Context.getInstance().setAmountOfSwitchesOfSJF(Context.getInstance().getAmountOfSwitchesOfSJF() + 1);
		
		processes.remove(0);
		
		Collections.sort(processes);
		
		Context.getInstance().setCurrentAmountOfProcessesForSJF(Context.getInstance().getCurrentAmountOfProcessesForSJF() + 1);
		
		if(!processes.isEmpty()) {
			processes.get(0).setWaitingTime(Context.getInstance().getTime() - processes.get(0).getArriveTime());
			Context.getInstance().setWaitingTimeOfSJF(Context.getInstance().getWaitingTimeOfSJF() + processes.get(0).getWaitingTime());
			
			if(processes.get(0).getWaitingTime() > Context.getInstance().getMaxWaitingTimeOfSJF())
				Context.getInstance().setMaxWaitingTimeOfSJF(processes.get(0).getWaitingTime());
			
			if(processes.get(0).getWaitingTime() > Context.getHungryprocesslimit())
				Context.getInstance().setHungryProcessesOfSJF(Context.getInstance().getHungryProcessesOfSJF() + 1);
		}
		
		return tmp;
	}
	
	public void sjfTick() {
		
		if(!this.isDone) {
			
			if(!processes.isEmpty() ) {
				
				processes.get(0).tick();
				
				if(processes.get(0).isDone())
					remove();

			}
			
		}
		
	}
	
}
