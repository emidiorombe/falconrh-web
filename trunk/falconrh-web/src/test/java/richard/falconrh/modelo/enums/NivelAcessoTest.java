package richard.falconrh.modelo.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class NivelAcessoTest {

	@Test
	public void test() {
		NivelAcesso nivelAcesso = NivelAcesso.NACIONAL;
		assertEquals("Nacional", nivelAcesso.getNome());
	}

}
