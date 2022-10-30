package simulation;


//Szymon Sawczuk 260287
public class Context {
	
	private final static Context instance = new Context();
	
	private int scuffleTime;
	private int scufflePercentage;
	private int ppfTime;
	private double lowPPF;
	private double highPPF;
	
	private int resultEqual;
	private int resultProp;
	private int resultPPF;
	
	private int scuffleEqual;
	private int scuffleProp;
	private int scufflePPF;
	private final int AMOUNTOFCYCLES = 10;
	
	public void setToZero() {
		resultEqual = 0;
		resultProp = 0;
		resultPPF = 0;
		scuffleEqual = 0;
		scuffleProp = 0;
		scufflePPF = 0;
	}
	
	public static Context getInstance() {
		return instance;
	}

	public int getScuffleTime() {
		return scuffleTime;
	}

	public void setScuffleTime(int scuffleTime) {
		this.scuffleTime = scuffleTime;
	}

	public int getScufflePercentage() {
		return scufflePercentage;
	}

	public void setScufflePercentage(int scufflePercentage) {
		this.scufflePercentage = scufflePercentage;
	}

	public int getPpfTime() {
		return ppfTime;
	}

	public void setPpfTime(int ppfTime) {
		this.ppfTime = ppfTime;
	}

	public double getLowPPF() {
		return lowPPF;
	}

	public void setLowPPF(double lowPPF) {
		this.lowPPF = lowPPF;
	}

	public double getHighPPF() {
		return highPPF;
	}

	public void setHighPPF(double highPPF) {
		this.highPPF = highPPF;
	}

	public int getResultEqual() {
		return resultEqual;
	}

	public void setResultEqual(int resultEqual) {
		this.resultEqual = resultEqual;
	}

	public int getResultProp() {
		return resultProp;
	}

	public void setResultProp(int resultProp) {
		this.resultProp = resultProp;
	}

	public int getResultPPF() {
		return resultPPF;
	}

	public void setResultPPF(int resultPPF) {
		this.resultPPF = resultPPF;
	}

	public int getScuffleEqual() {
		return scuffleEqual;
	}

	public void setScuffleEqual(int scuffleEqual) {
		this.scuffleEqual = scuffleEqual;
	}

	public int getScuffleProp() {
		return scuffleProp;
	}

	public void setScuffleProp(int scuffleProp) {
		this.scuffleProp = scuffleProp;
	}

	public int getScufflePPF() {
		return scufflePPF;
	}

	public void setScufflePPF(int scufflePPF) {
		this.scufflePPF = scufflePPF;
	}

	public int getAMOUNTOFCYCLES() {
		return AMOUNTOFCYCLES;
	}

	
}
