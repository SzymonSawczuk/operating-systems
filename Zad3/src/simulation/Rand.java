package simulation;

import java.util.ArrayList;
import java.util.Random;

//Szymon Sawczuk 260287
public class Rand {
	
	private ArrayList<Page> pages;
	private int size, pos;
	Random rand;
	
	public Rand() {
	
		this.pages = new ArrayList<Page>();
		this.size = 0;
		this.rand = new Random();
	}
	
	public void tick(Page page) {
		
		if(size < Context.getInstance().getAmountOfFrames()) {
			if(!containsPage(page)) {
				pages.add(page);
				size++;
				Context.getInstance().setAmountOfErrorsRAND(Context.getInstance().getAmountOfErrorsRAND() + 1);
			}
		}else {
			if(!containsPage(page)) {
				pos = rand.nextInt(Context.getInstance().getAmountOfFrames());
				
				pages.remove(pos);
				pages.add(pos, page);
				Context.getInstance().setAmountOfErrorsRAND(Context.getInstance().getAmountOfErrorsRAND() + 1);
			}
		}
		
		
	}
	
	private boolean containsPage(Page page) {
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return true;
		}
		return false;
	}
	

}
