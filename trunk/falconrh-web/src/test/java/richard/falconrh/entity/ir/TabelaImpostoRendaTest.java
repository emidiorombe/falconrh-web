package richard.falconrh.entity.ir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class TabelaImpostoRendaTest {
	private static final long HUM_DIA = 24*60*60*1000;
	private TabelaImpostoRenda tabelaImpostoRenda;
	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;
	
	@Before
	public void setUp(){
		tabelaImpostoRenda = new TabelaImpostoRenda();
	
		Calendar calendar =  Calendar.getInstance(new Locale("pt", "BR"));
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.YEAR, 2011);
		dataInicioVigencia = calendar.getTime();
		
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, 2011);
		dataTerminoVigencia = calendar.getTime();
		
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
	}
	
	@After
	public void tearDown(){
		dataInicioVigencia = null;
		dataTerminoVigencia = null;
		tabelaImpostoRenda = null;
	}
	
	@Test
	public void testarGetsESets(){
		final Long id = 111L;
		final Integer anoBase = 2010;
		final String descricao = "descricao";
		
		List<AliquotaImpostoRenda> listaAliquotasImpostoRenda = new ArrayList<AliquotaImpostoRenda>();
		
		tabelaImpostoRenda.setAnoBase(anoBase);
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		tabelaImpostoRenda.setDescricao(descricao);
		tabelaImpostoRenda.setId(id);
		tabelaImpostoRenda.setListaAliquotasImpostoRenda(listaAliquotasImpostoRenda);
		
		assertEquals(anoBase, tabelaImpostoRenda.getAnoBase());
		assertEquals(descricao, tabelaImpostoRenda.getDescricao());
		assertEquals(dataInicioVigencia, tabelaImpostoRenda.getDataInicioVigencia());
		assertEquals(dataTerminoVigencia, tabelaImpostoRenda.getDataTerminoVigencia());
		assertEquals(id, tabelaImpostoRenda.getId());
		assertEquals(listaAliquotasImpostoRenda, tabelaImpostoRenda.getListaAliquotasImpostoRenda());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 4444L;
		tabelaImpostoRenda = new TabelaImpostoRenda(id);
		assertEquals(id, tabelaImpostoRenda.getId());
	}

	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaForIgualADataInicioVigencia() {
		Date dataReferencia = dataInicioVigencia;
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaImpostoRenda.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaForIgualADataTerminoVigencia(){
		Date dataReferencia = dataTerminoVigencia;
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaImpostoRenda.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataEstiverEntreDatasVigencia(){
		//Coloquei a tempo exatamente entre as duas datas, mas pode ser qualquer outra data entre as vigencias
		Date dataReferencia = new Date((long) Math.floor((dataInicioVigencia.getTime() + dataTerminoVigencia.getTime())/2));
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaImpostoRenda.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaMaiorQueDataInicioVigenciaEDataTerminoVigenciaNula(){
		Date dataReferencia = new Date(dataInicioVigencia.getTime() + HUM_DIA);
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(null);
		assertTrue(tabelaImpostoRenda.isVigente(dataReferencia));
	}
	
	
	@Test
	public void isVigenteDeveRetornarFalseParaDataReferenciaInferiorADataDeInicioDeVigencia(){
		Date dataReferencia = new Date(dataInicioVigencia.getTime() - HUM_DIA);
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		assertFalse(tabelaImpostoRenda.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarFalseParaDataReferenciaSuperiorADataTerminoVigencia(){
		Date dataReferencia = new Date(dataTerminoVigencia.getTime() + HUM_DIA);
		tabelaImpostoRenda.setDataInicioVigencia(dataInicioVigencia);
		tabelaImpostoRenda.setDataTerminoVigencia(dataTerminoVigencia);
		assertFalse(tabelaImpostoRenda.isVigente(dataReferencia));
	}
}
