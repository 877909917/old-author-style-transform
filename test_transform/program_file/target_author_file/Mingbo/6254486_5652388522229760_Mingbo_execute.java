package CountingSheep;
 import java.util.*;
 public class Execute {
 	 String result;
      public Execute(int thisCase){
     	 this.core(thisCase);
      }
      private void core(int thisCase){
     	 if(thisCase == 0) {
     		 result= "INSOMNIA";
     		 return;
     	 }
     	 Set<Integer> digitSet = new HashSet<Integer>();
     	 int count=0;
     	 int base = thisCase;
     	 while(digitSet.size()<10 && count < 10000) {
     		 int temp = base;
     		 this.result = base+"";   		 
     		 while(temp>0 ){
     			 digitSet.add(temp%10);
     			 temp=temp/10;
     		 }
     		 System.out.println(digitSet);
     		 base = base + thisCase;
     		 count++;
     	 }
 
      }
      
      
      public String output(){
     	 return result;
      }
      
 }
