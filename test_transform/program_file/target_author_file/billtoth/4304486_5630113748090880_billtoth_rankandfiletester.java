import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class RankAndFileTester {
 
 	public static void main(String[] args) throws FileNotFoundException {
 		File inputFile = new File(args[0]);
 		RankAndFile t = new RankAndFile(inputFile);
 		t.evaluate();
 	}
 
 }
