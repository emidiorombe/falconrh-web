package richard.falconrh.entity.inss;

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
public class TabelaINSSTest {
	private TabelaINSS tabelaINSS;
	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;
	public static final long HUM_DIA = 24*60*60*1000;
	
	@Before
	public void setUp(){
		tabelaINSS = new TabelaINSS();
		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.YEAR, 2011);
		dataInicioVigencia = new Date(calendar.getTimeInMillis());
		
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, 2011);
		dataTerminoVigencia = new Date(calendar.getTimeInMillis());
	}
	
	@After
	public void tearDown(){
		tabelaINSS = null;
		dataInicioVigencia = null;
		dataTerminoVigencia = null;
	}
	
	@Test
	public void testaGestsESets(){
		Long id = 654L;
		String descricao = "descricao";
		List<AliquotaINSS> listaAliquotasINSS = new ArrayList<AliquotaINSS>();
		AliquotaINSS aliquotaINSS = new AliquotaINSS();
		Long idAliquotaINSS = 777L;
		aliquotaINSS.setId(idAliquotaINSS );
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		tabelaINSS.setDescricao(descricao);
		tabelaINSS.setId(id);
		tabelaINSS.setListaAliquotasINSS(listaAliquotasINSS);
		
		assertEquals(id, tabelaINSS.getId());
		assertEquals(descricao, tabelaINSS.getDescricao());
		assertEquals(dataInicioVigencia, tabelaINSS.getDataInicioVigencia());
		assertEquals(dataTerminoVigencia, tabelaINSS.getDataTerminoVigencia());
		assertEquals(listaAliquotasINSS, tabelaINSS.getListaAliquotasINSS());
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 89473L;
		tabelaINSS = new TabelaINSS(id);
		assertEquals(id, tabelaINSS.getId());
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaForIgualADataInicioVigencia() {
		Date dataReferencia = dataInicioVigencia;
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaINSS.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaForIgualADataTerminoVigencia(){
		Date dataReferencia = dataTerminoVigencia;
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaINSS.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataEstiverEntreDatasVigencia(){
		//Coloquei a tempo exatamente entre as duas datas, mas pode ser qualquer outra data entre as vigencias
		Date dataReferencia = new Date((long) Math.floor((dataInicioVigencia.getTime() + dataTerminoVigencia.getTime())/2));
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		assertTrue(tabelaINSS.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarTrueQuandoDataReferenciaMaiorQueDataInicioVigenciaEDataTerminoVigenciaNula(){
		Date dataReferencia = new Date(dataInicioVigencia.getTime() + HUM_DIA);
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(null);
		assertTrue(tabelaINSS.isVigente(dataReferencia));
	}
	
	
	@Test
	public void isVigenteDeveRetornarFalseParaDataReferenciaInferiorADataDeInicioDeVigencia(){
		Date dataReferencia = new Date(dataInicioVigencia.getTime() - HUM_DIA);
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		assertFalse(tabelaINSS.isVigente(dataReferencia));
	}
	
	@Test
	public void isVigenteDeveRetornarFalseParaDataReferenciaSuperiorADataTerminoVigencia(){
		Date dataReferencia = new Date(dataTerminoVigencia.getTime() + HUM_DIA);
		tabelaINSS.setDataInicioVigencia(dataInicioVigencia);
		tabelaINSS.setDataTerminoVigencia(dataTerminoVigencia);
		assertFalse(tabelaINSS.isVigente(dataReferencia));
	}
	
}
