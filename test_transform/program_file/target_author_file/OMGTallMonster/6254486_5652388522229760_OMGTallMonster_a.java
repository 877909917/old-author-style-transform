import java.util.*;
 
 class A {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static int countDigits(boolean[] seen, int n) {
       int out = 0;
       for (int i = n; i > 0; i /= 10) {
          int ndx = i%10;
          if (!seen[ndx]) {
             seen[ndx] = true;
             out++;
          }
       }
       return out;
    } 
 
    public static Object solve(Scanner scan) {
       int N = scan.nextInt();
       if (N == 0) { return "INSOMNIA"; }
 
       boolean[] seen = new boolean[10];
       int out = 0;
       for (int count = 0; count < 10; count += countDigits(seen, out))
          out += N;
       return out;
    }
 }
