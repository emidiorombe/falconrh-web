package richard.falconrh.entity.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import richard.falconrh.util.FalconRHUtils;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FalconRHUtils.class})
public class AgenciaTest {
	private Agencia agencia;
	
	@Before
	public void setUp(){
		PowerMockito.mockStatic(FalconRHUtils.class);
		this.agencia = new Agencia();
	}
	
	@After
	public void tearDown(){
		this.agencia = null;
	}
	
	@Test
	public void testGetsESets(){
		Long id = 1L;
		String nome = "Nome da Agência";
		String numero = "0016";
		String digitoVerificador = "7";
		Long idBanco = 777L;
		Banco banco = new Banco(idBanco);
		Long idContaCorrente = 1L;
		ContaCorrente contaCorrente = new ContaCorrente(idContaCorrente);
		List<ContaCorrente> listaContasCorrentes = new ArrayList<ContaCorrente>();
		listaContasCorrentes.add(contaCorrente);
		
		agencia.setId(id);
		agencia.setNome(nome);
		agencia.setNumero(numero);
		agencia.setDigitoVerificador(digitoVerificador);
		agencia.setBanco(banco);
		agencia.setListaContasCorrentes(listaContasCorrentes);
		
		assertEquals(id, agencia.getId());
		assertEquals(nome, agencia.getNome());
		assertEquals(numero, agencia.getNumero());
		assertEquals(digitoVerificador, agencia.getDigitoVerificador());
		assertEquals(banco, agencia.getBanco());
		assertEquals(listaContasCorrentes, agencia.getListaContasCorrentes());
	}
	
	@Test
	public void testarConstrutoraComParametroLong(){
		Long id = 654L;
		agencia = new Agencia(id);
		assertEquals(id, agencia.getId());
	}
	
	@Test
	public void getDescricaoDeveRetornarDescricaoContendoNumeroDVENomeQuantoTemDV(){
		final String descricaoEsperada = "0016-7/Praça XI de Novembro";
		agencia = new Agencia();
		agencia.setNome("Praça XI de Novembro");
		agencia.setNumero("0016");
		agencia.setDigitoVerificador("7");
		assertEquals(descricaoEsperada, agencia.getNumeroFormatado());
	}
	
	@Test
	public void getDescricaoDeveRetornarDescricaoContentoApenasNumeroENomeQuandoNaoTemDV(){
		final String descricaoEsperada = "0016/Praça XI de Novembro";
		agencia = new Agencia();
		agencia.setNome("Praça XI de Novembro");
		agencia.setNumero("0016");
		assertEquals(descricaoEsperada, agencia.getNumeroFormatado());
	}
	
	@Test
	public void isValidaDeveRetornarTrueParaAgenciaComDVCorreto(){
		String numero = "6510";
		String dv = "2";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dv);
		agencia = new Agencia();
		agencia.setNumero(numero);
		agencia.setDigitoVerificador(dv);
		assertTrue(agencia.isValida());
		PowerMockito.verifyStatic();
	}
	
	@Test
	public void isValidaDeveRetornarTrueParaAgenciaComDVCorretoX(){
		String numero = "0104";
		String dv = "X";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dv);
		agencia = new Agencia();
		agencia.setNumero(numero);
		agencia.setDigitoVerificador(dv);
		assertTrue(agencia.isValida());
		PowerMockito.verifyStatic();
	}
	
	@Test
	public void isValidaDeveRetornarFalseParaAgenciaComDVIncorreto(){
		String numero = "6845";
		String dvCorreto = "4";
		String dvIncorreto = "3";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dvCorreto);
		agencia = new Agencia();
		agencia.setNumero(numero);
		agencia.setDigitoVerificador(dvIncorreto);
		assertFalse(agencia.isValida());
		PowerMockito.verifyStatic();
	}
	
	@Test
	public void testCompareTo(){
		Agencia agencia = new Agencia();
		agencia.setId(1L);
		agencia.setNome("Bbbb");
		agencia.setNumero("1111");
		
		Agencia agencia2 = new Agencia();
		agencia2.setId(2L);
		agencia2.setNumero("2222");
		agencia2.setNome("Aaaa");
		int a = agencia.compareTo(agencia2);
		assertTrue(a<0);
		int a2 = agencia2.compareTo(agencia);
		assertTrue(a2>0);
		agencia2 = agencia;
		int a3 = agencia.compareTo(agencia2);
		assertEquals(0, a3);
	}
}
