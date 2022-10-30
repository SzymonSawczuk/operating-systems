package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Scan_EDF {
	
	private boolean isDone;
	private ArrayList<Task> priorityTasks = new ArrayList<Task>();
	private int[] disk;
	private int currentPosition;
	private int direction = -1;
	private int tmpDirection;
	private int normalDirection = -1;
	private EdfComparator comparator = new EdfComparator();
	
	public Scan_EDF() {
		
		this.isDone = false;
		currentPosition = Context.getInstance().getCurrentPositionSCAN_EDF();
		disk = new int[Context.getInstance().getMax()+1];
	}
	
	public boolean isDone() {
		
		if(!isDone && (Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() + Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF()) == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			if(task.getIsPriority() && 
					Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
				priorityTasks.add(task);
			}
			if(!task.getIsPriority() &&
					Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount())
				disk[task.getNumber()]++;
			
		}

	}
	
	private void normalDequeue() {
		

		disk[currentPosition]--;
		Context.getInstance().setAmountOfDoneNormalTasksSCAN_EDF(Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() + 1);
		
		if(disk[currentPosition] > 0 && normalDirection != 0) {
			tmpDirection = normalDirection;
			normalDirection = 0;
		}
		else if(disk[currentPosition] == 0 && normalDirection == 0 &&
				Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount()))normalDirection = tmpDirection;
		
		
	}
	
	private Task priorityDequeue() {
		
		Task dequeuedTask = priorityTasks.get(0);
		
		priorityTasks.remove(0);
		Context.getInstance().setAmountOfDonePriorityTasksSCAN_EDF(Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF() + 1);

		if(!priorityTasks.isEmpty())
			Collections.sort(priorityTasks, comparator);
		
		return dequeuedTask;

	}
	
	public void scan_edfTick() {
		
		if(!this.isDone) {
			
			if(priorityTasks.isEmpty() || 
					Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF() == (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {

				if(Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount())) {
					
					if(disk[currentPosition] != 0) {
						normalDequeue();
					}

					if(Context.getInstance().getAmountOfDoneNormalTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount())) {
						
						if(currentPosition == Context.getInstance().getMin() && normalDirection != 0) {
							normalDirection = 1;
							
						}
						if(currentPosition == Context.getInstance().getMax() && normalDirection != 0) {
							normalDirection = -1;
						}
						
						currentPosition += normalDirection;
						Context.getInstance().setDistanceSCAN_EDF(Context.getInstance().getDistanceSCAN_EDF() + Math.abs(normalDirection));
					}
					
				}

			}else if(!priorityTasks.isEmpty() && 
					Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)){
				
				if(currentPosition < priorityTasks.get(0).getNumber())direction = 1;
				else if(currentPosition > priorityTasks.get(0).getNumber())direction = -1;
				else direction = 0;
				
				if(currentPosition == priorityTasks.get(0).getNumber()) {
					priorityDequeue();
				}
				
				if(Context.getInstance().getAmountOfDonePriorityTasksSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
					currentPosition += direction;
					tickTasks();
					Context.getInstance().setDistanceSCAN_EDF(Context.getInstance().getDistanceSCAN_EDF() + Math.abs(direction));
				}

			}
			
			Context.getInstance().setCurrentPositionSCAN_EDF(currentPosition);

		}	
		
		
	}
	
	private void tickTasks() {
		
		for(Iterator<Task> iterator = priorityTasks.iterator(); iterator.hasNext();) {
			
			Task task = iterator.next();
			
			task.tick();
			
			if(task.isDone()) {
				
				if(task.equals(priorityTasks.get(0))) {
					
					iterator.remove();
					Collections.sort(priorityTasks, comparator);
					iterator = priorityTasks.iterator();
					
				}else iterator.remove();
			
			}
			
		}
		
	}

}
