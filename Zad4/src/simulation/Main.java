package simulation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Szymon Sawczuk 260287
public class Main {
	
	private	static void getResults(int amountOfFrames, ArrayList<Process> processes) {
	
		
		Equal equal = null;
		Prop prop = null; 
		PPF ppf = null; 
		Context.getInstance().setToZero();
		
		for(int i = 0; i<Context.getInstance().getAMOUNTOFCYCLES(); i++) {
			equal = new Equal(processes, amountOfFrames);
			prop = new Prop(processes, amountOfFrames);
			ppf = new PPF(processes, amountOfFrames);
			
			equal.allocate();
			equal.simulate();
			equal.reset();
			
			prop.allocate();
			prop.simulate();
			prop.reset();
			
			ppf.allocate();
			ppf.simulate();
			ppf.reset();
		}
		
		
		
		
		
		System.out.println("-------------------------------------------");
		System.out.printf("Ilosc bledow dla %d ramek: \n", amountOfFrames);
		equal.showStatistics();
		System.out.println();

		prop.showStatistics();
		System.out.println();

		ppf.showStatistics();
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
		System.out.print("Podaj ilosc procesow: ");
		final int AMOUNTOFPROCESSES = scanner.nextInt();
		
		
		System.out.print("Podaj ilosc stron: ");
		int AMOUNTOFPAGES = scanner.nextInt();
		int MINIMALPAGE = 1;
		int rise = AMOUNTOFPAGES;
		
		System.out.print("Podaj maksymalna dlugosc testu odwolan: ");
		final int LENGTH = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent dlugosci odwolan dla odwolan lokalnych: ");
		final int MAXLENGTHOFLOCAL = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent zbioru stron dla podzbioru odwolan lokalnych: ");
		final int MAXSUBSET = scanner.nextInt();
		
		System.out.print("Podaj maksymalny procent prawdopodobienstwa pojawienia sie podzbioru odwolan lokalnych: ");
		final int MAXFRAQUENCYOFLOCAL = scanner.nextInt();
		
		System.out.print("Podaj czas badania szamotania: ");
		final int SCUFFLETIME = scanner.nextInt();
		Context.getInstance().setScuffleTime(SCUFFLETIME);
		
		System.out.print("Podaj prog pojawienia sie szamotania(w procentach): ");
		final int SCUFFLEPERCENTAGE = scanner.nextInt();
		Context.getInstance().setScufflePercentage(SCUFFLEPERCENTAGE);
		
		System.out.print("Okno czasowe dla pomiaru PPF: ");
		final int PPFTIME = scanner.nextInt();
		Context.getInstance().setPpfTime(PPFTIME);
		
		System.out.print("Prog dolny czestosci bledow: ");
		final double LOWPPF = scanner.nextDouble();
		Context.getInstance().setLowPPF(LOWPPF);
		
		System.out.print("Prog gorny czestosci bledow: ");
		final double HIGHTPPF = scanner.nextDouble();
		Context.getInstance().setHighPPF(HIGHTPPF);
		System.out.println();
		
		Random random = new Random();
		
		ArrayList<Process> processes = new ArrayList<Process>();
		Process process = null;
		
		for(int i = 0; i < AMOUNTOFPROCESSES; i++) {
			process = new Process(random.nextInt(LENGTH - 5) + 5);
			processes.add(process);
		}
		Page page = null;
		
		ArrayList<Integer> randomValues = new ArrayList<Integer>();
		double tmpRandom;
		boolean isLocal = false;
		int tmpMaxSizeOfLocal = 0, tmpSizeOfLocal = 0;
		
		for(Process p : processes) {
		
			for(int i = 0; i < p.getLength(); i++) {
				
				if(!isLocal) {
				
					tmpRandom = random.nextDouble();
					
					if(tmpRandom < (double)MAXFRAQUENCYOFLOCAL / 100) {
						
						isLocal = true;
						tmpMaxSizeOfLocal = random.nextInt((int)((double)MAXLENGTHOFLOCAL/100.0 * (double)LENGTH) - 1) + 2;
						
						for(int j = 0; j < random.nextInt((int)(((double)MAXSUBSET * ((double)AMOUNTOFPAGES - (double)MINIMALPAGE + 1)/100.0)) - 1) + 2; j++) {
							
							int tmp = random.nextInt(AMOUNTOFPAGES + 1 - MINIMALPAGE) + MINIMALPAGE;
							
							if(randomValues.indexOf(tmp) == -1) randomValues.add(tmp);

						}
						
					}
					page = new Page(random.nextInt(AMOUNTOFPAGES + 1 - MINIMALPAGE) + MINIMALPAGE);
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
				
				
				p.getPages().add(page);
			}
			
			isLocal = false;
			tmpMaxSizeOfLocal = 0;
			tmpSizeOfLocal = 0;
			randomValues.clear();
			MINIMALPAGE += rise;
			AMOUNTOFPAGES += rise;
			
		}

		
		for(int frames : amountOfFrames) {
			getResults(frames, processes);
		}
		
		
		scanner.close();
	

	}
	
}
