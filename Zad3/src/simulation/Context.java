package simulation;

//Szymon Sawczuk 260287
public class Context {
	
	private final static Context instance = new Context();
	
	private int amountOfFrames = 0;
	
	private int amountOfErrorsFIFO = 0;
	private int amountOfErrorsRAND = 0;
	private int amountOfErrorsOPT = 0;
	private int amountOfErrorsLRU = 0;
	private int amountOfErrorsALRU = 0;


	public static Context getInstance() {
		return instance;
	}

	public int getAmountOfErrorsFIFO() {
		return amountOfErrorsFIFO;
	}

	public void setAmountOfErrorsFIFO(int amountOfErrorsFIFO) {
		this.amountOfErrorsFIFO = amountOfErrorsFIFO;
	}

	public int getAmountOfFrames() {
		return amountOfFrames;
	}

	public void setAmountOfFrames(int amountOfFrames) {
		this.amountOfFrames = amountOfFrames;
	}

	public int getAmountOfErrorsRAND() {
		return amountOfErrorsRAND;
	}

	public void setAmountOfErrorsRAND(int amountOfErrorsRAND) {
		this.amountOfErrorsRAND = amountOfErrorsRAND;
	}

	public int getAmountOfErrorsOPT() {
		return amountOfErrorsOPT;
	}

	public void setAmountOfErrorsOPT(int amountOfErrorsOPT) {
		this.amountOfErrorsOPT = amountOfErrorsOPT;
	}

	public int getAmountOfErrorsLRU() {
		return amountOfErrorsLRU;
	}

	public void setAmountOfErrorsLRU(int amountOfErrorsLRU) {
		this.amountOfErrorsLRU = amountOfErrorsLRU;
	}

	public int getAmountOfErrorsALRU() {
		return amountOfErrorsALRU;
	}

	public void setAmountOfErrorsALRU(int amountOfErrorsALRU) {
		this.amountOfErrorsALRU = amountOfErrorsALRU;
	}

}
