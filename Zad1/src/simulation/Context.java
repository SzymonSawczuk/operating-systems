package simulation;

public class Context {
	
	private final static Context instance = new Context();
	private static long HUNGRYPROCESSLIMIT;
	private static int maxDuration;
	
	private int time = 0;
	
	private long currentAmountOfProcessesForFCFS = 0;
	private long currentAmountOfProcessesForSJF = 0;
	private long currentAmountOfProcessesForRR = 0;
	
	private long amountOfProcesses = 0;
	private long maxAmountOfProcesses = 0;
	
	private long fullTimeOfFCFS = 0;
	private long fullTimeOfSJF = 0;
	private long fullTimeOfRR = 0;
	
	private double waitingTimeOfFCFS = 0;
	private double waitingTimeOfSJF = 0;
	private double waitingTimeOfRR = 0;
	
	private double maxWaitingTimeOfFCFS = 0; 
	private double maxWaitingTimeOfSJF = 0; 
	private double maxWaitingTimeOfRR = 0; 
	
	private double hungryProcessesOfFCFS = 0;
	private double hungryProcessesOfSJF = 0;
	private double hungryProcessesOfRR = 0;
	
	private double finishingTimeOfFCFS = 0;
	private double finishingTimeOfSJF = 0;
	private double finishingTimeOfRR = 0;
	
	private long amountOfSwitchesOfFCFS = 0;
	private long amountOfSwitchesOfSJF = 0;
	private long amountOfSwitchesOfRR = 0;
	
	public static Context getInstance() {
		return instance;
	}
	
	public int getTime() {
		return time;
	}
	
	public long getCurrentAmountOfProcessesForFCFS() {
		return currentAmountOfProcessesForFCFS;
	}
	
	public long getCurrentAmountOfProcessesForSJF() {
		return currentAmountOfProcessesForSJF;
	}
	
	public long getFullTimeOfSJF() {
		return fullTimeOfSJF;
	}
	
	public long getFullTimeOfFCFS() {
		return fullTimeOfFCFS;
	}
	
	public long getMaxAmountOfProcesses() {
		return maxAmountOfProcesses;
	}
	
