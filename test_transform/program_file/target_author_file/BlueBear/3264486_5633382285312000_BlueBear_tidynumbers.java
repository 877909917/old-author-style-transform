import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class TidyNumbers {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("B-small-attempt0.in"));
         PrintStream cout = new PrintStream("B-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
             long n = cin.nextLong();
             char[] s = ("" + n).toCharArray();
 
             for (int i = 0; i < s.length - 1; i++)
                 if (s[i] > s[i + 1]) {
                     int j = i;
                     for (; j >= 0; j--)
                         if (s[j] < s[j + 1]) {
                             break;
                         }
                     s[j + 1]--;
                     for (int k = j + 2; k < s.length; k++)
                         s[k] = '9';
 
                     break;
                 }
 
             long ans = 0;
             for (char ch : s)
                 ans = ans * 10 + ch - '0';
 
             cout.printf("Case #%d: %d%n", _case, ans);
         }
 
         cin.close();
         cout.close();
     }
 }
