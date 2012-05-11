package richard.falconrh.entity.localizacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.UF;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class MunicipioTest {
	private Municipio municipio;
	
	@Before
	public void setUp(){
		municipio = new Municipio();
	}
	
	@After
	public void tearDown(){
		municipio = null;
	}
	
	@Test
	public void testaConstrutoraComParametroLong(){
		Long id = 3213L;
		municipio = new Municipio(id);
		assertEquals(id, municipio.getId());
	}
	
	@Test
	public void testGetsESets() {
		Long id = 1L;
		String nome = "Nome do Município";
		List<Bairro> listaBairros = new ArrayList<Bairro>();
		UF uf = UF.SANTA_CATARINA;

		municipio.setId(id);
		municipio.setListaBairros(listaBairros);
		municipio.setNome(nome);
		municipio.setUf(uf);
		
		assertEquals(id, municipio.getId());
		assertEquals(nome, municipio.getNome());
		assertEquals(uf, municipio.getUf());
		assertEquals(listaBairros, municipio.getListaBairros());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashParaObjetosIguais(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = municipio;
		assertEquals(municipio.hashCode(), municipio2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarHashesDiferentesParaMunicipiosDiferentes(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setNome("Joinville");
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertNotSame(municipio.hashCode(), municipio2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarHashesDiferentesParaUFsDiferentes(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setNome("Florianópolis");
		municipio2.setUf(UF.MINAS_GERAIS);
		
		assertNotSame(municipio.hashCode(), municipio2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashParaMesmoMunicipioEUFNula(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		
		Municipio municipio2 = new Municipio();
		municipio2.setNome("Florianópolis");
		
		assertEquals(municipio.hashCode(), municipio2.hashCode());
	}
	
	@Test
	public void hashCodeDeveRetornarMesmoHashParaMesmaUFENomesNulos(){
		municipio = new Municipio();
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertEquals(municipio.hashCode(), municipio2.hashCode());
	}
	
	@Test
	public void equalsDeveRetornarTrueParaMesmosObjetos(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = municipio;
		
		assertTrue(municipio.equals(municipio2));
		assertTrue(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarTrueParaObjetosComPropriedadesIdenticas(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setNome("Florianópolis");
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertTrue(municipio.equals(municipio2));
		assertTrue(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarFalseQuandoClassePaiForDiferente(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		String s = "teste";
		assertFalse(municipio.equals(s));
	}
	
	@Test
	public void equalsDeveRetornarFalseQuandoClassesSaoIrmas(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		/**
		 * @author richard
		 * @version $Revision: 1.0 $
		 */
		class Municipio2 extends Parent{
			private static final long serialVersionUID = 1L;
		}
		
		Municipio2 municipio2 = new Municipio2();
		assertFalse(municipio.equals(municipio2));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaNomeDoMunicipioNaoInformado(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertFalse(municipio.equals(municipio2));
		assertFalse(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaUfsDiferentes(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio.setNome("Florianópolis");
		municipio2.setUf(UF.MINAS_GERAIS);
		
		assertFalse(municipio.equals(municipio2));
		assertFalse(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaUmaDasUFsNula(){
		municipio = new Municipio();
		municipio.setNome("Florianópolis");
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setNome("Florianpópolis");
		
		assertFalse(municipio.equals(municipio2));
		assertFalse(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarTrueParaNomesNulosEUFsIguais(){
		municipio = new Municipio();
		municipio.setUf(UF.SANTA_CATARINA);
		
		Municipio municipio2 = new Municipio();
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertTrue(municipio.equals(municipio2));
		assertTrue(municipio2.equals(municipio));
	}
	
	@Test
	public void equalsDeveRetornarFalseParaNomesNulosEUFsIguais(){
		municipio = new Municipio();
		municipio.setUf(UF.MINAS_GERAIS);
		
		Municipio municipio2 = new Municipio();
		municipio2.setUf(UF.SANTA_CATARINA);
		
		assertFalse(municipio.equals(municipio2));
		assertFalse(municipio2.equals(municipio));
	}
}
