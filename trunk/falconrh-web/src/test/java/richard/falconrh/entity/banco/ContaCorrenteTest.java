package richard.falconrh.entity.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class ContaCorrenteTest {
	private ContaCorrente contaCorrente;
	
	@Before
	public void setUp(){
		PowerMockito.mockStatic(FalconRHUtils.class);
		this.contaCorrente = new ContaCorrente();
	}
	
	@After
	public void tearDown(){
		this.contaCorrente = null;
	}
	
	@Test
	public void testaGetsESets(){
		Long id = 1L;
		Long idAgencia = 768L;
		Agencia agencia = new Agencia(idAgencia);
		String numero = "47554";
		String digitoVerificador = "8";
		contaCorrente.setId(id);
		contaCorrente.setAgencia(agencia);
		contaCorrente.setNumero(numero);
		contaCorrente.setDigitoVerificador(digitoVerificador);
		
		assertEquals(id, contaCorrente.getId());
		assertEquals(agencia, contaCorrente.getAgencia());
		assertEquals(numero, contaCorrente.getNumero());
		assertEquals(digitoVerificador, contaCorrente.getDigitoVerificador());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 1234L;
		contaCorrente = new ContaCorrente(id);
		assertEquals(id, contaCorrente.getId());
	}
	
	@Test
	public void getDescricaoDeveRetornarNumeroEDVQuandoDVExiste(){
		final String descricaoEsperada = "47554-8";
		contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("47554");
		contaCorrente.setDigitoVerificador("8");
		assertEquals(descricaoEsperada, contaCorrente.getDescricao());
	}
	
	@Test
	public void getDescricaoDeveRetornarApenasNumeroQuandoDVNaoExiste(){
		final String descricaoEsperada = "47554";
		contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("47554");
		assertEquals(descricaoEsperada, contaCorrente.getDescricao());
	}
	
	@Test
	public void isValidaDeveRetornarTrueParaContaComDVCorreto(){
		String numero = "47554";
		String dv = "8";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dv);
		contaCorrente = new ContaCorrente();
		contaCorrente.setNumero(numero);
		contaCorrente.setDigitoVerificador(dv);
		assertTrue(contaCorrente.isValida());
	}
	
	@Test
	public void isValidaDeveRetornarTrueParaContaComDVCorretoX(){
		String numero = "100140";
		String dv = "x";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dv);
		contaCorrente = new ContaCorrente();
		contaCorrente.setNumero(numero);
		contaCorrente.setDigitoVerificador(dv);
		assertTrue(contaCorrente.isValida());
	}
	
	@Test
	public void isValidaDeveRetornarFalseParaContaComDVIncorreto(){
		String numero = "47554";
		String dvIncorreto = "0";
		String dvCorreto = "1";
		Mockito.when(FalconRHUtils.getDigitoVerificador(numero)).thenReturn(dvCorreto);
		contaCorrente = new ContaCorrente();
		contaCorrente.setNumero(numero);
		contaCorrente.setDigitoVerificador(dvIncorreto);
		assertFalse(contaCorrente.isValida());
	}
}
