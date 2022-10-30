package simulation;

import java.util.Comparator;

public class EdfComparator implements Comparator<Task>{

	@Override
	public int compare(Task t1, Task t2) {
		
		return Integer.compare(t1.getDeadline(), t2.getDeadline());
	}

}
