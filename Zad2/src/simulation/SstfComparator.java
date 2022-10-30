package simulation;

import java.util.Comparator;

public class SstfComparator implements Comparator<Task>{

	@Override
	public int compare(Task t1, Task t2) {
		
		t1.updateDistanceSstf();
		t2.updateDistanceSstf();
		
		return Integer.compare(t1.getDistanceFromCurrentPosition(), t2.getDistanceFromCurrentPosition());
	}

}