	public long getAmountOfProcesses() {
		return amountOfProcesses;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setCurrentAmountOfProcessesForFCFS(long currentAmountOfProcessesForFCFS) {
		this.currentAmountOfProcessesForFCFS = currentAmountOfProcessesForFCFS;
	}
	
	public void setCurrentAmountOfProcessesForSJF(long currentAmountOfProcessesForSJF) {
		this.currentAmountOfProcessesForSJF = currentAmountOfProcessesForSJF;
	}
	
	public void setFullTimeOfSJF(long fullTimeOfSJF) {
		this.fullTimeOfSJF = fullTimeOfSJF;
	}

	
	public void setFullTimeOfFCFS(long fullTimeOfFCFS) {
		this.fullTimeOfFCFS = fullTimeOfFCFS;
	}
	
	public void setMaxAmountOfProcesses(long maxAmountOfProcesses) {
		this.maxAmountOfProcesses = maxAmountOfProcesses;
	}
	
	public void setAmountOfProcesses(long amountOfProcesses) {
		this.amountOfProcesses = amountOfProcesses;
	}

	public long getFullTimeOfRR() {
		return fullTimeOfRR;
	}

	public void setFullTimeOfRR(long fullTimeOfRR) {
		this.fullTimeOfRR = fullTimeOfRR;
	}

	public long getCurrentAmountOfProcessesForRR() {
		return currentAmountOfProcessesForRR;
	}

	public void setCurrentAmountOfProcessesForRR(long currentAmountOfProcessesForRR) {
		this.currentAmountOfProcessesForRR = currentAmountOfProcessesForRR;
	}

	public double getWaitingTimeOfFCFS() {
		return waitingTimeOfFCFS;
	}

	public void setWaitingTimeOfFCFS(double waitingTimeOfFCFS) {
		this.waitingTimeOfFCFS = waitingTimeOfFCFS;
	}

	public double getWaitingTimeOfSJF() {
		return waitingTimeOfSJF;
	}

	public void setWaitingTimeOfSJF(double waitingTimeOfSJF) {
		this.waitingTimeOfSJF = waitingTimeOfSJF;
	}

	public double getWaitingTimeOfRR() {
		return waitingTimeOfRR;
	}

	public void setWaitingTimeOfRR(double waitingTimeOfRR) {
		this.waitingTimeOfRR = waitingTimeOfRR;
	}

	public double getMaxWaitingTimeOfSJF() {
		return maxWaitingTimeOfSJF;
	}

	public void setMaxWaitingTimeOfSJF(double maxWaitingTimeOfSJF) {
		this.maxWaitingTimeOfSJF = maxWaitingTimeOfSJF;
	}

	public double getMaxWaitingTimeOfFCFS() {
		return maxWaitingTimeOfFCFS;
	}

	public void setMaxWaitingTimeOfFCFS(double maxWaitingTimeOfFCFS) {
		this.maxWaitingTimeOfFCFS = maxWaitingTimeOfFCFS;
	}

	public double getMaxWaitingTimeOfRR() {
		return maxWaitingTimeOfRR;
	}

	public void setMaxWaitingTimeOfRR(int maxWaitingTimeOfRR) {
		this.maxWaitingTimeOfRR = maxWaitingTimeOfRR;
	}

	public double getHungryProcessesOfFCFS() {
		return hungryProcessesOfFCFS;
	}

	public void setHungryProcessesOfFCFS(double hungryProcessesOfFCFS) {
		this.hungryProcessesOfFCFS = hungryProcessesOfFCFS;
	}

	public double getHungryProcessesOfSJF() {
		return hungryProcessesOfSJF;
	}

	public void setHungryProcessesOfSJF(double hungryProcessesOfSJF) {
		this.hungryProcessesOfSJF = hungryProcessesOfSJF;
	}

	public double getHungryProcessesOfRR() {
		return hungryProcessesOfRR;
	}

	public void setHungryProcessesOfRR(double hungryProcessesOfRR) {
		this.hungryProcessesOfRR = hungryProcessesOfRR;
	}

	public static long getHungryprocesslimit() {
		return HUNGRYPROCESSLIMIT;
	}
	
	public static void setHUNGRYPROCESSLIMIT(long hUNGRYPROCESSLIMIT) {
		HUNGRYPROCESSLIMIT = hUNGRYPROCESSLIMIT;
	}

	public double getFinishingTimeOfFCFS() {
		return finishingTimeOfFCFS;
	}

	public void setFinishingTimeOfFCFS(double finishingTimeOfFCFS) {
		this.finishingTimeOfFCFS = finishingTimeOfFCFS;
	}

	public double getFinishingTimeOfSJF() {
		return finishingTimeOfSJF;
	}

	public void setFinishingTimeOfSJF(double finishingTimeOfSJF) {
		this.finishingTimeOfSJF = finishingTimeOfSJF;
	}

	public double getFinishingTimeOfRR() {
		return finishingTimeOfRR;
	}

	public void setFinishingTimeOfRR(double finishingTimeOfRR) {
		this.finishingTimeOfRR = finishingTimeOfRR;
	}

	public long getAmountOfSwitchesOfFCFS() {
		return amountOfSwitchesOfFCFS;
	}

	public void setAmountOfSwitchesOfFCFS(long amountOfSwitchesOfFCFS) {
		this.amountOfSwitchesOfFCFS = amountOfSwitchesOfFCFS;
	}

	public long getAmountOfSwitchesOfSJF() {
		return amountOfSwitchesOfSJF;
	}

	public void setAmountOfSwitchesOfSJF(long amountOfSwitchesOfSJF) {
		this.amountOfSwitchesOfSJF = amountOfSwitchesOfSJF;
	}

	public long getAmountOfSwitchesOfRR() {
		return amountOfSwitchesOfRR;
	}

	public void setAmountOfSwitchesOfRR(long amountOfSwitchesOfRR) {
		this.amountOfSwitchesOfRR = amountOfSwitchesOfRR;
	}

	public static int getMaxDuration() {
		return maxDuration;
	}

	public static void setMaxDuration(int maxDuration) {
		Context.maxDuration = maxDuration;
	}

}
