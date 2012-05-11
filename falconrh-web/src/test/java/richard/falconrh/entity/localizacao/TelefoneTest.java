package richard.falconrh.entity.localizacao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoTelefone;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TelefoneTest {
	private Telefone telefone;
	
	@Before
	public void setUp(){
		this.telefone = new Telefone();
	}
	
	@After
	public void tearDown(){
		this.telefone = null;
	}
	
	@Test
	public void testaGetsESets(){
		Long id = 555L;
		Integer ddd = 48;
		Long numero = 97867564L;
		TipoTelefone tipoTelefone = TipoTelefone.CELULAR;
		Long idPessoa = 12L;
		Pessoa pessoa = new Pessoa(idPessoa);
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		listaPessoas.add(pessoa);
		
		telefone.setDdd(ddd);
		telefone.setId(id);
		telefone.setListaPessoas(listaPessoas);
		telefone.setNumero(numero);
		telefone.setTipoTelefone(tipoTelefone);
		
		assertEquals(id, telefone.getId());
		assertEquals(ddd, telefone.getDdd());
		assertEquals(numero, telefone.getNumero());
		assertEquals(listaPessoas, telefone.getListaPessoas());
		assertEquals(tipoTelefone, telefone.getTipoTelefone());
	}
	
	@Test
	public void testaConstrutoraComParametrosTipoTelefoneDDDENumero(){
		TipoTelefone tipoTelefone = TipoTelefone.CORPORATIVO;
		Integer ddd = 99;
		Long numero = 10101010L;

		telefone = new Telefone(tipoTelefone, ddd, numero);
		
		assertEquals(tipoTelefone, telefone.getTipoTelefone());
		assertEquals(ddd, telefone.getDdd());
		assertEquals(numero, telefone.getNumero());
	}
}