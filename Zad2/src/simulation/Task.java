package simulation;

//Szymon Sawczuk 260287

public class Task{
	
	private int number;
	private int distanceFromCurrentPosition;
	private boolean isPriority;
	private int deadline;
	
	Task(int number, boolean isPriority, int deadline){
		
		this.number = number;
		this.distanceFromCurrentPosition = Math.abs(number - Context.getInstance().getCurrentPositionSSTF());
		this.isPriority = isPriority;
		this.deadline = deadline;
	}
	
	Task(Task task){
		
		this.number = task.number;
		this.distanceFromCurrentPosition = task.distanceFromCurrentPosition;
		this.isPriority = task.isPriority;
		this.deadline = task.deadline;
	}
	
	public void tick() {
		
		if(!isDone()) {
			deadline--;
		}
		
	}
	
	public boolean isDone() {
		
		return deadline == 0;
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDistanceFromCurrentPosition() {
		return distanceFromCurrentPosition;
	}

	public void setDistanceFromCurrentPosition(int distanceFromCurrentPosition) {
		this.distanceFromCurrentPosition = distanceFromCurrentPosition;
	}

	
	public void updateDistanceSstf() {
		
		this.distanceFromCurrentPosition = Math.abs(this.number - Context.getInstance().getCurrentPositionSSTF());
		
	}
	
	public void updateDistanceFCFS_EDF() {
		
		this.distanceFromCurrentPosition = Math.abs(this.number - Context.getInstance().getCurrentPositionFCFS_EDF());
		
	}

	public boolean getIsPriority() {
		return isPriority;
	}

	public void setIsPriority(boolean isPriority) {
		this.isPriority = isPriority;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	
	

}
