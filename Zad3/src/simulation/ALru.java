package simulation;

import java.util.ArrayList;

//Szymon Sawczuk 260287
public class ALru {

	private ArrayList<Page> pages;
	private ArrayList<Element> fifo;
	private int size;
	
	public ALru() {
	
		this.pages = new ArrayList<Page>();
		this.size = 0;
		this.fifo = new ArrayList<Element>();
		
	}
	
	public void tick(Page page) {
		
		if(size < Context.getInstance().getAmountOfFrames()) {
			if(!containsPage(page)) {
				pages.add(page);
				fifo.add(new Element(page));
				size++;
				Context.getInstance().setAmountOfErrorsALRU(Context.getInstance().getAmountOfErrorsALRU() + 1);
			}
		}else {
			if(!containsPage(page)) {
				
				int posToDelete = getIndex(newElementFifo(page));
				pages.remove(posToDelete);
				pages.add(posToDelete, page);
				
				Context.getInstance().setAmountOfErrorsALRU(Context.getInstance().getAmountOfErrorsALRU() + 1);
			}else {
				existingElementFifo(page);
			}
		}
		
		
	}
	
	private int getIndex(Page page) {
		
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return pages.indexOf(elem);
		}
		return -1;
		
	}
	
	private boolean containsPage(Page page) {
		for(Page elem : pages) {
			if(elem.getValue() == page.getValue()) return true;
		}
		return false;
	}
	
	private void existingElementFifo(Page page) {
		
		for(Element elem : fifo) {
			if(elem.getPage().getValue() == page.getValue()) elem.setBit(1);
		}
		
	}
	
	private Page newElementFifo(Page page) {
		
		boolean done = false;
		Element elem = fifo.get(0);
		Page oldPage = null;
		
		while(!done) {
			if(elem.getBit() == 1) {
				fifo.remove(0);
				elem.setBit(0);
				fifo.add(elem);
				elem = fifo.get(0);
				
			}else if(elem.getBit() == 0){
				oldPage = fifo.get(0).getPage();
				fifo.remove(0);
				fifo.add(new Element(page));
				done = true;
			}
		}
		
		return oldPage;
		
	}
	
}
