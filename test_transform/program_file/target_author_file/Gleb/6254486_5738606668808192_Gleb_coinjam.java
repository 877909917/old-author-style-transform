import java.io.*;
 import java.math.BigInteger;
 
 public class CoinJam implements Runnable {
   private static final String NAME = "";
 
   @Override
   public void run() {
     try {
       PrintWriter out = new PrintWriter(NAME + "coin.out");
 
       out.println("Case #1:");
 
       int tests = 1;
       int n = 16;
       int num = 50;
       BigInteger[] oo = new BigInteger[10];
       for (int o = 2; o < 10; o++) {
         oo[o] = new BigInteger(Integer.toString(o));
       }
 
       for (int i = (1 << (n - 1)) + 1; i < 1 << n && num > 0; i++) {
         if (i % 2 == 0) continue;
         String s = Integer.toBinaryString(i);
         System.out.println("!!! " + s);
         BigInteger[] rr = new BigInteger[10];
         for (int o = 2; o < 10; o++) {
           BigInteger r = BigInteger.ZERO;
           for (int j = 0; j < s.length(); j++) {
             r = r.multiply(oo[o]).add(s.charAt(j) == '0' ? BigInteger.ZERO : BigInteger.ONE);
           }
           System.out.println(o + ": " + r);
           rr[o] = r;
         }
         boolean ok = true;
         for (int o = 2; o < 10; o++) {
           if (rr[o].isProbablePrime(10)) {
             ok = false;
           }
         }
         if (ok) {
           BigInteger[] res = new BigInteger[10];
           for (int o = 2; o < 10; o++) {
             BigInteger t = new BigInteger("3");
             while (t.multiply(t).compareTo(rr[o]) < 0) {
               if (rr[o].mod(t).equals(BigInteger.ZERO)) {
                 res[o] = rr[o].divide(t);
                 break;
               }
               t = t.add(BigInteger.ONE);
             }
             if (res[o] == null) {
               ok = false;
               break;
             }
           }
           if (ok) {
             num--;
             out.print(s + " ");
             for (int o = 2; o < 10; o++) {
               out.print(res[o] + " ");
             }
             out.println();
           }
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new CoinJam()).start();
   }
 }
