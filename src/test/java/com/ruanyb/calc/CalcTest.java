package com.ruanyb.calc;

import org.junit.Test;

public class CalcTest {
	
	@Test
	public void test() {
		
		Calculator calc = new Calculator();
		Double result = calc.calc("1+2*3*(5-3 * (3-1))");
		System.out.println(result);
	}
}
