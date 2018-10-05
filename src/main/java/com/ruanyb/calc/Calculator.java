package com.ruanyb.calc;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	
	private Stack<Double> numStack;
	
	private Stack<String> opStack;
	
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	
	static {
		map.put("+", 1);
		map.put("-", 1);
		map.put("*", 2);
		map.put("/", 2);
		map.put("(", 0);
	}
	
	public Double calc(String input) {
		numStack = new Stack<Double>(200);
		opStack = new Stack<String>(200);
		for(int i = 0; i < input.length(); i++) {
			String op = input.substring(i, i + 1);
			if(Character.isDigit(op.charAt(0))) {
				int endIndex = readDouble(input, i);
				Double num = Double.parseDouble(input.substring(i, endIndex));
				numStack.push(num);
				i = endIndex - 1;
			}else if(op.equals("-") ) {
				if(i == 0 || input.charAt(i - 1) == '(') {
					int endIndex = readDouble(input, i);
					Double num = Double.parseDouble(input.substring(i, endIndex));
					numStack.push(num);
					i = endIndex - 1;
				}else {
					pushOpIntoStack(op);
				}
			}else if(op.equals("+") || op.equals("*")  || op.equals("/")) {
				pushOpIntoStack(op);
			}else if(op.equals("(")) {
				opStack.push(op);
			}else if(op.equals(")")) {
				while(true) {
					String opPop = opStack.pop();
					if(opPop.equals("(")) {
						break;
					}
					calcNum(opPop);
				}
			}
		}
		while(!opStack.isEmpty()) {
			String opPop = opStack.pop();
			calcNum(opPop);
		}
		return numStack.pop();
	}
	
	private void pushOpIntoStack(String op) {
		while(true) {
			if(opStack.isEmpty() || map.get(op) > map.get(opStack.getTop())) {
				opStack.push(op);
				break;
			}
			else {
				calcNum(opStack.pop());
			}
		}
	}
	
	private void calcNum(String op) {
		Double num2 = numStack.pop();
		Double num1 = numStack.pop();
		switch(op) {
			case "+" :
				numStack.push(num1 + num2);
				break;
			case "-" :
				numStack.push(num1 - num2);
				break;
			case "*" :
				numStack.push(num1 * num2);
				break;
			case "/" :
				numStack.push(num1 / num2);
				break;
		}
	}
	
	private int readDouble(String str, int start) {
		int end = start;
		while(end < str.length() && (Character.isDigit(str.charAt(end)) 
				|| str.charAt(end) == '.')) {
			end++;
		}
		return end;
	}
}
