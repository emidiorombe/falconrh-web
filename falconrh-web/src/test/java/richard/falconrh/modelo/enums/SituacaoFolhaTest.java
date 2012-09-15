package richard.falconrh.modelo.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class SituacaoFolhaTest {
	
	@Test
	public void test(){
		SituacaoFolha situacaoFolha = SituacaoFolha.PREVIA;
		assertEquals("Situação Prévia", situacaoFolha.getDescricao());
	}
}
