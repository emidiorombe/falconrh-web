package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class EstadoCivilTest {

	@Test
	public void test() {
		String descricao = "Solteiro(a)";
		EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;
		assertEquals(descricao, estadoCivil.getDescricao());
	}

}
