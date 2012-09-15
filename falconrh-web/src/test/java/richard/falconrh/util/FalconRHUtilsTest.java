package richard.falconrh.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**Classe de testes da classe utilitária <link>richard.falconrh.util.FalconRHUtils</link>
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public class FalconRHUtilsTest {
	private static String senhaPlana;
	private static String hashSenhaEsperada;
	private static String hashSenhaObtida;
	
	@Test
	public void getHashSenhaDeveRetornarNullQuandoSenhaForNula() {
		senhaPlana = null;
		hashSenhaObtida = FalconRHUtils.getHashSenha(senhaPlana);
		assertNull(hashSenhaObtida);
	}
	
	@Test
	public void getHashSenhaDeveRetornarBlankQuandoSenhaBlank() {
		senhaPlana = "";
		hashSenhaEsperada = "";
		hashSenhaObtida = FalconRHUtils.getHashSenha(senhaPlana);
		assertSame(hashSenhaEsperada, hashSenhaObtida);
	}
	
	@Test
	public void getHashSenhaDeveRetornarHashDaSenha(){
		senhaPlana = "senhaPlana";
		hashSenhaEsperada = "ABMPTkSyTW26CA3HsdoptTiIDS7nQqknehOssgxE/6k=";
		hashSenhaObtida = FalconRHUtils.getHashSenha(senhaPlana);
		assertEquals(hashSenhaEsperada, hashSenhaObtida);
	}
	
	@Test
	public void concatenarSeparadoPontoDeveRetornarDuasStringsConcatenadasESeparadasPorPonto() {
		String string1 = "nome";
		String string2 = "sobrenome";
		String stringEsperada = "nome.sobrenome";
		String stringObtida = FalconRHUtils.concat(string1, string2);
		assertEquals(stringEsperada, stringObtida);
	}
	
	@Test
	public void getDigitoVerificadorDeveRetornarDigitoVerificadorCorreto(){
		String dvEsperado = "X";
		String numero = "0104";
		assertEquals(dvEsperado, FalconRHUtils.getDigitoVerificador(numero));
		
		dvEsperado = "X";
		numero = "100140";
		assertEquals(dvEsperado, FalconRHUtils.getDigitoVerificador(numero));
		
		dvEsperado = "9";
		numero = "022810";
		assertEquals(dvEsperado, FalconRHUtils.getDigitoVerificador(numero));
	}
	
	@Test
	public void isCPFValidoDeveRetornarTrueParaCPFValido() {
		String cpf = "03930263610";
		assertTrue(FalconRHUtils.isCPFValido(cpf));
	}
	
	@Test
	public void isCPFValidoDeveRetornarFalseParaCPFComNumerosIguais() {
		String cpf = "22222222222";
		assertFalse(FalconRHUtils.isCPFValido(cpf));
	}
	
	@Test
	public void isCNPJValidoDeveRetornarTrueParaNumeroValido(){
		String cnpj = "42422253000101";
		assertTrue(FalconRHUtils.isCNPJValido(cnpj));
	}
	
	@Test
	public void isCNPJValidoDeveRetornarFalseParaNumeroInvalido(){
		String cnpj = "42422253000186";
		assertFalse(FalconRHUtils.isCNPJValido(cnpj));
	}
	
	@Test
	public void isPISValidoDeveRetornarTrueParaNumeroValido(){
		String pis = "19037314492";
		assertTrue(FalconRHUtils.isPISValido(pis));
	}
	
	@Test
	public void isPISValidoDeveRetornarFalseParaNumeroInvalido(){
		String pis = "123456789012";
		assertFalse(FalconRHUtils.isPISValido(pis));
	}
	
	@Test
	public void getSomenteNumerosDeveRetornarApenasNumeros(){
		String valor = "a1b2c3d";
		String valorEsperado = "123";
		assertEquals(valorEsperado, FalconRHUtils.getSomenteNumeros(valor));
	}
	
	@Test
	public void getSomenteNumerosDeveRetornarNullQuandoValorEntradaForNulo(){
		assertNull(FalconRHUtils.getSomenteNumeros(null));
	}
	
	@Test
	public void isCPFValidoDeveRetornarFalseParaCPFInvalido(){
		String cpf = "12345678900";
		assertFalse(FalconRHUtils.isCPFValido(cpf));
	}

}


