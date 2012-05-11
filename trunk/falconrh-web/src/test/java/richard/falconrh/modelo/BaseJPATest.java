package richard.falconrh.modelo;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.Connection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ejb.HibernateEntityManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
public class BaseJPATest {
	public static final String PERSISTENCE_UNIT_NAME = "falconrh-ejb-test";
	protected static String nomeArquivoBase;
	protected static String nomeArquivoAposInclusao;
	protected static String nomeArquivoAposAlteracao;
	protected static String nomeArquivoAposExclusao;
	protected static String nomeSequence;
	
	private static EntityManagerFactory emf;
	private static EntityManager entityManager;
	
	@BeforeClass
	public static void beforeSetUp(){
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	@AfterClass
	public static void afterTearDown(){
		entityManager = null;
		emf = null;
	}

	/**
	 * Method getEntityManager.
	
	 * @return EntityManager */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Method setUp.
	
	 * @throws Exception */
	public void setUp() throws Exception{
		entityManager = emf.createEntityManager();
		File fileDataSetInicial = new File(nomeArquivoBase);
		IDatabaseConnection databaseConnection = new DatabaseConnection(getConnection());
		IDataSet dataSetInicial = new FlatXmlDataSetBuilder().build(fileDataSetInicial);
		try{
			String sql = "ALTER SEQUENCE "+nomeSequence+" RESTART WITH 1";
			java.sql.PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
			prepareStatement.executeUpdate();
			DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSetInicial);
		}finally{
			databaseConnection.close();
		}
	}

	/**
	 * Method getConnection.
	
	 * @return Connection */
	@SuppressWarnings("deprecation")
	protected Connection getConnection(){
		return ((HibernateEntityManager)entityManager).getSession().connection();
	}

	/**
	 * Method substituirDatasEValoresNulos.
	 * @param dataSet IDataSet
	
	
	 * @throws DatabaseUnitException * @throws Exception */
	protected void substituirDatasEValoresNulos(IDataSet dataSet) throws DatabaseUnitException, Exception{
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet); 
		replacementDataSet.addReplacementObject("[NULL]", null);
		replacementDataSet.addReplacementObject("[SYSDATE]", new Date());
		replacementDataSet.addReplacementObject("[31/12/09]", new Date());
		replacementDataSet.addReplacementObject("[01/01/09]", new Date());
	}
	
	
	/**
	 * Method verificarEstruturaBanco.
	 * @param arquivoDadosEsperado String
	 * @param tabelas String[]
	
	 * @throws Exception */
	private void verificarEstruturaBanco(String arquivoDadosEsperado, String... tabelas) throws Exception{
		IDatabaseConnection databaseConnection = new DatabaseConnection(getConnection());
		IDataSet dataSetBanco = databaseConnection.createDataSet();
        IDataSet dataSetEsperado = new FlatXmlDataSetBuilder().build(new File(arquivoDadosEsperado));
        substituirDatasEValoresNulos(dataSetBanco);
        substituirDatasEValoresNulos(dataSetEsperado);
        ITable tabelaAtual = null;
        ITable tabelaEsperada = null;
        for(String tabela :  tabelas){
			tabelaAtual = dataSetBanco.getTable(tabela);
			tabelaEsperada = dataSetEsperado.getTable(tabela);
			Assertion.assertEquals(tabelaEsperada, tabelaAtual);
		}
	}
	
	/**
	 * Method verificarEstruturaBancoAposInclusao.
	 * @param tabelas String[]
	
	 * @throws Exception */
	public void verificarEstruturaBancoAposInclusao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessário informar o arquivo de comparacao", nomeArquivoAposInclusao);
		verificarEstruturaBanco(nomeArquivoAposInclusao, tabelas);
	}
	
	/**
	 * Method verificarEstruturaBancoAposAlteracao.
	 * @param tabelas String[]
	
	 * @throws Exception */
	public void verificarEstruturaBancoAposAlteracao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoAposAlteracao);
		verificarEstruturaBanco(nomeArquivoAposAlteracao, tabelas);
	}
	
	/**
	 * Method verificarEstruturaBancoAposExclusao.
	 * @param tabelas String[]
	
	 * @throws Exception */
	public void verificarEstruturaBancoAposExclusao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoAposExclusao);
		verificarEstruturaBanco(nomeArquivoAposExclusao, tabelas);
	}
	
	/**
	 * Method getTabelaBase.
	 * @param nomeTabela String
	
	
	 * @return ITable * @throws Exception */
	public ITable getTabelaBase(String nomeTabela) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoBase);
		return new FlatXmlDataSetBuilder().build(new File(nomeArquivoBase)).getTable(nomeTabela);
	}
}
