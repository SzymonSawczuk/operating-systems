package simulation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Szymon Sawczuk 260287
public class Main {
	
	private	static void getResults(int amountOfFrames, ArrayList<Page> pages) {
		
		final int AMOUNTOFCYCLES = 1000;
		
		Context.getInstance().setAmountOfFrames(amountOfFrames);
		
		Context.getInstance().setAmountOfErrorsFIFO(0);
		Context.getInstance().setAmountOfErrorsOPT(0);
		Context.getInstance().setAmountOfErrorsLRU(0);
		Context.getInstance().setAmountOfErrorsALRU(0);
		Context.getInstance().setAmountOfErrorsRAND(0);
		
		Fifo fifo = new Fifo();
		Rand rand = new Rand();
		Opt opt = new Opt(pages);
		Lru lru = new Lru();
		ALru aLru = new ALru();
		
		for(int i = 0; i < AMOUNTOFCYCLES; i++) {
			fifo = new Fifo();
			rand = new Rand();
			opt = new Opt(pages);
			lru = new Lru();
			aLru = new ALru();
			
			for(Page elem : pages) {
				
				fifo.tick(elem);
				rand.tick(elem);
				opt.tick(elem);
				lru.tick(elem);
				aLru.tick(elem);
				
			}
		}
		
		
		
		System.out.printf("Ilosc bledow dla %d ramek: \n", amountOfFrames);
		System.out.println("FIFO: " + Context.getInstance().getAmountOfErrorsFIFO()/AMOUNTOFCYCLES);
		System.out.println("OPT: " + Context.getInstance().getAmountOfErrorsOPT()/AMOUNTOFCYCLES);
		System.out.println("LRU: " + Context.getInstance().getAmountOfErrorsLRU()/AMOUNTOFCYCLES);
		System.out.println("Aproksymowany LRU: " + Context.getInstance().getAmountOfErrorsALRU()/AMOUNTOFCYCLES);
		System.out.println("RAND: " + Context.getInstance().getAmountOfErrorsRAND()/AMOUNTOFCYCLES);
		System.out.println();
		
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Podaj ilosc testow dla roznych ramek: ");
		final int AMOUNTOFTESTS = scanner.nextInt();
		
		ArrayList<Integer> amountOfFrames = new ArrayList<Integer>();
		
		for(int i = 0; i < AMOUNTOFTESTS; i++) {
			System.out.printf("Ilosc ramek test %d: ",i + 1);
			amountOfFrames.add(scanner.nextInt());
			
		}
		
		System.out.print("Podaj ilosc stron: ");
		final int AMOUNTOFPAGES = scanner.nextInt();
		
		System.out.print("Podaj dlugosc testu odwolan: ");
		final int LENGTH = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent dlugosci odwolan dla odwolan lokalnych: ");
		final int MAXLENGTHOFLOCAL = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent zbioru stron dla podzbioru odwolan lokalnych: ");
		final int MAXSUBSET = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent prawdopodobienstwa pojawienia sie podzbioru odwolan lokalnych: ");
		final int MAXFRAQUENCYOFLOCAL = scanner.nextInt();
		System.out.println();
		
		Random random = new Random();
		
		ArrayList<Page> pages = new ArrayList<Page>();
		Page page = null;
		
		ArrayList<Integer> randomValues = new ArrayList<Integer>();
		double tmpRandom;
		boolean isLocal = false;
		int tmpMaxSizeOfLocal = 0, tmpSizeOfLocal = 0;
		
		for(int i = 0; i < LENGTH; i++) {
			
			if(!isLocal) {
			
				tmpRandom = random.nextDouble();
				
				if(tmpRandom < (double)MAXFRAQUENCYOFLOCAL / 100) {
					
					isLocal = true;
					tmpMaxSizeOfLocal = random.nextInt((int)((double)MAXLENGTHOFLOCAL/100 * (double)LENGTH) - 1) + 2;
					
					for(int j = 0; j < random.nextInt((int)((double)MAXSUBSET/100 * (double)AMOUNTOFPAGES) - 1) + 2; j++) {
						
						int tmp = random.nextInt(AMOUNTOFPAGES) + 1;
						
						if(randomValues.indexOf(tmp) == -1) randomValues.add(tmp);

					}
					
				}
				page = new Page(random.nextInt(AMOUNTOFPAGES) + 1);
			}else {
				
				page = new Page(randomValues.get(random.nextInt(randomValues.size())));
				
				tmpSizeOfLocal++;
				
				if(tmpSizeOfLocal == tmpMaxSizeOfLocal) {
					isLocal = false;
					tmpMaxSizeOfLocal = 0;
					tmpSizeOfLocal = 0;
					randomValues.clear();
				}
				
			}
			
			
			pages.add(page);
		}

		
		for(int frames : amountOfFrames) {
			getResults(frames, pages);
		}
		
		
		scanner.close();
	

	}
	
}
