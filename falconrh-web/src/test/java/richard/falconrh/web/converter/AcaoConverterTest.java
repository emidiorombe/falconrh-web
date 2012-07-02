package richard.falconrh.web.converter;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import richard.falconrh.entity.seguranca.Acao;
import richard.falconrh.service.AcaoServices;


@RunWith(PowerMockRunner.class)
@PrepareForTest(AcaoConverter.class)
public class AcaoConverterTest {
	private AcaoConverter acaoConverter;
	
	AcaoServices mockedAcaoServices = mock(AcaoServices.class);

	@Before
	public void setUp() throws Exception{
		acaoConverter = new AcaoConverter();
		AcaoConverter partialMocked = PowerMockito.spy(acaoConverter);
		PowerMockito.doReturn(mockedAcaoServices).when(partialMocked, "getAcaoServices");
	}
	
	@After
	public void tearDown() throws Exception{
		acaoConverter = null;
	}
	
	@Test
	public void getAsObject(){
		//TODO implementar
		fail("");
	}
	
	
	protected Acao criarAcao() {
		final Long idAcao = 1L;
		final String nomeAcao = "Cadastrar";
		Acao acaoRetorno = new Acao();
		acaoRetorno.setId(idAcao);
		acaoRetorno.setNome(nomeAcao);
		return acaoRetorno;
	}

}
