package richard.falconrh.service.impl;

import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.localizacao.Municipio;
import richard.falconrh.modelo.BaseJPATest;
import richard.falconrh.modelo.enums.UF;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class MunicipioServicesImplTest extends BaseJPATest{
	
	private MunicipioServicesImpl municipioServices;
	
	static{
		nomeArquivoBase = "src/test/resources/municipios.xml";
		nomeArquivoAposInclusao = "src/test/resources/municipios-inclusao.xml";
		nomeArquivoAposAlteracao = "src/test/resources/municipios-alteracao.xml";
		nomeArquivoAposExclusao = "src/test/resources/municipios-exclusao.xml";
		nomeSequence = "SEQ_MUNICIPIOS";
	}
	
	/**
	 * Method setUp.
	
	 * @throws Exception */
	@Before
	public void setUp() throws Exception{
		super.setUp();
		municipioServices = new MunicipioServicesImpl();
		municipioServices.setEntityManager(getEntityManager());
	}
	
	/**
	 * Method cadastrarDeveCadastrarMunicipio.
	
	 * @throws Exception */
	@Test
	public void cadastrarDeveCadastrarMunicipio() throws Exception {
		Municipio municipio = new Municipio();
		municipio.setNome("Nome do Município");
		municipio.setUf(UF.ACRE);
		municipioServices.getEntityManager().getTransaction().begin();
		municipioServices.cadastrar(municipio);
		municipioServices.getEntityManager().getTransaction().commit();
		verificarEstruturaBancoAposInclusao("MUNICIPIOS");
	}

}
