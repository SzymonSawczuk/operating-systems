package simulation;

public class Context {

	private final static Context instance = new Context();
	private int maxAmountOfTasks;
	private int start;
	private int min = 1;
	private int max;
	private int prioritypercent;
	private int priorityAmount;
	
	private int distanceFCFS;
	private int amountOfDoneTasksFCFS = 0;
	
	private int currentPositionSSTF = start;
	private int distanceSSTF;
	private int amountOfDoneTasksSSTF = 0;
	
	private int currentPositionSCAN = start;
	private int distanceSCAN;
	private int amountOfDoneTasksSCAN = 0;
	
	private int currentPositionCSCAN = start;
	private int distanceCSCAN;
	private int amountOfDoneTasksCSCAN = 0;
	
	private int currentPositionFCFS_EDF = start;
	private int distanceFCFS_EDF;
	private int amountOfDoneNormalTasksFCFS_EDF = 0;
	private int amountOfDonePriorityTasksFCFS_EDF = 0;
	
	private int currentPositionSSTF_EDF = start;
	private int distanceSSTF_EDF;
	private int amountOfDoneNormalTasksSSTF_EDF = 0;
	private int amountOfDonePriorityTasksSSTF_EDF = 0;
	
	private int currentPositionSCAN_EDF = start;
	private int distanceSCAN_EDF;
	private int amountOfDoneNormalTasksSCAN_EDF = 0;
	private int amountOfDonePriorityTasksSCAN_EDF = 0;
	
	private int currentPositionCSCAN_EDF = start;
	private int distanceCSCAN_EDF;
	private int amountOfDoneNormalTasksCSCAN_EDF = 0;
	private int amountOfDonePriorityTasksCSCAN_EDF = 0;
	
	public static Context getInstance() {
		return instance;
	}

	public int getDistanceFCFS() {
		return distanceFCFS;
	}

	public void setDistanceFCFS(int distanceFCFS) {
		this.distanceFCFS = distanceFCFS;
	}

	public int getMaxAmountOfTasks() {
		return maxAmountOfTasks;
	}

