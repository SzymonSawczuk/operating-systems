package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sstf_EDF {
	
	private boolean isDone;
	private ArrayList<Task> normalTasks = new ArrayList<Task>();
	private ArrayList<Task> priorityTasks = new ArrayList<Task>();
	private int currentPosition;
	private int direction = -1;
	private EdfComparator comparator = new EdfComparator();
	private SstfComparator comparatorNormal = new SstfComparator();
	
	public Sstf_EDF() {
		
		this.isDone = false;
		currentPosition = Context.getInstance().getCurrentPositionSSTF_EDF();
	}
	
	public boolean isDone() {
		
		if(!isDone && (Context.getInstance().getAmountOfDoneNormalTasksSSTF_EDF() + Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF()) == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			if(task.getIsPriority() && 
					Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
				priorityTasks.add(task);
			}
			if(!task.getIsPriority() &&
					Context.getInstance().getAmountOfDoneNormalTasksSSTF_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount())
				normalTasks.add(task);
			
		}

	}
	
	private Task normalDequeue() {
		
		Task dequeuedTask = normalTasks.get(0);
		
		normalTasks.remove(0);
		Context.getInstance().setAmountOfDoneNormalTasksSSTF_EDF(Context.getInstance().getAmountOfDoneNormalTasksSSTF_EDF() + 1);
		
		if(!normalTasks.isEmpty())
			Collections.sort(normalTasks, comparatorNormal);
		
		return dequeuedTask;
		
	}
	
	private Task priorityDequeue() {
		
		Task dequeuedTask = priorityTasks.get(0);
		
		priorityTasks.remove(0);
		Context.getInstance().setAmountOfDonePriorityTasksSSTF_EDF(Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF() + 1);

		if(!priorityTasks.isEmpty())
			Collections.sort(priorityTasks, comparator);
		
		return dequeuedTask;

	}
	
	public void sstf_edfTick() {
		
		if(!this.isDone) {
			
			if(priorityTasks.isEmpty() || 
					Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF() == (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {

				if(!normalTasks.isEmpty() && 
						Context.getInstance().getAmountOfDoneNormalTasksSSTF_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount()) {
					
					if(currentPosition < normalTasks.get(0).getNumber())direction = 1;
					else if(currentPosition > normalTasks.get(0).getNumber())direction = -1;
					else direction = 0;
					
					if(currentPosition == normalTasks.get(0).getNumber()) {
						normalDequeue();
					}
					
					if(Context.getInstance().getAmountOfDoneNormalTasksSSTF_EDF() < Context.getInstance().getMaxAmountOfTasks() - Context.getInstance().getPriorityAmount()) {
						Context.getInstance().setDistanceSSTF_EDF(Context.getInstance().getDistanceSSTF_EDF() + Math.abs(direction));
						currentPosition += direction;
					}
					
					
				}

			}else if(!priorityTasks.isEmpty() && 
					Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)){
				
				if(currentPosition < priorityTasks.get(0).getNumber())direction = 1;
				else if(currentPosition > priorityTasks.get(0).getNumber())direction = -1;
				else direction = 0;
				
				if(currentPosition == priorityTasks.get(0).getNumber()) {
					priorityDequeue();
				}
				
				if(Context.getInstance().getAmountOfDonePriorityTasksSSTF_EDF() < (Context.getInstance().getMaxAmountOfTasks() * Context.getInstance().getPrioritypercent() / 100)) {
					currentPosition += direction;
					tickTasks();
					Context.getInstance().setDistanceSSTF_EDF(Context.getInstance().getDistanceSSTF_EDF() + Math.abs(direction));
				}
				
				
				
			}
			
			Context.getInstance().setCurrentPositionSSTF_EDF(currentPosition);

			
			
		

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
