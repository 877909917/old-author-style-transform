import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Scanner;
 
 
 public class Main {
 	public static void main(String[] args) throws IOException {
 		new Main();
 	}
 	
 	public Main() throws IOException {
 		
 		Scanner sc = new Scanner(new File("B-small-attempt0.in"));
 		PrintWriter out = new PrintWriter(new File("B-small-attempt0.out"));
 		
 		int amountOfTasks = sc.nextInt();
 		for (int task = 1; task <= amountOfTasks; task++) {
 			int n = sc.nextInt();
 			double volume = Double.parseDouble(sc.next());
 			double temperature = Double.parseDouble(sc.next());
 			
 			double rate[] = new double[n];
 			double temp[] = new double[n];
 			ArrayList<Source> equalTemp = new ArrayList<Source>();
 			ArrayList<Source> lowerTemp = new ArrayList<Source>();
 			ArrayList<Source> higherTemp = new ArrayList<Source>();
 			for (int i = 0; i < n; i++) {
 				rate[i] = Double.parseDouble(sc.next());
 				temp[i] = Double.parseDouble(sc.next());
 				if (temp[i] == temperature) {
 					equalTemp.add(new Source(rate[i], temp[i]));
 				} else if (temp[i] > temperature) {
 					higherTemp.add(new Source(rate[i], temp[i]));
 				} else {
 					lowerTemp.add(new Source(rate[i], temp[i]));
 				}
 			}
 			
 			double litersPerSecond = 0.0;
 			
 			for (Source s : equalTemp) {
 				litersPerSecond += s.rate;
 			}
 			
 			for (Source s1 : higherTemp) {
 				for (Source s2 : lowerTemp) {
 					if (s1.rate == 0.0) break;
 					if (s2.rate == 0.0) continue;
 					double r1 = s2.rate*(temperature-s2.temp)/(-temperature+s1.temp);
 					if (r1 <= s1.rate) {
 						litersPerSecond += (s2.rate + r1);
 						s2.rate = 0.0;
 						s1.rate -= r1;
 					} else {
 						double r2 = s1.rate*(temperature-s1.temp)/(-temperature+s2.temp);
 						litersPerSecond += (s1.rate + r2);
 						s2.rate -= r2;
 						s1.rate = 0.0;
 					}
 				}
 			}
 			
 			double time = volume/litersPerSecond;
 			
 			boolean smaller = false;
 			boolean bigger = false;
 			for (int i = 0; i < n; i++) {
 				if (temp[i] >= temperature) bigger = true;
 				if (temp[i] <= temperature) smaller = true;
 			}
 			
 			String solution = "Case #" + task + ": " + time;
 			if (!(smaller && bigger))solution = "Case #" + task + ": IMPOSSIBLE";
 			System.out.println(solution);
 			out.println(solution);
 		}
 		
 		out.close();
 		sc.close();
 	}
 	
 	class Source{
 		double rate;
 		double temp;
 		public Source(double rate, double temp) {
 			this.rate = rate;
 			this.temp = temp;
 		}
 	}
 }