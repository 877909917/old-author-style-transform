package bathStall;
 import java.util.*;
 public class Execute {
 	 List<String> cs;
 	 String result;
 
      public Execute(List<String> thisCase){
     	 this.cs=thisCase;
     	 this.core();
      }
      private void core(){
     	 int num = Integer.parseInt(cs.get(0));
     	 int ppl = Integer.parseInt(cs.get(1));
 
 
 
 
 
 
 
 
 
 
     	 Queue<Integer> p = new PriorityQueue<>((a,b)->(b-a));
     	 p.add(num);
     	 while (ppl>0){
     		 int t = p.poll();
     		 int m = -1;
     		 if (t%2==1){
     			 m = t/2 +1;
     		 } else {
     			 m = t/2;
     		 }
     		 int s=m-1;
     		 int b=t-m;
     		 p.add(s);
     		 p.add(b);
     		 if (ppl == 1){
     			 result = b+" "+s;
     			 return;
     		 }
     		 ppl--;
     	 }
       }
      
      
      public String output(){
     	 return result;
      }
      
 }
