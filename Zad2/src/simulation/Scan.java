package simulation;


public class Scan {
	
	private boolean isDone;
	private int[] disk;
	private int pos;
	private int tmpDirection;
	private int direction = -1;
	
	public Scan() {
		
		disk = new int[Context.getInstance().getMax()+1];
		this.isDone = false;
		pos = Context.getInstance().getCurrentPositionSCAN();
	}
	
	public boolean isDone() {
		
		if(!isDone && Context.getInstance().getAmountOfDoneTasksSCAN() == Context.getInstance().getMaxAmountOfTasks())
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
		Context.getInstance().setAmountOfDoneTasksSCAN(Context.getInstance().getAmountOfDoneTasksSCAN() + 1);

		if(disk[pos] > 0 && direction != 0) {
			tmpDirection = direction;
			direction = 0;
		}
		else if(disk[pos] == 0 && direction == 0 && !this.isDone)direction = tmpDirection;	

		
	}
	
	public void scanTick() {
		
		if(!this.isDone) {

			if(disk[pos] != 0) {
				dequeue();
			}
			
			if(!this.isDone()) {
				
				if(pos == Context.getInstance().getMin() && direction != 0) {
					direction = 1;
					
				}
				if(pos == Context.getInstance().getMax() && direction != 0) {
					direction = -1;
				}
				
				pos += direction;
				Context.getInstance().setDistanceSCAN(Context.getInstance().getDistanceSCAN() + Math.abs(direction));
				Context.getInstance().setCurrentPositionSCAN(pos);
				
			}
			
			
		}	
		
		
	}

}
