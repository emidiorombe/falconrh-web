package richard.falconrh.entity.competencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompetenciaTest {
	private Competencia competencia;
	
	@Before
	public void setUp() throws Exception{
		competencia = new Competencia();
	}
	
	@After
	public void tearDown() throws Exception{
		competencia = null;
	}
	
	@Test
	public void testaConstrutoraComParametroString(){
		String descricao = "Descricao";
		competencia = new Competencia(descricao);
		assertEquals(descricao, competencia.getDescricao());
	}
	
	@Test
	public void testaConstrutoraComVariosParametros(){
		Date dataAbertura = new Date();
		Date dataFechamento = new Date();
		String descricao = "Descricao";
		
		competencia = new Competencia(descricao, dataAbertura, dataFechamento);
		assertNull(competencia.getId());
		assertEquals(dataAbertura, competencia.getDataAbertura());
		assertEquals(dataFechamento, competencia.getDataFechamento());
		assertEquals(descricao, competencia.getDescricao());
	}
	
	@Test
	public void testaGestESets(){
		Long id = 1L;
		Date dataAbertura = new Date();
		Date dataFechamento = new Date();
		String descricao = "Janeiro/2012";
		
		competencia.setId(id);
		competencia.setDataAbertura(dataAbertura);
		competencia.setDataFechamento(dataFechamento);
		competencia.setDescricao(descricao);
		
		assertEquals(id, competencia.getId());
		assertEquals(dataAbertura, competencia.getDataAbertura());
		assertEquals(dataFechamento, competencia.getDataFechamento());
		assertEquals(descricao, competencia.getDescricao());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashParaMesmosObjetos(){
		Competencia competencia2 = competencia;
		assertEquals(competencia2.hashCode(), competencia.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashQuandoDescricaoForemIguais(){
		final String descricao ="08/2012";
		Competencia competencia2 = new Competencia();
		competencia2.setDescricao(descricao);
		competencia.setDescricao(descricao);
		assertEquals(competencia.hashCode(), competencia2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashQuandoDescricaoForemNulas(){
		Competencia competencia2 = new Competencia();
		competencia2.setDescricao(null);
		competencia.setDescricao(null);
		assertEquals(competencia.hashCode(), competencia2.hashCode());
	}
	
	@Test
	public void equalsDeveRetornarTrueParaMesmoObjetos(){
		assertTrue(competencia.equals(competencia));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaObjetoNulo(){
		assertFalse(competencia.equals(null));
	}
	
	@Test
	public void equalsDeveRetornarTrueParaObjetosComMesmosConteudos(){
		Competencia competencia2 = new Competencia();
		assertTrue(competencia.equals(competencia2));
		String descricao = "descricao";
		competencia.setDescricao(descricao);
		competencia2.setDescricao(descricao);
		assertTrue(competencia.equals(competencia2));
		Date dataAbertura = new Date();
		competencia.setDataAbertura(dataAbertura);
		competencia2.setDataAbertura(dataAbertura);
		assertTrue(competencia.equals(competencia2));
		Date dataFechamento = new Date();
		competencia.setDataFechamento(dataFechamento);
		competencia2.setDataFechamento(dataFechamento);
		assertTrue(competencia.equals(competencia2));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaObjetosQueNaoSaoCompetencias(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertFalse(competencia.equals(sdf));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaCompetenciasDiferentes(){
		String descricao = "descricao";
		Date dataAbertura = new Date();
		Date dataFechamento = new Date();
		competencia.setDescricao(descricao);
		competencia.setDataAbertura(dataAbertura);
		competencia.setDataFechamento(dataFechamento);
		
		Competencia competencia2 = new Competencia();
		assertFalse(competencia.equals(competencia2));
		assertFalse(competencia2.equals(competencia));
	}
	
}
