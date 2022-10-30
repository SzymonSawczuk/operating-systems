package simulation;

import java.util.ArrayList;

//Szymon Sawczuk 260287
public class Opt {

	private ArrayList<Page> pages;
	private ArrayList<Page> original;
	private int size, pos;
	
	public Opt(ArrayList<Page> original) {
	
		this.pages = new ArrayList<Page>();
		this.size = 0;
		this.pos = 0;
		this.original = new ArrayList<Page>(original);
		
	}
	
	public void tick(Page page) {
	
		
		if(size < Context.getInstance().getAmountOfFrames()) {
			if(!containsPage(page)) {
				pages.add(page);
				size++;
				Context.getInstance().setAmountOfErrorsOPT(Context.getInstance().getAmountOfErrorsOPT() + 1);
			}
		}else {
			if(!containsPage(page)) {
				
				setDistance();
				int posOfMax = pages.indexOf(maxDistanceToNext());
				pages.remove(posOfMax);
				pages.add(posOfMax, page);
				
				Context.getInstance().setAmountOfErrorsOPT(Context.getInstance().getAmountOfErrorsOPT() + 1);
				setDistanceToZero();

			}
		}
		pos++;

		
	}
	
	private boolean containsPage(Page page) {
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return true;
		}
		return false;
	}
	
	private void setDistance() {
		
		int tmpPos = pos + 1;
		
		for(Page page : pages) {
			
			for(int i = tmpPos; i < original.size(); i++) {
				if(page.getDistanceToNext() == 0 && page.getValue() == original.get(i).getValue()) page.setDistanceToNext(i - pos);
			}
			
			if(page.getDistanceToNext() == 0) page.setDistanceToNext(original.size() - pos);
			
		}
		
	}

	
	private Page maxDistanceToNext() {
		
		Page max = pages.get(0);
		
		for(int i = 1; i < pages.size(); i++) {
			
			if(pages.get(i).getDistanceToNext() > max.getDistanceToNext())max = pages.get(i);
			
		}
		
		return max;
		
	}
	
	private void setDistanceToZero() {
		
		for(Page page : pages) {
			page.setDistanceToNext(0);
		}
		
	}
	
	
}
