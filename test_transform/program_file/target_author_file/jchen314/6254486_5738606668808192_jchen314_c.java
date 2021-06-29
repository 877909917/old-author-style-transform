import java.util.ArrayList;
 import java.util.List;
 
 public class C {
     static boolean[] notPrime = new boolean[100000000];
     static List<Integer> primes;
 
     public static void main(String[] args) {
         int N = 16;
         int J = 50;
         System.out.println("Case #1:");
 
         notPrime[0] = notPrime[1] = true;
         primes = new ArrayList<>();
         for (int i = 2; i < notPrime.length; i++) {
             if (notPrime[i])
                 continue;
             primes.add(i);
             for (int k = 2 * i; k < notPrime.length; k += i) {
                 notPrime[k] = true;
             }
         }
 
         int numFound = 0;
         for (int coin = (1 << (N - 1)) + 1; coin < (1 << N); coin += 2) {
             String jamcoin = Integer.toBinaryString(coin);
             List<Integer> factors = verifyJamcoin(jamcoin);
             if (factors != null) {
                 System.out.print(jamcoin);
                 for (int factor : factors) {
                     System.out.print(" " + factor);
                 }
                 System.out.println();
                 numFound++;
                 if (numFound >= J) {
                     return;
                 }
             }
         }
     }
 
     static List<Integer> verifyJamcoin(String coin) {
         List<Integer> factors = new ArrayList<>();
         for (int base = 2; base <= 10; base++) {
             long value = Long.valueOf(coin, base);
             for (int p : primes) {
                 if (p * p > value) {
                     break;
                 }
                 if (value % p == 0 && value != p) {
                     factors.add(p);
                     break;
                 }
             }
             if (factors.size() != base - 1) {
                 return null;
             }
         }
         return factors;
     }
 }
