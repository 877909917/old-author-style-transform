package com.company;
 
 import java.util.List;
 
 public class Second {
 	public static String computeRow(List<String> parsedRow, int index) {
 		Integer result = 0;
 		String last = null;
 		for (String c : parsedRow) {
 			if (last != null) {
 				if (!last.equals(c)) {
 					result++;
 				}
 			}
 			last = c;
 		}
 		if (parsedRow.get(parsedRow.size() - 1).equals("-")) {
 			result++;
 		}
 		return "Case #"+ index +": " + result;
 	}
 }
