package simulation;

import java.util.ArrayList;

//Szymon Sawczuk 260287
public class Fifo {

	private ArrayList<Page> pages;
	private int size, pos;
	
	public Fifo() {
	
		this.pages = new ArrayList<Page>();
		this.size = 0;
		this.pos = 0;
		
	}
	
	public void tick(Page page) {
		
		if(size < Context.getInstance().getAmountOfFrames()) {
			if(!containsPage(page)) {
				pages.add(page);
				size++;
				pos = (pos+1)%Context.getInstance().getAmountOfFrames();
				Context.getInstance().setAmountOfErrorsFIFO(Context.getInstance().getAmountOfErrorsFIFO() + 1);
			}

		}else {
			if(!containsPage(page)) {
				pages.remove(pos);
				pages.add(pos, page);
				pos = (pos+1)%Context.getInstance().getAmountOfFrames();
				Context.getInstance().setAmountOfErrorsFIFO(Context.getInstance().getAmountOfErrorsFIFO() + 1);
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
