package com.ak.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NumberToStringConverter {

	private final Map<Integer, String> singleDigitMap = new HashMap<Integer, String>();
	private final Map<Integer, String> tenMap = new HashMap<Integer, String>();
	
	private final Map<Integer, String> unitsMap = new HashMap<Integer, String>();
	
	public static void main(String [] args) {
		final NumberToStringConverter ntsc = new NumberToStringConverter();
		ntsc.init();
		
		// Tests
		System.out.println("5: " + ntsc.convertNumberToString(5));
		System.out.println("17: " + ntsc.convertNumberToString(17));
		System.out.println("60: " + ntsc.convertNumberToString(60));
		System.out.println("23: " + ntsc.convertNumberToString(23));
		System.out.println("112: " + ntsc.convertNumberToString(112));
		System.out.println("168: " + ntsc.convertNumberToString(168));
		System.out.println("170: " + ntsc.convertNumberToString(170));
		System.out.println("1,683: " + ntsc.convertNumberToString(1683));
		System.out.println("1,700: " + ntsc.convertNumberToString(1700));
		System.out.println("11,683: " + ntsc.convertNumberToString(11683));
		System.out.println("320,683: " + ntsc.convertNumberToString(320683));
		System.out.println("4,321,683: " + ntsc.convertNumberToString(4321683));
		System.out.println("54,321,683: " + ntsc.convertNumberToString(54321683));
		System.out.println("954,321,683: " + ntsc.convertNumberToString(954321683));
		System.out.println("1,000,000,030: " + ntsc.convertNumberToString(1000000030));
	        // Maximum integer value possible
		System.out.println("2,147,483,647: " + ntsc.convertNumberToString(2147483647));
	}
	
	private void init() {
		singleDigitMap.put(0, "zero");
		singleDigitMap.put(1, "one");
		singleDigitMap.put(2, "two");
		singleDigitMap.put(3, "three");
		singleDigitMap.put(4, "four");
		singleDigitMap.put(5, "five");
		singleDigitMap.put(6, "six");
		singleDigitMap.put(7, "seven");
		singleDigitMap.put(8, "eight");
		singleDigitMap.put(9, "nine");
		
		tenMap.put(10, "ten");
		tenMap.put(11, "eleven");
		tenMap.put(12, "twelve");
		tenMap.put(13, "thirteen");
		tenMap.put(14, "fourteen");
		tenMap.put(15, "fifteen");
		tenMap.put(16, "sixteen");
		tenMap.put(17, "seventeen");
		tenMap.put(18, "eighteen");
		tenMap.put(19, "nineteen");
		
		tenMap.put(20, "twenty");
		tenMap.put(30, "thirty");
		tenMap.put(40, "forty");
		tenMap.put(50, "fifty");
		tenMap.put(60, "sixty");
		tenMap.put(70, "seventy");
		tenMap.put(80, "eighty");
		tenMap.put(90, "ninety");
		
		unitsMap.put(2, "hundred");
		unitsMap.put(3, "thousand");
		unitsMap.put(6, "million");
		unitsMap.put(9, "billion");
		unitsMap.put(12, "trillion");
	}
	private String convertNumberToString(Integer number) {
		if (singleDigitMap.containsKey(number)) {
			return singleDigitMap.get(number);
		}
		
		if (tenMap.containsKey(number)) {
			return tenMap.get(number);
		}
		
		final Stack<String> stack = new Stack<String>();
		int n = number;
		int m = 0;
		int idx = 0;
		int lIdx = 0;
		while (n != 0) {
			int t = n % 10;
			n /= 10;
			
			if (idx > 2 && 
					unitsMap.containsKey(idx) &&
					t != 0) {
				stack.push(" " + unitsMap.get(idx));
			}
			
			if (t == 0) {
				idx++;
				if (lIdx == 2) {
					lIdx = -1;
				}
				lIdx++;
				continue;
			}
			
			if (lIdx == 0) {
				if (n % 10 != 1) {
				    stack.push(" " + singleDigitMap.get(t));
				} else {
					m = t;
				}
			} else if (lIdx == 1) {
				stack.push(" " + tenMap.get(t * 10 + m));
				m = 0;
			} else {
				stack.push(" " + singleDigitMap.get(t) + " " + unitsMap.get(lIdx));
				lIdx = -1;
			}
			
			idx++;
			lIdx++;
		}
		final StringBuilder sbuilder = new StringBuilder();
		while (!stack.isEmpty()) {
			sbuilder.append(stack.pop());
		}
		return sbuilder.toString();
	}
}
