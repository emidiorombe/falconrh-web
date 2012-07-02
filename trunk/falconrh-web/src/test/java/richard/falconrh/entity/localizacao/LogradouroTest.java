package richard.falconrh.entity.localizacao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.modelo.enums.TipoLogradouro;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class LogradouroTest {
	private Logradouro logradouro;
	
	@Before
	public void setUp(){
		this.logradouro = new Logradouro();
	}
	
	@After
	public void tearDown(){
		this.logradouro = null;
	}
	
	@Test
	public void testGetsESets(){
		Long idBairro = 3212L;
		Bairro bairro = new Bairro(idBairro);
		Integer cep = 88036001;
		Long id = 5648L;
		String descricao = "Nome do bairro";
		TipoLogradouro tipoLogradouro = TipoLogradouro.RUA;
		
		logradouro.setBairro(bairro);
		logradouro.setCep(cep);
		logradouro.setId(id);
		logradouro.setNome(descricao);
		logradouro.setTipoLogradouro(tipoLogradouro);
		
		assertEquals(bairro, logradouro.getBairro());
		assertEquals(cep, logradouro.getCep());
		assertEquals(id, logradouro.getId());
		assertEquals(descricao, logradouro.getNome());
		assertEquals(tipoLogradouro, logradouro.getTipoLogradouro());
		
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 76869L;
		logradouro = new Logradouro(id);
		assertEquals(id, logradouro.getId());
	}
}
