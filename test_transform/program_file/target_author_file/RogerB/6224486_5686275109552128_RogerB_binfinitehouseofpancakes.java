package Qualifier;
 
 import java.util.Scanner;
 import java.util.ArrayList;
 
 public class BInfiniteHouseOfPancakes {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int dinerCount = sc.nextInt();
             ArrayList<Integer> diners = new ArrayList<Integer>();
             ArrayList<Integer> origins = new ArrayList<Integer>();
             for (int x = 0; x < dinerCount; x++) {
                 diners.add(sc.nextInt());
                 origins.add(x);
             }
             int bestCase = Integer.MAX_VALUE;
             int currMoves = 0;
             if (max(diners) == 1) bestCase = 1;
             while (max(diners) > 1 && currMoves < bestCase) {
                 int max = max(diners);
                 bestCase = Math.min(bestCase, currMoves + max);
                 
                 int index = diners.indexOf(max);
                 int origin = origins.get(index);
                 ArrayList<Integer> allProgeny = new ArrayList<Integer>();
                 for (int x = 0; x < diners.size(); x++) {
                     if (origins.get(x) == origin) allProgeny.add(x);
                 }
                 int total = 0;
                 for (int i : allProgeny) {
                     total += diners.get(i);
                 }
                 int base = total / (allProgeny.size() + 1);
                 int overflow = total % (allProgeny.size() + 1);
                 for (int x = 0; x < overflow; x++) {
                     diners.set(allProgeny.get(x), base + 1);
                 }
                 for (int x = overflow; x < allProgeny.size(); x++) {
                     diners.set(allProgeny.get(x), base);
                 }
                 diners.add(base);
                 origins.add(origin);
                 
                 currMoves++;
             }
             System.out.printf("Case #%d: %d%n", t, bestCase);
         }
         sc.close();
     }
     static int max (ArrayList<Integer> diners) {
         int max = Integer.MIN_VALUE;
         for (int diner : diners) {
             max = Math.max(max, diner);
         }
         return max;
     }
 }
