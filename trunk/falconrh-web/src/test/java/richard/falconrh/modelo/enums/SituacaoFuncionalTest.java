package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class SituacaoFuncionalTest {

	@Test
	public void test() {
		String descricao = "Aposentado por Idade";
		SituacaoFuncional situacaoFuncional = SituacaoFuncional.APOSENTADO_POR_IDADE;
		assertEquals(descricao, situacaoFuncional.getDescricao());
	}
}
