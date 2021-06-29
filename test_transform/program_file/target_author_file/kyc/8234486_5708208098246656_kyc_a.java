import java.util.*;
 import java.io.*;
 
 public class A
 {
     static int height;
     static int width;
     static char[][] grid;
 
     static int numChanges;
 
     public static void main(String ... orange) throws Exception
     {
         Scanner input = new Scanner(System.in);
         int numCases = input.nextInt();
         for (int n = 0; n < numCases; n++)
         {
             height = input.nextInt();
             width = input.nextInt();
             grid = new char[height][];
             for (int i = 0; i < height; i++)
                 grid[i] = input.next().toCharArray();
 
             numChanges = 0;
             calculate();
 
             System.out.printf("Case #%d: ", n + 1);
             System.out.println(numChanges == -1 ? "IMPOSSIBLE" : numChanges);
         }
     }
 
     static void calculate()
     {
         for (int i = 0; i < height; i++)
             for (int j = 0; j < width; j++)
                 if (die(i, j))
                 {
                     boolean canSave = false;
                     for (char c : ">v<^".toCharArray())
                     {
                         grid[i][j] = c;
                         if (!die(i, j))
                         {
                             numChanges++;
                             canSave = true;
                             break;
                         }
                     }
                     if (!canSave)
                     {
                         numChanges = -1;
                         return;
                     }
                 }
     }
 
     static boolean die(int i, int j)
     {
         int[] dx = {1, 0, -1, 0};
         int[] dy = {0, 1, 0, -1};
         int dir = ">v<^".indexOf(grid[i][j]);
         if (dir == -1)
             return false;
 
         i += dy[dir];
         j += dx[dir];
         while (inBounds(i, j))
         {
             if (">v<^".indexOf(grid[i][j]) != -1)
                 return false;
             i += dy[dir];
             j += dx[dir];
         }
         return true;
     }
 
     static boolean inBounds(int i, int j)
     {
         return i >= 0 && i < height && j >= 0 && j < width;
     }
 }
