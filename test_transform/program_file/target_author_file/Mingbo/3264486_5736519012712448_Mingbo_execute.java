package oversizedPancakeFlipper;
 import java.util.*;
 public class Execute {
 	 List<String> thisCase;
 	 String result;
 
      public Execute(List<String> thisCase){
     	 this.thisCase=thisCase;
     	 this.core();
      }
      private void core(){
     	 String panCake = thisCase.get(0);
     	 List<Character> l = new ArrayList<>();
     	 for (int i=0; i<panCake.length(); i++){
     		 l.add(panCake.charAt(i));
     	 }
     	 int size = Integer.parseInt(thisCase.get(1));
     	 int count =0;
     	 int i = 0;
     	 while (i<=l.size()-size){
     		 if (l.get(i)=='-'){
     			 for (int j=i; j<i+size; j++){
     				 if (l.get(j)=='-'){
     					 l.set(j, '+');
     				 } else {
     					 l.set(j, '-');
     				 }
     			 }
     			 count++;
     		 }
     		 i++;
     	 } System.out.println(l);
     	 while (i < l.size()){
     		 if (l.get(i) == '-'){
     			 result = "IMPOSSIBLE";
     			 return;
     		 }
     		 i++;
     	 }
     	 result = count+"";
      }
      
      
      public String output(){
     	 return result;
      }
      
 }
