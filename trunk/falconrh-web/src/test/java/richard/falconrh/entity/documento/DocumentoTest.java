package richard.falconrh.entity.documento;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoDocumento;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class DocumentoTest {
	private Documento documento;
	
	@Before
	public void setUp(){
		this.documento = new Documento();
	}
	
	@After
	public void tearDown(){
		this.documento = null;
	}
	
	@Test
	public void testGetsESets() {
		Date dataEmissao = new Date();
		Long id = 123L;
		String numero ="03930263610";
		Long idPessoa = 987L;
		Pessoa pessoa = new Pessoa(idPessoa);
		TipoDocumento tipoDocumento = TipoDocumento.CPF;
		documento.setDataEmissao(dataEmissao);
		documento.setId(id);
		documento.setNumero(numero);
		documento.setPessoa(pessoa);
		documento.setTipoDocumento(tipoDocumento);
		
		assertEquals(dataEmissao, documento.getDataEmissao());
		assertEquals(id, documento.getId());
		assertEquals(numero, documento.getNumero());
		assertEquals(pessoa, documento.getPessoa());
		assertEquals(tipoDocumento, documento.getTipoDocumento());
	}
	
	@Test
	public void testaConstrutoraComParametroTipoDocumentoENumeroDocumento(){
		TipoDocumento tipoDocumento = TipoDocumento.CPF;
		String numeroDocumento = "03930263610";
		documento = new Documento(tipoDocumento, numeroDocumento);
		assertEquals(tipoDocumento, documento.getTipoDocumento());
		assertEquals(numeroDocumento, documento.getNumero());
	}

}
