package richard.falconrh.modelo.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TipoVinculoEmpresaTest {

	@Test
	public void test() {
		String descricao = "Empregado(a)";
		TipoVinculoEmpresa tipoVinculo = TipoVinculoEmpresa.EMPREGADO;
		assertEquals(descricao, tipoVinculo.getDescricao());
	}

}
