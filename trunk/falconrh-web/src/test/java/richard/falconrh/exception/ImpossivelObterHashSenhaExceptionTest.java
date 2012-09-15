package richard.falconrh.exception;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImpossivelObterHashSenhaExceptionTest {
	final String MESSAGE = "message";
	final Throwable CAUSE = new Throwable(MESSAGE);
	
	private ImpossivelObterHashSenhaException exception;
	
	@Before
	public void setUp(){
		exception = new ImpossivelObterHashSenhaException();
	}
	
	@After
	public void tearDown(){
		exception = null;
	}
	
	@Test
	public void testaConstrutoraSemParametros() {
		exception = new ImpossivelObterHashSenhaException();
		assertNull(exception.getMessage());
		assertNull(exception.getCause());
		assertNull(exception.getLocalizedMessage());
	}
	
	@Test
	public void testaConstrutoraComParametroString(){
		exception = new ImpossivelObterHashSenhaException(MESSAGE);
		assertEquals(MESSAGE, exception.getMessage());
		assertNull(exception.getCause());
		assertEquals(MESSAGE, exception.getLocalizedMessage());
	}
	
	@Test
	public void testaConsturtoraComParametro(){
		exception = new ImpossivelObterHashSenhaException(CAUSE);
		assertEquals(CAUSE, exception.getCause());
	}
	
	@Test
	public void testaConsturtoraComParametroStringEThrowable(){
		exception = new ImpossivelObterHashSenhaException(MESSAGE, CAUSE);
		assertEquals(MESSAGE, exception.getMessage());
		assertEquals(CAUSE, exception.getCause());
		assertEquals(MESSAGE, exception.getLocalizedMessage());
	}
	

}
