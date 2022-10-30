package simulation;

import java.util.Random;

public class Process implements Comparable<Process>{
	
	private int arriveTime;
	private int waitingTime;
	private int duration;
	private int timeLeft;
	private int finishTime;
	private Random rnd;
	
	public Process(int arriveTime) {
		
		rnd = new Random();
		this.arriveTime = arriveTime;
		this.duration = rnd.nextInt(Context.getMaxDuration())+1;
		this.timeLeft = this.duration;
		
	}
	
	public Process(int arriveTime, Process process) {
		
		this.arriveTime = arriveTime;
		this.duration = process.duration;
		this.timeLeft = this.duration;
		
	}
	
//	Getters
	public int getArriveTime() {
		return arriveTime;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getFinishTime() {
		return finishTime;
	}
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
//	Setters
	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public void tick(){
		
		timeLeft--;
		
		if(isDone())
			finishTime = Context.getInstance().getTime();
			
	}
	
	public boolean isDone() {
		return timeLeft == 0;
	}
	
	@Override
	public String toString() {
		return arriveTime + " " + duration + " " + waitingTime;
	}

	@Override
	public int compareTo(Process o) {
		
		return Integer.compare(this.duration, o.duration);
	}
}
