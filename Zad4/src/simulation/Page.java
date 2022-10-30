package simulation;


//Szymon Sawczuk 260287
public class Page {
	
	private int value;
	private int distanceToNext;
	private int distanceToPrevious;
	private int lastAppearance;
	
	public Page(int value) {
		this.value = value;
		this.distanceToNext = 0;
		this.distanceToPrevious = 0;
		this.lastAppearance = 0;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public int getDistanceToNext() {
		return distanceToNext;
	}

	public void setDistanceToNext(int distanceToNext) {
		this.distanceToNext = distanceToNext;
	}

	public int getDistanceToPrevious() {
		return distanceToPrevious;
	}

	public void setDistanceToPrevious(int distanceToPrevious) {
		this.distanceToPrevious = distanceToPrevious;
	}

	public int getLastAppearance() {
		return lastAppearance;
	}

	public void setLastAppearance(int lastAppearance) {
		this.lastAppearance = lastAppearance;
	}

}
