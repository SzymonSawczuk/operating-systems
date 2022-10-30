package simulation;

import java.util.ArrayList;

public class Fcfs {
	
	private ArrayList<Process> processes;
	private boolean isDone;
	
	public Fcfs() {
		
		this.processes = new ArrayList<Process>();	
		this.isDone = false;
	}
	
	public boolean isDone() {
		
		if(!this.isDone && Context.getInstance().getCurrentAmountOfProcessesForFCFS() == Context.getInstance().getMaxAmountOfProcesses()) {
			Context.getInstance().setFullTimeOfFCFS(Context.getInstance().getFullTimeOfFCFS() + Context.getInstance().getTime());
			this.isDone = true;
		}
		return this.isDone;

		
	}
	
	public void enqueue(Process process) {
		
		if(!this.isDone) {
		
			processes.add(process);
			
		}

	}
	
	public Process dequeue() {
		
		Process tmp = processes.get(0);
		
		Context.getInstance().setFinishingTimeOfFCFS(Context.getInstance().getFinishingTimeOfFCFS() + tmp.getFinishTime() - tmp.getArriveTime());
		Context.getInstance().setAmountOfSwitchesOfFCFS(Context.getInstance().getAmountOfSwitchesOfFCFS() + 1);
		
		processes.remove(0);
		
		Context.getInstance().setCurrentAmountOfProcessesForFCFS(Context.getInstance().getCurrentAmountOfProcessesForFCFS() + 1);
		
		if(!processes.isEmpty()) {
			processes.get(0).setWaitingTime(Context.getInstance().getTime() - processes.get(0).getArriveTime());
			Context.getInstance().setWaitingTimeOfFCFS(Context.getInstance().getWaitingTimeOfFCFS() + processes.get(0).getWaitingTime());
			
			if(processes.get(0).getWaitingTime() > Context.getInstance().getMaxWaitingTimeOfFCFS())
				Context.getInstance().setMaxWaitingTimeOfFCFS(processes.get(0).getWaitingTime());
			
			if(processes.get(0).getWaitingTime() > Context.getHungryprocesslimit())
				Context.getInstance().setHungryProcessesOfFCFS(Context.getInstance().getHungryProcessesOfFCFS() + 1);
			
			
		}

		return tmp;
		
	}
	
	public void fcfsTick() {
		
		if(!this.isDone) {
			
			if(!processes.isEmpty() ) {

				processes.get(0).tick();
				
				if(processes.get(0).isDone())
					dequeue();

			
			}

		}	
		
		
	}

}
