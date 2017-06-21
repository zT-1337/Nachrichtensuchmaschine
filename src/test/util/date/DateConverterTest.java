package test.util.date;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.util.date.DateConverter;

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

}
