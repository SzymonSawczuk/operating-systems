package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class Sstf {
	
	private boolean isDone;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private SstfComparator comparator = new SstfComparator();
	private int pos, direction = 1;
	
	public Sstf() {
		
		pos = Context.getInstance().getStart();
		this.isDone = false;
	}
	
	public boolean isDone() {
		
		if(!isDone && Context.getInstance().getAmountOfDoneTasksSSTF() == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			tasks.add(task);
			
		}
		

	}
	
	private Task dequeue() {
		
		Task dequeuedTask = tasks.get(0);
	
		tasks.remove(0);
	
		
		Context.getInstance().setAmountOfDoneTasksSSTF(Context.getInstance().getAmountOfDoneTasksSSTF() + 1);
		
		Context.getInstance().setDistanceSSTF(Context.getInstance().getDistanceSSTF() + Math.abs(dequeuedTask.getNumber() - Context.getInstance().getCurrentPositionSSTF()));
		
		Context.getInstance().setCurrentPositionSSTF(dequeuedTask.getNumber());
		
		if(!tasks.isEmpty())
			Collections.sort(tasks, comparator);

		return dequeuedTask;
		
	}
	
	public void sstfTick() {
		
		if(!this.isDone) {
			
			if(!tasks.isEmpty()) {

				if(pos < tasks.get(0).getNumber())direction = 1;
				else if(pos > tasks.get(0).getNumber()) direction = -1;
				else direction = 0;
				
				if(pos == tasks.get(0).getNumber())
					dequeue();
				
				pos += direction;
			}
			

		}	
		
		
	}

}
