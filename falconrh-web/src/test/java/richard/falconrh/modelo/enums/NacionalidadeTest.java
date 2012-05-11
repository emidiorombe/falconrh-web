package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class NacionalidadeTest {

	@Test
	public void test() {
		String descricao = "Brasileiro(a)";
		Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
		assertEquals(descricao, nacionalidade.getDescricao());
	}

}
