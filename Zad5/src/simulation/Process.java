package simulation;

//Szymon Sawczuk 260287
public class Process {

	private int loadInPercent;
	private int time;
	
	public Process(int loadInPercent, int time) {
		
		this.loadInPercent = loadInPercent;
		this.time = time;
		
	}

	public int getLoadInPercent() {
		return loadInPercent;
	}

	public void setLoadInPercent(int loadInPercent) {
		this.loadInPercent = loadInPercent;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean isDone() {
		return time == 0;
	}
	
	public void tick() {
		
		if(isDone())return;
		
		this.time--;
	}
	
}
