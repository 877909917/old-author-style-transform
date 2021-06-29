package com.company;
 
 import java.util.*;
 
 public class Third {
 	public static String computeRow(int index) {
 		Integer n = Main.readInt();
 		Integer k = Main.readInt();
 		Double u = Main.readDouble();
 		List<Double> list = new LinkedList<>();
 		Double res = 1.0;
 		for (int i = 0; i < n; i++) {
 			Double a = Main.readDouble();
 			list.add(a);
 		}
 		Collections.sort(list);
 		for (int i = 0; i < n; i++) {
 			Double change = i + 1 == n ? 1.0 - list.get(i) : list.get(i + 1) - list.get(i);
 			Double part;
 			if (change * (i + 1) <= u) {
 				part = change;
 			} else {
 				part = u / (i + 1);
 			}
 			for (int ii = 0; ii <= i; ii++) {
 				list.set(ii, list.get(ii) + part);
 				u = u -part;
 			}
 		}
 		for (int i = 0; i < n; i++) {
 			res = res * list.get(i);
 		}
 
 
 
 		return "Case #"+ index +": " + String.format("%.9f", res);
 	}
 
 	private static double min(List<Double> f) {
 		double res = 0;
 		int i = 0;
 		if (f == null)
 			return 0;
 		Collections.sort(f);
 		return f.get(0);
 	}
 
 	private static class City {
 		int dist;
 		int speed;
 		List<Integer> distances = new LinkedList<>();
 
 		public City(int dist, int speed) {
 			this.dist = dist;
 			this.speed = speed;
 		}
 	}
 
 	private static class Route {
 		int from;
 		int to;
 
 		public Route(int from, int to) {
 			this.from = from;
 			this.to = to;
 		}
 	}
 }
