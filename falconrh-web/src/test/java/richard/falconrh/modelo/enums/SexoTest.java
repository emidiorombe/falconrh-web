package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class SexoTest {

	@Test
	public void test() {
		String descricao = "Masculino";
		Sexo sexo = Sexo.MASCULINO;
		assertEquals(descricao, sexo.getDescricao());
	}

}
