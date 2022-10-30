package simulation;

import java.util.ArrayList;

public class Rr {
	
	private ArrayList<Process> processes;
	private boolean isDone;
	private int quant;
	private int leftQuantOfTime;
	private int pos;
	
	public Rr(int quant) {

		this.processes = new ArrayList<Process>();
		this.isDone = false;
		this.quant = quant;
		this.leftQuantOfTime = quant;
		this.pos = 0;
	}
	
	public boolean isDone() {
		
		if(!this.isDone && Context.getInstance().getCurrentAmountOfProcessesForRR() == Context.getInstance().getMaxAmountOfProcesses()) {
			Context.getInstance().setFullTimeOfRR(Context.getInstance().getFullTimeOfRR() + Context.getInstance().getTime());
			this.isDone = true;
		}
		return this.isDone;
	}

	public void add(Process process) {
		
		if(!this.isDone) 
			processes.add(process);
		
		
	}
	
	public void next() {
		
		Context.getInstance().setAmountOfSwitchesOfRR(Context.getInstance().getAmountOfSwitchesOfRR() + 1);
		
		if(processes.get(pos).isDone())
			remove();
		
		pos++;
		this.leftQuantOfTime = this.quant;
		
		if(pos >= processes.size())
			pos = 0;
	}
	
	public Process remove() {
		
		Process tmp = processes.get(pos);
		
		Context.getInstance().setFinishingTimeOfRR(Context.getInstance().getFinishingTimeOfRR() + tmp.getFinishTime() - tmp.getArriveTime());
		
		processes.remove(pos);
		
		Context.getInstance().setCurrentAmountOfProcessesForRR(Context.getInstance().getCurrentAmountOfProcessesForRR() + 1);
	
		
		return tmp;
	}
	
	public void rRTick() {
		
		if(!this.isDone) {
			
			if(!processes.isEmpty() ) {
				
				if(processes.get(pos).getTimeLeft() == processes.get(pos).getDuration()) {
					processes.get(pos).setWaitingTime(Context.getInstance().getTime() - processes.get(pos).getArriveTime());
					Context.getInstance().setWaitingTimeOfRR(Context.getInstance().getWaitingTimeOfRR() + processes.get(pos).getWaitingTime());
					
					if(processes.get(pos).getWaitingTime() > Context.getInstance().getMaxWaitingTimeOfRR())
						Context.getInstance().setMaxWaitingTimeOfRR(processes.get(pos).getWaitingTime());
					
					if(processes.get(pos).getWaitingTime() > Context.getHungryprocesslimit())
						Context.getInstance().setHungryProcessesOfRR(Context.getInstance().getHungryProcessesOfRR() + 1);
				}
				
				processes.get(pos).tick();
				
				this.leftQuantOfTime--;
				
				if(this.leftQuantOfTime == 0 || processes.get(pos).isDone())
					next();

			}
			
		}
		
	}
	
}