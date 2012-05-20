package richard.falconrh.entity.pessoa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.localizacao.Telefone;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;
import richard.falconrh.modelo.enums.TipoTelefone;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class PessoaTest {
	private Pessoa pessoa;
	
	@Before
	public void setUp(){
		this.pessoa = new Pessoa();
	}
	
	@After
	public void tearDown(){
		this.pessoa = null;
	}
	
	@Test
	public void testaGetsESets(){
		Date dataNascimento = new Date();
		Boolean deficienteFisico = Boolean.TRUE;
		String email = "richard.madureira@gmail.com";
		EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;
		Etnia etnia = Etnia.NEGRA;
		Telefone telefone = new Telefone(TipoTelefone.CELULAR, 38, 98765432L);
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		listaTelefones.add(telefone);
		Long id = 656565L;
		Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
		String nome = "Richard";
		Sexo sexo = Sexo.MASCULINO;
				
		pessoa.setDataNascimento(dataNascimento);
		
		pessoa.setDeficienteFisico(deficienteFisico);
		pessoa.setEmail(email);
		pessoa.setEstadoCivil(estadoCivil);
		pessoa.setEtnia(etnia);
		pessoa.setId(id);
		pessoa.setListaTelefones(listaTelefones);
		pessoa.setNacionalidade(nacionalidade);
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		
		assertEquals(dataNascimento, pessoa.getDataNascimento());
		assertEquals(deficienteFisico, pessoa.getDeficienteFisico());
		assertEquals(email, pessoa.getEmail());
		assertEquals(estadoCivil, pessoa.getEstadoCivil());
		assertEquals(etnia, pessoa.getEtnia());
		assertEquals(id, pessoa.getId());
		assertEquals(listaTelefones, pessoa.getListaTelefones());
		assertEquals(nacionalidade, pessoa.getNacionalidade());
		assertEquals(nome, pessoa.getNome());
		assertEquals(sexo, pessoa.getSexo());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 5678L;
		pessoa = new Pessoa(id);
		assertEquals(id, pessoa.getId());
	}
	
	@Test
	public void testaAdicionarTelefone(){
		Telefone telefone = new Telefone(TipoTelefone.CELULAR, 48, 96149698L);
		pessoa.adicionarTelefone(telefone);
		assertTrue(pessoa.getListaTelefones().contains(telefone));
		assertEquals(1, pessoa.getListaTelefones().size());
	}
	
	@Test
	public void testaRemoverTelefone(){
		Telefone telefone = new Telefone(TipoTelefone.CELULAR, 48, 96149698L);
		pessoa.setListaTelefones(new ArrayList<Telefone>());
		pessoa.getListaTelefones().add(telefone);
		assertTrue(pessoa.getListaTelefones().contains(telefone));
		pessoa.removerTelefone(telefone);
		assertFalse(pessoa.getListaTelefones().contains(telefone));
		assertEquals(0, pessoa.getListaTelefones().size());
	}
}
