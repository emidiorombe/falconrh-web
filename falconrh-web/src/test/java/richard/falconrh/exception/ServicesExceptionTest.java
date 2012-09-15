package richard.falconrh.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServicesExceptionTest {
	final String MESSAGE = "message";
	final Throwable CAUSE = new Throwable(MESSAGE);
	
	private ServicesException exception;
	
	@Before
	public void setUp(){
		exception = new ServicesException();
	}
	
	@After
	public void tearDown(){
		exception = null;
	}
	
	@Test
	public void testaConstrutoraSemParametros() {
		exception = new ServicesException();
		assertNull(exception.getMessage());
		assertNull(exception.getCause());
		assertNull(exception.getLocalizedMessage());
	}
	
	@Test
	public void testaConstrutoraComParametroString(){
		exception = new ServicesException(MESSAGE);
		assertEquals(MESSAGE, exception.getMessage());
		assertNull(exception.getCause());
		assertEquals(MESSAGE, exception.getLocalizedMessage());
	}
	
	@Test
	public void testaConsturtoraComParametro(){
		exception = new ServicesException(CAUSE);
		assertEquals(CAUSE, exception.getCause());
	}
	
	@Test
	public void testaConsturtoraComParametroStringEThrowable(){
		exception = new ServicesException(MESSAGE, CAUSE);
		assertEquals(MESSAGE, exception.getMessage());
		assertEquals(CAUSE, exception.getCause());
		assertEquals(MESSAGE, exception.getLocalizedMessage());
	}
	

}
