package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class EtniaTest {

	@Test
	public void test() {
		String descricao = "Pardo(a)";
		Etnia etnia = Etnia.PARDA;
		assertEquals(descricao, etnia.getDescricao());
	}

}
