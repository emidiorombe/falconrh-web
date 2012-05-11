package richard.falconrh.entity.localizacao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.modelo.enums.TipoLogradouro;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class BairroTest {
	private Bairro bairro;
	
	@Before
	public void setUp(){
		bairro = new Bairro();
	}
	
	@After
	public void tearDown(){
		bairro = null;
	}
	
	@Test
	public void testarGetsESets(){
		Long idBairro = 123L;
		Long idLogradouro = 321L;
		String nome = "Trindade";
		Long idMunicipio = 987L;
		Municipio municipio = new Municipio(idMunicipio);
		Logradouro logradouro = new Logradouro();
		logradouro.setId(idLogradouro);
		logradouro.setNome("Lauro Linhares");
		logradouro.setTipoLogradouro(TipoLogradouro.RUA);
		logradouro.setBairro(bairro);
		Set<Logradouro> listaLogradouros = new HashSet<Logradouro>();
		listaLogradouros.add(logradouro);
		bairro.setId(idBairro);
		bairro.setNome(nome);
		bairro.setListaLogradouros(listaLogradouros);
		bairro.setMunicipio(municipio);
		
		assertEquals(idBairro, bairro.getId());
		assertEquals(nome, bairro.getNome());
		assertArrayEquals(listaLogradouros.toArray(), bairro.getListaLogradouros().toArray());
		assertEquals(municipio, bairro.getMunicipio());
	}
	
	public void testaConstrutoraSemParametroLong(){
		Long id = 3456L;
		bairro = new Bairro(id);
		assertEquals(id, bairro.getId());
	}
}