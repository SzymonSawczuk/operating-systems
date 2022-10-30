package simulation;

import java.util.ArrayList;

public class Fcfs {
	
	private boolean isDone;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private int currentPosition;
	
	public Fcfs() {
		
		this.isDone = false;
		currentPosition = Context.getInstance().getStart();
	}
	
	public boolean isDone() {
		
		if(!isDone && Context.getInstance().getAmountOfDoneTasksFCFS() == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			tasks.add(task);
			
		}

	}
	
	private Task dequeue() {
		
		Task dequeuedTask = null;
		
		if(!tasks.isEmpty()) {
			
		dequeuedTask = tasks.get(0);
		tasks.remove(0);
		Context.getInstance().setAmountOfDoneTasksFCFS(Context.getInstance().getAmountOfDoneTasksFCFS() + 1);
		
		Context.getInstance().setDistanceFCFS(Context.getInstance().getDistanceFCFS() + Math.abs(dequeuedTask.getNumber() - currentPosition));
		currentPosition = dequeuedTask.getNumber();
		
		}
		
		return dequeuedTask;
		
	}
	
	public void fcfsTick() {
		
		if(!this.isDone) {
			
			dequeue();

		}	
		
		
	}

}
