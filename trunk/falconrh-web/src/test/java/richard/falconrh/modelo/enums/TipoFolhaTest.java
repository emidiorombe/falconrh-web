package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoFolhaTest {

	@Test
	public void test() {
		String descricao = "Folha Normal";
		TipoFolha tipoFolha = TipoFolha.NORMAL;
		assertEquals(descricao, tipoFolha.getDescricao());
	}

}