	public void setMaxAmountOfTasks(int maxAmountOfTasks) {
		this.maxAmountOfTasks = maxAmountOfTasks;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getAmountOfDoneTasksFCFS() {
		return amountOfDoneTasksFCFS;
	}

	public void setAmountOfDoneTasksFCFS(int amountOfDoneTasksFCFS) {
		this.amountOfDoneTasksFCFS = amountOfDoneTasksFCFS;
	}

	public int getCurrentPositionSSTF() {
		return currentPositionSSTF;
	}

	public void setCurrentPositionSSTF(int currentPositionSSTF) {
		this.currentPositionSSTF = currentPositionSSTF;
	}

	public int getDistanceSSTF() {
		return distanceSSTF;
	}

	public void setDistanceSSTF(int distanceSSTF) {
		this.distanceSSTF = distanceSSTF;
	}

	public int getAmountOfDoneTasksSSTF() {
		return amountOfDoneTasksSSTF;
	}

	public void setAmountOfDoneTasksSSTF(int amountOfDoneTasksSSTF) {
		this.amountOfDoneTasksSSTF = amountOfDoneTasksSSTF;
	}

	public int getCurrentPositionSCAN() {
		return currentPositionSCAN;
	}

	public void setCurrentPositionSCAN(int currentPositionSCAN) {
		this.currentPositionSCAN = currentPositionSCAN;
	}

	public int getDistanceSCAN() {
		return distanceSCAN;
	}

	public void setDistanceSCAN(int distanceSCAN) {
		this.distanceSCAN = distanceSCAN;
	}

	public int getAmountOfDoneTasksSCAN() {
		return amountOfDoneTasksSCAN;
	}

	public void setAmountOfDoneTasksSCAN(int amountOfDoneTasksSCAN) {
		this.amountOfDoneTasksSCAN = amountOfDoneTasksSCAN;
	}

	public int getCurrentPositionCSCAN() {
		return currentPositionCSCAN;
	}

	public void setCurrentPositionCSCAN(int currentPositionCSCAN) {
		this.currentPositionCSCAN = currentPositionCSCAN;
	}

	public int getDistanceCSCAN() {
		return distanceCSCAN;
	}

	public void setDistanceCSCAN(int distanceCSCAN) {
		this.distanceCSCAN = distanceCSCAN;
	}

	public int getAmountOfDoneTasksCSCAN() {
		return amountOfDoneTasksCSCAN;
	}

	public void setAmountOfDoneTasksCSCAN(int amountOfDoneTasksCSCAN) {
		this.amountOfDoneTasksCSCAN = amountOfDoneTasksCSCAN;
	}

	public int getPrioritypercent() {
		return prioritypercent;
	}

	public void setPrioritypercent(int prioritypercent) {
		this.prioritypercent = prioritypercent;
	}

	public int getCurrentPositionFCFS_EDF() {
		return currentPositionFCFS_EDF;
	}

	public void setCurrentPositionFCFS_EDF(int currentPositionFCFS_EDF) {
		this.currentPositionFCFS_EDF = currentPositionFCFS_EDF;
	}

	public int getDistanceFCFS_EDF() {
		return distanceFCFS_EDF;
	}

	public void setDistanceFCFS_EDF(int distanceFCFS_EDF) {
		this.distanceFCFS_EDF = distanceFCFS_EDF;
	}

	public int getAmountOfDoneNormalTasksFCFS_EDF() {
		return amountOfDoneNormalTasksFCFS_EDF;
	}

	public void setAmountOfDoneNormalTasksFCFS_EDF(int amountOfDoneNormalTasksFCFS_EDF) {
		this.amountOfDoneNormalTasksFCFS_EDF = amountOfDoneNormalTasksFCFS_EDF;
	}

	public int getAmountOfDonePriorityTasksFCFS_EDF() {
		return amountOfDonePriorityTasksFCFS_EDF;
	}

	public void setAmountOfDonePriorityTasksFCFS_EDF(int amountOfDonePriorityTasksFCFS_EDF) {
		this.amountOfDonePriorityTasksFCFS_EDF = amountOfDonePriorityTasksFCFS_EDF;
	}

	public int getCurrentPositionSSTF_EDF() {
		return currentPositionSSTF_EDF;
	}

	public void setCurrentPositionSSTF_EDF(int currentPositionSSTF_EDF) {
		this.currentPositionSSTF_EDF = currentPositionSSTF_EDF;
	}

	public int getDistanceSSTF_EDF() {
		return distanceSSTF_EDF;
	}

	public void setDistanceSSTF_EDF(int distanceSSTF_EDF) {
		this.distanceSSTF_EDF = distanceSSTF_EDF;
	}

	public int getAmountOfDoneNormalTasksSSTF_EDF() {
		return amountOfDoneNormalTasksSSTF_EDF;
	}

	public void setAmountOfDoneNormalTasksSSTF_EDF(int amountOfDoneNormalTasksSSTF_EDF) {
		this.amountOfDoneNormalTasksSSTF_EDF = amountOfDoneNormalTasksSSTF_EDF;
	}

	public int getAmountOfDonePriorityTasksSSTF_EDF() {
		return amountOfDonePriorityTasksSSTF_EDF;
	}

	public void setAmountOfDonePriorityTasksSSTF_EDF(int amountOfDonePriorityTasksSSTF_EDF) {
		this.amountOfDonePriorityTasksSSTF_EDF = amountOfDonePriorityTasksSSTF_EDF;
	}

	public int getPriorityAmount() {
		return priorityAmount;
	}

	public void setPriorityAmount(int priorityAmount) {
		this.priorityAmount = priorityAmount;
	}

	public int getDistanceSCAN_EDF() {
		return distanceSCAN_EDF;
	}

	public void setDistanceSCAN_EDF(int distanceSCAN_EDF) {
		this.distanceSCAN_EDF = distanceSCAN_EDF;
	}

	public int getAmountOfDoneNormalTasksSCAN_EDF() {
		return amountOfDoneNormalTasksSCAN_EDF;
	}

	public void setAmountOfDoneNormalTasksSCAN_EDF(int amountOfDoneNormalTasksSCAN_EDF) {
		this.amountOfDoneNormalTasksSCAN_EDF = amountOfDoneNormalTasksSCAN_EDF;
	}

	public int getAmountOfDonePriorityTasksSCAN_EDF() {
		return amountOfDonePriorityTasksSCAN_EDF;
	}

	public void setAmountOfDonePriorityTasksSCAN_EDF(int amountOfDonePriorityTasksSCAN_EDF) {
		this.amountOfDonePriorityTasksSCAN_EDF = amountOfDonePriorityTasksSCAN_EDF;
	}

	public int getCurrentPositionSCAN_EDF() {
		return currentPositionSCAN_EDF;
	}

	public void setCurrentPositionSCAN_EDF(int currentPositionSCAN_EDF) {
		this.currentPositionSCAN_EDF = currentPositionSCAN_EDF;
	}

	public int getCurrentPositionCSCAN_EDF() {
		return currentPositionCSCAN_EDF;
	}

	public void setCurrentPositionCSCAN_EDF(int currentPositionCSCAN_EDF) {
		this.currentPositionCSCAN_EDF = currentPositionCSCAN_EDF;
	}

	public int getDistanceCSCAN_EDF() {
		return distanceCSCAN_EDF;
	}

	public void setDistanceCSCAN_EDF(int distanceCSCAN_EDF) {
		this.distanceCSCAN_EDF = distanceCSCAN_EDF;
	}

	public int getAmountOfDoneNormalTasksCSCAN_EDF() {
		return amountOfDoneNormalTasksCSCAN_EDF;
	}

	public void setAmountOfDoneNormalTasksCSCAN_EDF(int amountOfDoneNormalTasksCSCAN_EDF) {
		this.amountOfDoneNormalTasksCSCAN_EDF = amountOfDoneNormalTasksCSCAN_EDF;
	}

	public int getAmountOfDonePriorityTasksCSCAN_EDF() {
		return amountOfDonePriorityTasksCSCAN_EDF;
	}

	public void setAmountOfDonePriorityTasksCSCAN_EDF(int amountOfDonePriorityTasksCSCAN_EDF) {
		this.amountOfDonePriorityTasksCSCAN_EDF = amountOfDonePriorityTasksCSCAN_EDF;
	}
	
}
