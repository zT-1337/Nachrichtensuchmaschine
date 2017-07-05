package test.util.date;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.util.date.DateConverter;

/**
 * 
 * @author Thomas Zerr
 *
 */
public class DateConverterTest {

	DateConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new DateConverter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidDate() {
		String date;
		String result;
		String answer;
		
		/*
		 * date: "01.06.1997"
		 * result: "19970601"
		 */
		date = "01.06.1997";
		result = converter.dateToNumber(date);
		answer = "19970601";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
		
		/*
		 * date: "01.01.2000"
		 * result: "20000101"
		 */
		date = "01.01.2000";
		result = converter.dateToNumber(date);
		answer = "20000101";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
		
		/*
		 * date: "24.12.2012"
		 * result: "20121224"
		 */
		date = "24.12.2012";
		result = converter.dateToNumber(date);
		answer = "20121224";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
	}
	
	@Test
	public void testInvalidDate() {
		String date;
		String result;
		
		/*
		 * date: null
		 * result: null
		 */
		date = null;
		result = converter.dateToNumber(date);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		/*
		 * date: ""
		 * result: null
		 */
		date = "";
		result = converter.dateToNumber(date);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		/*
		 * date: "1.6.97"
		 * result: null
		 */
		date = "1.6.97";
		result = converter.dateToNumber(date);
		assertNull("Result sollte null sein, ist aber " + result, result);
	}
	
	@Test
	public void testValidNumber() {
		String number;
		String result;
		String answer;
		
		number = "19970601";
		result = converter.numberToDate(number);
		answer = "01.06.1997";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
		
		number = "20000101";
		result = converter.numberToDate(number);
		answer = "01.01.2000";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
		
		number = "20121224";
		result = converter.numberToDate(number);
		answer = "24.12.2012";
		assertTrue("Result sollte " + answer + "sein, ist aber " + result, result.equals(answer));
	}
	
	@Test
	public void testInvalidNumber() {
		String number;
		String result;
		
		number = null;
		result = converter.numberToDate(number);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		number = "";
		result = converter.numberToDate(number);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		number = "1";
		result = converter.numberToDate(number);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		number = "1234";
		result = converter.numberToDate(number);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		number = "123456789";
		result = converter.numberToDate(number);
		assertNull("Result sollte null sein, ist aber " + result, result);
		
		
	}

}
