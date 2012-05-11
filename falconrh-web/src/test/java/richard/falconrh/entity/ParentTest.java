package richard.falconrh.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class ParentTest {
	private Parent parent;
	
	@Before
	public void setUp(){
		parent = new ParentImpl();
	}
	
	@After
	public void tearDown(){
		parent = null;
	}
	/**
	 * Method testarGetsESets.
	
	
	 * @throws Exception * @throws IllegalAccessException */
	@Test
	public void testarGetsESets() throws Exception, IllegalAccessException{
		parent = new ParentImpl();
		Long id = 1L;
		parent.setId(id);
		assertEquals(id, parent.getId());
	}
	
	@Test
	public void equalsDeveRetornarTrueParaMesmoObjeto(){
		
		Parent parent2 = parent;
		assertTrue(parent.equals(parent2));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaObjetoNulo() {
		assertFalse(parent.equals(null));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaObjetosDeInstanciasDiferentes(){
		assertFalse(parent.equals(new Object()));
	}
	
	@Test
	public void equalsDeveRetornarTrueQuandoTodosObjetosSaoNulos(){
		Parent parent2 = new ParentImpl();
		assertTrue(parent.equals(parent2));
		assertTrue(parent2.equals(parent2));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaPeloMenosUmIdNuloEOutroNao(){
		Long id = 1L;
		parent.setId(id);
		Parent parent2 = new ParentImpl();
		assertFalse(parent.equals(parent2));
		assertFalse(parent2.equals(parent));
		
	}
	
	@Test
	public void hashCodeDeveRetornarHashesIguaisParaMesmoObjeto(){
		Parent parent2 = parent;
		assertEquals(parent.hashCode(), parent2.hashCode());
		Long id = 1L;
		parent.setId(id);
		parent2.setId(id);
		assertEquals(parent.hashCode(), parent2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarHashesDiferentesParaObjetosIguaisComIdsDiferentes(){
		Long id1 = 1L;
		Long id2 = 2L;
		Parent parent2 = new ParentImpl();
		parent.setId(id1);
		parent2.setId(id2);
		assertNotSame(parent.hashCode(), parent2.hashCode());
		parent.setId(null);
		assertNotSame(parent.hashCode(), parent2.hashCode());
	}
	
	/**
	 * @author richard
	 * @version $Revision: 1.0 $
	 */
	private class ParentImpl extends Parent{
		private static final long serialVersionUID = -901057184197424509L;
	}

}
