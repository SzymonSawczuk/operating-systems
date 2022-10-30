package simulation;

//Szymon Sawczuk 260287
public class Element {
	
	private Page page;
	private int bit;
	
	public Element(Page page) {
		
		this.setPage(page);
		this.setBit(1);
		
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

}
