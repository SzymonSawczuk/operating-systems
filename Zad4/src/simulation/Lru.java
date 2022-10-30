package simulation;

import java.util.ArrayList;

//Szymon Sawczuk 260287
public class Lru {

	private ArrayList<Page> frames;
	private Process process;
	private int pos;
	
	public boolean tick(Process process, Page page,  ArrayList<Page> frames, int pos) {
	
		this.frames = frames;
		this.process = process;
		this.pos = pos;
		boolean errorAppeared = false;
		
		if(process.getSize() < process.getAmountOfFrames()) {
			
			if(!containsPage(page)) {
				
				frames.remove(process.getStartingPos() + process.getSize());
				frames.add(process.getStartingPos() + process.getSize(), page);
				process.setSize(process.getSize() + 1);
				process.setAmountOfErrors(process.getAmountOfErrors() + 1);
				errorAppeared = true;
				process.setPpfErrors(process.getPpfErrors() + 1);
			}

		}else {
			if(!containsPage(page)) {
				
				setDistance();
				int posOfMax = frames.indexOf(maxDistanceToNext());
				frames.remove(posOfMax);
				frames.add(posOfMax, page);
				
				process.setAmountOfErrors(process.getAmountOfErrors() + 1);
				errorAppeared = true;
				process.setPpfErrors(process.getPpfErrors() + 1);

			}
		}
		frames.get(getIndex(page)).setLastAppearance(pos);
		
		return errorAppeared;
	}
	
	private int getIndex(Page page) {
		
		for(int i = process.getStartingPos(); i < process.getStartingPos() + process.getAmountOfFrames(); i++) {
			if(page == null && frames.get(i) == null) return i;
			if(page != null && frames.get(i) != null && frames.get(i).getValue() == page.getValue()) return i;
		}
		
		return -1;
		
	}
	
	private void setDistance() {
		
		for(int i = process.getStartingPos(); i < process.getStartingPos() + process.getAmountOfFrames(); i++) {
//			System.out.println(frames.get(i) + " " + i);
			frames.get(i).setDistanceToPrevious(pos - frames.get(i).getLastAppearance());
		}
		
	}
	
	private boolean containsPage(Page page) {
		for(int i = process.getStartingPos(); i < process.getStartingPos() + process.getAmountOfFrames(); i++) {
			if(frames.get(i) != null && frames.get(i).getValue() == page.getValue()) return true;
		}
		return false;
	}
	
	private Page maxDistanceToNext() {
		
		Page max = frames.get(process.getStartingPos());
		
		for(int i = process.getStartingPos() + 1; i < process.getStartingPos() + process.getAmountOfFrames(); i++) {
			
			if(frames.get(i).getDistanceToPrevious() > max.getDistanceToPrevious())max = frames.get(i);
			
		}
		
		return max;
		
	}

	
	
	
}
