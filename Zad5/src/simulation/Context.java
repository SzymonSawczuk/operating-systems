package simulation;

//Szymon Sawczuk 260287
public class Context {

	private static final Context instance = new Context();

	private int time = 0;
	private int[] firstSimLoadList;
	private double amountOfAsksFirstSim = 0;
	private double amountOfMigrationsFirstSim = 0;
	private int[] amountOfOverLoadsFirstSim;
	private int[] timeOfOverLoadFirstSim;
	
	private int[] secondSimLoadList;
	private double amountOfAsksSecondSim = 0;
	private double amountOfMigrationsSecondSim = 0;
	private int[] amountOfOverLoadsSecondSim;
	private int[] timeOfOverLoadSecondSim;
	
	private int[] thirdSimLoadList;
	private double amountOfAsksThirdSim = 0;
	private double amountOfMigrationsThirdSim = 0;
	private int[] amountOfOverLoadsThirdSim;
	private int[] timeOfOverLoadThirdSim;
	
	
	public static Context getInstance() {
		return instance;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getAmountOfAsksFirstSim() {
		return amountOfAsksFirstSim;
	}

	public void setAmountOfAsksFirstSim(double amountOfAsksFirstSim) {
		this.amountOfAsksFirstSim = amountOfAsksFirstSim;
	}

	public double getAmountOfMigrationsFirstSim() {
		return amountOfMigrationsFirstSim;
	}

	public void setAmountOfMigrationsFirstSim(double amountOfMigrationsFirstSim) {
		this.amountOfMigrationsFirstSim = amountOfMigrationsFirstSim;
	}


	public double getAmountOfAsksSecondSim() {
		return amountOfAsksSecondSim;
	}

	public void setAmountOfAsksSecondSim(double amountOfAsksSecondSim) {
		this.amountOfAsksSecondSim = amountOfAsksSecondSim;
	}

	public double getAmountOfMigrationsSecondSim() {
		return amountOfMigrationsSecondSim;
	}

	public void setAmountOfMigrationsSecondSim(double amountOfMigrationsSecondSim) {
		this.amountOfMigrationsSecondSim = amountOfMigrationsSecondSim;
	}

	public double getAmountOfAsksThirdSim() {
		return amountOfAsksThirdSim;
	}

	public void setAmountOfAsksThirdSim(double amountOfAsksThirdSim) {
		this.amountOfAsksThirdSim = amountOfAsksThirdSim;
	}

	public double getAmountOfMigrationsThirdSim() {
		return amountOfMigrationsThirdSim;
	}

	public void setAmountOfMigrationsThirdSim(double amountOfMigrationsThirdSim) {
		this.amountOfMigrationsThirdSim = amountOfMigrationsThirdSim;
	}

	public int[] getFirstSimLoadList() {
		return firstSimLoadList;
	}

	public void setFirstSimLoadList(int[] firstSimLoadList) {
		this.firstSimLoadList = firstSimLoadList;
	}

	public int[] getSecondSimLoadList() {
		return secondSimLoadList;
	}

	public void setSecondSimLoadList(int[] secondSimLoadList) {
		this.secondSimLoadList = secondSimLoadList;
	}

	public int[] getThirdSimLoadList() {
		return thirdSimLoadList;
	}

	public void setThirdSimLoadList(int[] thirdSimLoadList) {
		this.thirdSimLoadList = thirdSimLoadList;
	}

	public int[] getAmountOfOverLoadsFirstSim() {
		return amountOfOverLoadsFirstSim;
	}

	public void setAmountOfOverLoadsFirstSim(int[] amountOfOverLoadsFirstSim) {
		this.amountOfOverLoadsFirstSim = amountOfOverLoadsFirstSim;
	}

	public int[] getTimeOfOverLoadFirstSim() {
		return timeOfOverLoadFirstSim;
	}

	public void setTimeOfOverLoadFirstSim(int[] timeOfOverLoadFirstSim) {
		this.timeOfOverLoadFirstSim = timeOfOverLoadFirstSim;
	}

	public int[] getAmountOfOverLoadsSecondSim() {
		return amountOfOverLoadsSecondSim;
	}

	public void setAmountOfOverLoadsSecondSim(int[] amountOfOverLoadsSecondSim) {
		this.amountOfOverLoadsSecondSim = amountOfOverLoadsSecondSim;
	}

	public int[] getTimeOfOverLoadSecondSim() {
		return timeOfOverLoadSecondSim;
	}

	public void setTimeOfOverLoadSecondSim(int[] timeOfOverLoadSecondSim) {
		this.timeOfOverLoadSecondSim = timeOfOverLoadSecondSim;
	}

	public int[] getAmountOfOverLoadsThirdSim() {
		return amountOfOverLoadsThirdSim;
	}

	public void setAmountOfOverLoadsThirdSim(int[] amountOfOverLoadsThirdSim) {
		this.amountOfOverLoadsThirdSim = amountOfOverLoadsThirdSim;
	}

	public int[] getTimeOfOverLoadThirdSim() {
		return timeOfOverLoadThirdSim;
	}

	public void setTimeOfOverLoadThirdSim(int[] timeOfOverLoadThirdSim) {
		this.timeOfOverLoadThirdSim = timeOfOverLoadThirdSim;
	}
	
}
