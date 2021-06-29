package contest_2017.round_1b;
 
 import java.io.File;
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Solution;
 
 
 public class A extends Solution {
 
     
 
     private static final String stringPath = "A-small-attempt1";
     
 
     public static void main(String[] args) {
         try {
 
             
             
 
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             List<String> inputFile = new ArrayList<>();
             int T = Integer.parseInt(rawInputFile.remove(0));
             inputFile.add(Integer.toString(T));
             for (int i = 0; i < T; i++) {
                 String[] DN = rawInputFile.remove(0).split(" ");
                 int D = Integer.parseInt(DN[0]);
                 int N = Integer.parseInt(DN[1]);
                 String others = rawInputFile.remove(0);
                 for (int j = 1; j < N; j++) {
                     others += " " + rawInputFile.remove(0);
                 }
                 inputFile.add(Integer.toString(D));
                 inputFile.add(Integer.toString(N));
                 inputFile.add(others);
             }
             A solver = new A(inputFile);
 
             String[] result = solver.solve();
 
             write("out" + File.separatorChar + stringPath + ".out", result);
 
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected A(String stringPath) throws IOException {
         super(stringPath);
     }
 
     protected A(final List<String> inputFile) throws IOException {
         super(inputFile);
     }
 
     protected Runnable getNewRunnable(final List<Integer> i) {
         return new SolveRunnable(i);
     }
 
     private class SolveRunnable implements Runnable {
         private final List<Integer> I;
 
         private SolveRunnable(final List<Integer> i) {
             I = i;
         }
 
         public void run() {
             for (Integer i : I) {
                 long startTime = System.currentTimeMillis();
                 final int D = Integer.parseInt(m_inputFile.get(i * 3 + 1));
                 final int N = Integer.parseInt(m_inputFile.get(i * 3 + 2));
                 final String[] others = m_inputFile.get(i * 3 + 3).split(" ");
                 final int[] K = new int[N];
                 final int[] S = new int[N];
                 for (int j = 0; j < N; j++) {
                     K[j] = Integer.parseInt(others[j * 2]);
                     S[j] = Integer.parseInt(others[j * 2 + 1]);
                 }
 
                 double lastArrival = (D - K[0]) * 1.0 / S[0];
                 for (int j = 1; j < N; j++) {
                     if ((D - K[j]) * 1.0 / S[j] > lastArrival) {
                         lastArrival = (D - K[j]) * 1.0 / S[j];
                     }
                 }
 
                 String solution = String.format("%06f", D / lastArrival);
 
                 m_results[i] = solution;
                 long duration = System.currentTimeMillis() - startTime;
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(String.format("%03d/%03d (%dms)", m_done, m_numOfProblems, duration));
                 }
             }
         }
     }
 }
