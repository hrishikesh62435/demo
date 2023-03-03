package com.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	
	private Calculator c=new Calculator();
	
	

	
	@Test
	void testSum() {
		//expect
		int expected =138;
		
		int actual = c.doSum(45, 88, 5);
		
		assertThat(actual).isEqualTo(expected);
				
	}
	
	@Test
	void testProduct() {
		//expect
		int expected =30;
		
		int actual = c.doProduct(5, 6);
		
		assertThat(actual).isEqualTo(expected);
				
	}

}
