import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class D {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
 
     private static int rows;
     private static int cols;
 
     private Omino[][] xOminos;
     private char[][] grid;
 
     static class Omino {
         char[][] g;
         int n;
         int m;
 
         Omino(String[] g) {
             n = g.length;
             m = g[0].length();
             this.g = new char[n][];
             for (int i = 0; i < n; ++i) {
                 this.g[i] = g[i].toCharArray();
             }
         }
 
         boolean canPut(char[][] grid, int r, int c) {
             if (n + r > rows || m + c > cols) {
                 return false;
             }
 
             for (int i = 0; i < n; ++i) {
                 for (int k = 0; k < m; ++k) {
                     int rp = i + r;
                     int cp = k + c;
                     if (g[i][k] == '*') {
                         if (grid[rp][cp] != '.') {
                             return false;
                         }
                     }
                 }
             }
 
             return true;
         }
 
         void put(char[][] grid, int r, int c) {
             for (int i = 0; i < n; ++i) {
                 for (int k = 0; k < m; ++k) {
                     int rp = i + r;
                     int cp = k + c;
                     if (g[i][k] == '*') {
                         grid[rp][cp] = '*';
                     }
                 }
             }
         }
 
         void remove(char[][] grid, int r, int c) {
             for (int i = 0; i < n; ++i) {
                 for (int k = 0; k < m; ++k) {
                     int rp = i + r;
                     int cp = k + c;
                     if (g[i][k] == '*') {
                         grid[rp][cp] = '.';
                     }
                 }
             }
         }
     }
 
     static Omino $(String... grid) {
         return new Omino(grid);
     }
 
     boolean canFill(Omino[] required, boolean requiredUsed) {
         Cell empty = findEmpty();
         if (empty == null) {
             return requiredUsed;
         }
 
 
 
 
         for (int r = 0; r < rows; ++r) {
             for (int c = 0; c < cols; ++c) {
                 
                 for (Omino omino : required) {
                     if (omino.canPut(grid, r, c)) {
                         omino.put(grid, r, c);
                         boolean can = canFill(required, true);
                         omino.remove(grid, r, c);
                         if (can) {
                             return true;
                         }
                     }
                 }
 
                 
                 for (Omino[] oneType : xOminos) {
                     if (oneType == required) {
                         continue; 
                     }
 
                     for (Omino omino : oneType) {
                         if (omino.canPut(grid, r, c)) {
                             omino.put(grid, r, c);
                             boolean can = canFill(required, false);
                             omino.remove(grid, r, c);
                             if (can) {
                                 return true;
                             }
                         }
                     }
                 }
             }
         }
 
         return false;
     }
 
     Cell findEmpty() {
         for (int i = 0; i < rows; ++i) {
             for (int k = 0; k < cols; ++k) {
                 if (grid[i][k] == '.') {
                     return new Cell(i, k);
                 }
             }
         }
 
         return null;
     }
 
     static class Cell {
         int r;
         int c;
 
         Cell(int r, int c) {
             this.r = r;
             this.c = c;
         }
     }
 
     void solve() {
         int x = scan.nextInt();
         rows = scan.nextInt();
         cols = scan.nextInt();
 
         xOminos = allOminos[x - 1];
 
         grid = new char[rows][];
         for (int i = 0; i < rows; ++i) {
             grid[i] = new char[cols];
             Arrays.fill(grid[i], '.');
         }
 
 
         boolean firstWin = false;
         for (Omino[] oneType : xOminos) {
             if (!canFill(oneType, false)) {
                 firstWin = true;
                 break;
             }
         }
 
 
         System.out.printf("%s\n", firstWin ? "RICHARD" : "GABRIEL");
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         String file = "D-small-attempt0";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new D().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 
     static Omino[][][] allOminos = new Omino[][][] {
             {
                     {
                             $("*")
                     }
             },
             {
                     {
                             $("**"),
                             $(
                                     "*",
                                     "*"
                             ),
                     }
             },
             {
                     {
                             $("***"),
                             $(
                                     "*",
                                     "*",
                                     "*"
                             )
                     },
                     {
                             
                             
                             $(
                                     "*.",
                                     "**"
                             ),
                             $(
                                     "**",
                                     "*."
                             ),
                             $(
                                     "**",
                                     ".*"
                             ),
                             $(
                                     ".*",
                                     "**"
                             )
                     }
             },
             {
                     
                     {
                             $("****"),
                             $(
                                     "*",
                                     "*",
                                     "*",
                                     "*"
                             )
                     },
                     
                     
                     
                     {
                             $(
                                     "*.",
                                     "**",
                                     ".*"
                             ),
                             $(
                                     ".*",
                                     "**",
                                     "*."
                             ),
                             $(
                                     ".**",
                                     "**."
                             ),
                             $(
                                     "**.",
                                     ".**"
                             ),
                     },
                     
                     
                     {
                             $(
                                     ".*.",
                                     "***"
                             ),
                             $(
                                     "***",
                                     ".*."
                             ),
                             $(
                                     "*.",
                                     "**",
                                     "*."
                             ),
                             $(
                                     ".*",
                                     "**",
                                     ".*"
                             )
                     },
                     
                     
                     {
                             $(
                                     "*..",
                                     "***"
                             ),
                             $(
                                     "..*",
                                     "***"
                             ),
                             $(
                                     "***",
                                     "*.."
                             ),
                             $(
                                     "***",
                                     "..*"
                             ),
                             $(
                                     "**",
                                     "*.",
                                     "*."
                             ),
                             $(
                                     "**",
                                     ".*",
                                     ".*"
                             ),
                             $(
                                     "*.",
                                     "*.",
                                     "**"
                             ),
                             $(
                                     ".*",
                                     ".*",
                                     "**"
                             ),
                     },
                     
                     
                     {
                             $(
                                     "**",
                                     "**"
                             )
                     }
             }
     };
 
 }
