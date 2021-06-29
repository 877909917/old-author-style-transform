package Qualifier;
 
 import java.math.BigInteger;
 
 public class CCoinJam {
     public static void main (String[] args) {
         int jamLength = 16;
         int jamCount = 50;
         
         long begin = (1 << jamLength - 1) + 1;
         long end = (1 << jamLength) - 1;
         
         System.out.println("Case #1:");
         
         int foundCount = 0;
         
         for (long x = begin; x <= end; x += 2) {
             String[] divisorList = coinJam(x);
             if (divisorList.length > 0) {
                 System.out.printf(Long.toBinaryString(x));
                 for (int i = 0; i < divisorList.length; i++)
                     System.out.printf(" %s", divisorList[i]);
                 System.out.println();
                 if (++foundCount >= jamCount) break;
             }
         }
     }
     
     static String[] coinJam(long testVal) {
         String[] divisorList = new String[9];
         for (int x = 2; x <= 10; x++) {
             String smallestDivisor = smallestDivisor(testVal, x);
             if (smallestDivisor.length() == 0) {
                 return new String[0];
             } else {
                 divisorList[x - 2] = smallestDivisor;
             }
         }
         return divisorList;
     }
     
     static String smallestDivisor(long testVal, int base) {
         BigInteger val = new BigInteger(Long.toBinaryString(testVal), base);
         BigInteger cap = val.shiftRight((val.bitLength() / 2) - 1); 
         for (BigInteger test = new BigInteger("3"); test.compareTo(cap) <= 0; test = test.add(new BigInteger("2"))) {
             if (val.mod(test).equals(BigInteger.ZERO)) return test.toString();
         }
         return "";
     }
 }
