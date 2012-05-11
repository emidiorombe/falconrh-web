package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class UFTest {

	@Test
	public void getSiglaDeveRetornarSiglaDoEstado() {
		String siglaEsperada = "SC";
		UF uf = UF.SANTA_CATARINA;
		assertEquals(siglaEsperada, uf.getSigla());
	}

	@Test
	public void getNomeDeveRetornarNomeDoEstado() {
		String nomeEsperado = "Santa Catarina";
		UF uf = UF.SANTA_CATARINA;
		assertEquals(nomeEsperado, uf.getNome());
	}
	
	@Test
	public void toStringDeveRetornarNomeDoEstado(){
		assertEquals("UF: Santa Catarina", UF.SANTA_CATARINA.toString());
	}
}
