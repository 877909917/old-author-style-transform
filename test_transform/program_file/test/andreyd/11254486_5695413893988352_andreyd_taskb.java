import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             String s = sc.nextLine();
             String c = s.substring(0, (s.length() - 1)/2);
             String j = s.substring((s.length() - 1)/2 + 1);
             
             String resC = "";
             String resJ = "";
             Boolean minimizeC = null;
             for (int k = 0; k < c.length(); k++) {
                 char currC = c.charAt(k);
                 char currJ = j.charAt(k);
                 if (minimizeC == null) {
                     if (currC == '?' && currJ == '?') {
                         currC = '0';
                         currJ = '0';
                     } else if (currC == '?') {
                         currC = currJ;
                     } else if (currJ == '?') {
                         currJ = currC;
                     } else {
                         if (currC > currJ) {
                             minimizeC = true;
                         } else if (currC < currJ) {
                             minimizeC = false;
                         }
                     }
                 } else {
                     currC = min(currC, minimizeC);
                     currJ = min(currJ, !minimizeC);
                 }
                 resC += currC;
                 resJ += currJ;
             }
             
             print(i, resC + " " + resJ);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static char min(char c, boolean val) {
         return c == '?' ? (val ? '0' : '9') : c;
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
 
 }
