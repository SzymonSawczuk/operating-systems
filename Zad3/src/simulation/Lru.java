package simulation;

import java.util.ArrayList;

//Szymon Sawczuk 260287
public class Lru {

	private ArrayList<Page> pages;
	private int size, pos;
	
	public Lru() {
	
		this.pages = new ArrayList<Page>();
		this.size = 0;
		this.pos = 0;
		
	}
	
	public void tick(Page page) {
	
		
		if(size < Context.getInstance().getAmountOfFrames()) {
			
			if(!containsPage(page)) {
				pages.add(page);
				size++;
				Context.getInstance().setAmountOfErrorsLRU(Context.getInstance().getAmountOfErrorsLRU() + 1);
			}

		}else {
			if(!containsPage(page)) {
				
				setDistance();
				int posOfMax = pages.indexOf(maxDistanceToNext());
				pages.remove(posOfMax);
				pages.add(posOfMax, page);
				
				Context.getInstance().setAmountOfErrorsLRU(Context.getInstance().getAmountOfErrorsLRU() + 1);

			}
		}
		pages.get(getIndex(page)).setLastAppearance(pos);
		pos++;
		
		
	}
	
	private int getIndex(Page page) {
		
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return pages.indexOf(elem);
		}
		return -1;
		
	}
	
	private void setDistance() {
		
		for(Page elem : pages) {
			elem.setDistanceToPrevious(pos - elem.getLastAppearance());
		}
		
	}
	
	private boolean containsPage(Page page) {
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return true;
		}
		return false;
	}
	

	
	private Page maxDistanceToNext() {
		
		Page max = pages.get(0);
		
		for(int i = 1; i < pages.size(); i++) {
			
			if(pages.get(i).getDistanceToPrevious() > max.getDistanceToPrevious())max = pages.get(i);
			
		}
		
		return max;
		
	}
	
	
}
