package simulation;


public class CScan {
	
	private boolean isDone;
	private int[] disk;
	private int pos;
	private int direction = 1;
	
	public CScan() {
		
		disk = new int[Context.getInstance().getMax()+1];
		this.isDone = false;
		pos = Context.getInstance().getCurrentPositionCSCAN();
	}
	
	public boolean isDone() {
		
		if(!isDone && Context.getInstance().getAmountOfDoneTasksCSCAN() == Context.getInstance().getMaxAmountOfTasks())
			isDone = true;
		
		return this.isDone;

		
	}
	
	public void enqueue(Task task) {
		
		if(!this.isDone) {
		
			disk[task.getNumber()]++;
			
		}
		

	}
	
	private void dequeue() {
		
		
		disk[pos]--;
		Context.getInstance().setAmountOfDoneTasksCSCAN(Context.getInstance().getAmountOfDoneTasksCSCAN() + 1);

		if(disk[pos]>0) {
			direction = 0;
		}
		else direction = 1;	
		
		
		
		
	}
	
	public void cscanTick() {
		
		if(!this.isDone) {

			if(disk[pos] != 0) {
				dequeue();
			}
			
			if(!this.isDone()) {
				
				if(disk[pos] == 0 && pos == Context.getInstance().getMax()) {
					pos = Context.getInstance().getMin()-1;
				}
				
				pos += direction;
				Context.getInstance().setDistanceCSCAN(Context.getInstance().getDistanceCSCAN() + Math.abs(direction));
				Context.getInstance().setCurrentPositionCSCAN(pos);
				
			}

		}	
		
		
	}

}
