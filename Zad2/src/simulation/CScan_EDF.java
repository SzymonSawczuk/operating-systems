package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CScan_EDF {
	
	private boolean isDone;
	private ArrayList<Task> priorityTasks = new ArrayList<Task>();
	private int[] disk;
	private int currentPosition;
	private int direction = -1;
	private int normalDirection = 1;
	private EdfComparator comparator = new EdfComparator();
	
	public CScan_EDF() {
		
		this.isDone = false;
		currentPosition = Context.getInstance().getCurrentPositionCSCAN_EDF();
		disk = new int[Context.getInstance().getMax()+1];
	}
	
	public boolean isDone() {
		
		if(!isDone && (Context.getInstance().getAmountOfDoneNormalTasksCSCAN_EDF() + Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF()) == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			if(task.getIsPriority() && 
					Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
				priorityTasks.add(task);
			}
			if(!task.getIsPriority() &&
					Context.getInstance().getAmountOfDoneNormalTasksCSCAN_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount())
				disk[task.getNumber()]++;
			
		}

	}
	
	private void normalDequeue() {
		

		disk[currentPosition]--;
		Context.getInstance().setAmountOfDoneNormalTasksCSCAN_EDF(Context.getInstance().getAmountOfDoneNormalTasksCSCAN_EDF() + 1);
		
		if(disk[currentPosition]>0) {
			normalDirection = 0;
		}
		else normalDirection = 1;
		
		
	}
	
	private Task priorityDequeue() {
		
		Task dequeuedTask = priorityTasks.get(0);
		
		priorityTasks.remove(0);
		Context.getInstance().setAmountOfDonePriorityTasksCSCAN_EDF(Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF() + 1);

		if(!priorityTasks.isEmpty())
			Collections.sort(priorityTasks, comparator);
		
		return dequeuedTask;

	}
	
	public void cscan_edfTick() {
		
		if(!this.isDone) {
			
			if(priorityTasks.isEmpty() || 
					Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF() == (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {

				if(Context.getInstance().getAmountOfDoneNormalTasksCSCAN_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount()) {
					
					if(disk[currentPosition] != 0) {
						normalDequeue();
					}
					
					if(Context.getInstance().getAmountOfDoneNormalTasksCSCAN_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount()) {
						
						if(disk[currentPosition] == 0 && currentPosition == Context.getInstance().getMax()) {
							currentPosition = Context.getInstance().getMin()-1;
						}
						
						currentPosition += normalDirection;
						Context.getInstance().setDistanceCSCAN_EDF(Context.getInstance().getDistanceCSCAN_EDF() + Math.abs(normalDirection));
						
					}
					
					
				}

			}else if(!priorityTasks.isEmpty() && 
					Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)){
				
				if(currentPosition < priorityTasks.get(0).getNumber())direction = 1;
				else if(currentPosition > priorityTasks.get(0).getNumber())direction = -1;
				else direction = 0;
				
				if(currentPosition == priorityTasks.get(0).getNumber()) {
					priorityDequeue();
				}
				
				if(Context.getInstance().getAmountOfDonePriorityTasksCSCAN_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
					currentPosition += direction;
					tickTasks();
					Context.getInstance().setDistanceCSCAN_EDF(Context.getInstance().getDistanceCSCAN_EDF() + Math.abs(direction));
				}

			}
			
			Context.getInstance().setCurrentPositionCSCAN_EDF(currentPosition);
			
		

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
