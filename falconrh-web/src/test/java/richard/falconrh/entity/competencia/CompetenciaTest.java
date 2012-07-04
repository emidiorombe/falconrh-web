package richard.falconrh.entity.competencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}
