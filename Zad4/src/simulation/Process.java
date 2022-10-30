package simulation;

import java.util.ArrayList;

public class Process {
	
	private ArrayList<Page> pages;
	private int amountOfFrames, startingPos;
	private int length, size, pos;
	private int amountOfErrors;
	private boolean isDone;
	private double ppf;
	private int ppfErrors;
	private boolean isFreezed;
	
	public Process(int length) {
		this.setPages(new ArrayList<Page>());
		this.setLength(length); 
		this.setAmountOfFrames(0);
		this.setAmountOfErrors(0);
		this.setSize(0);
		this.setIsDone(false);
		this.setPos(0);
		this.setPpf(0);
		this.setPpfErrors(0);
		this.setFreezed(false);
	}
	
	public void reset() {
		this.setAmountOfFrames(0);
		this.setAmountOfErrors(0);
		this.setSize(0);
		this.setIsDone(false);
		this.setPos(0);
		this.setPpf(0);
		this.setPpfErrors(0);
		this.setFreezed(false);
	}
	
	public int getAmountOfFrames() {
		return amountOfFrames;
	}
	
	public ArrayList<Page> getPages() {
		return pages;
	}
	
	public void setAmountOfFrames(int amountOfFrames) {
		this.amountOfFrames = amountOfFrames;
	}
	
	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isDone() {
		return isDone;
	}
	
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public int getAmountOfErrors() {
		return amountOfErrors;
	}

	public void setAmountOfErrors(int amountOfErrors) {
		this.amountOfErrors = amountOfErrors;
	}

	public int getStartingPos() {
		return startingPos;
	}

	public void setStartingPos(int startingPos) {
		this.startingPos = startingPos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public double getPpf() {
		return ppf;
	}

	public void setPpf(double ppf) {
		this.ppf = ppf;
	}

	public int getPpfErrors() {
		return ppfErrors;
	}

	public void setPpfErrors(int ppfErrors) {
		this.ppfErrors = ppfErrors;
	}

	public boolean isFreezed() {
		return isFreezed;
	}

	public void setFreezed(boolean isFreezed) {
		this.isFreezed = isFreezed;
	}
	
	
}
