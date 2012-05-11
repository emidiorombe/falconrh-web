package richard.falconrh.entity.localizacao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class EnderecoTest {
	private Endereco endereco;
	
	@Before
	public void setUp(){
		endereco = new Endereco();
	}
	
	@After
	public void tearDown(){
		endereco = null;
	}
	
	@Test
	public void testaGetsESets(){
		Long id = 321L;
		String complemento = "complemento";
		Long idLogradouro = 987L;
		String numero = "987654";
		Logradouro logradouro = new Logradouro(idLogradouro);
		
		endereco.setComplemento(complemento);
		endereco.setId(id);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		
		assertEquals(complemento, endereco.getComplemento());
		assertEquals(id, endereco.getId());
		assertEquals(logradouro, endereco.getLogradouro());
		assertEquals(numero, endereco.getNumero());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 3L;
		endereco = new Endereco(id);
		assertEquals(id, endereco.getId());
	}
}
