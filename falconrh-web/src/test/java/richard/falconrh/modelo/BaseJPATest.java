package richard.falconrh.modelo;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Classe base para realização de testes utilizando o JUnit e DBUnit juntos
 * @author Richard Mendes Madureira
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
	
	/**
	 * Método que é executado antes da execução dos métodos de teste da classe (executado apenas uma vez por classe).
	 */
	@BeforeClass
	public static void beforeSetUp(){
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	/**
	 * Método que é executado após da execução dos métodos de teste da classe (executado apenas uma vez por classe).
	 */
	@AfterClass
	public static void afterTearDown(){
		entityManager = null;
		emf = null;
	}

	/**
	 * Método que retorna o entityManager.
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Método que realiza as configurações básicas antes de inicializar os testes.
	 * @throws Exception
	 */
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
	 * Método que obterm a conexão utilizada a partir da sessão do hibernate.
	 * @return Connection - a conexão atualmente utilizada 
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	protected Connection getConnection() throws SQLException{
		Session session = (Session) getEntityManager().getDelegate();
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		return cp.getConnection();
	}

	/**
	 * Método que substitui os valores nulos e algumas datas no formata string para objetos java.
	 * @param dataSet IDataSet
	 * @throws DatabaseUnitException
	 * @throws Exception
	 */
	protected void substituirDatasEValoresNulos(IDataSet dataSet) throws DatabaseUnitException, Exception{
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet); 
		replacementDataSet.addReplacementObject("[NULL]", null);
		replacementDataSet.addReplacementObject("[SYSDATE]", new Date());
		replacementDataSet.addReplacementObject("[31/12/09]", new Date());
		replacementDataSet.addReplacementObject("[01/01/09]", new Date());
	}
	
	/**
	 * Método que faz a verificação da estrutura do banco de dados após a execução de algum teste.
	 * @param arquivoDadosEsperado String que indica o caminho do arquivo xml com os dados que deverão estar no banco após a execução do teste.
	 * @param tabelas String[]- array que contém os nomes das tabelas que serão verificadas pelo método.
	 * @throws Exception
	 */
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
	 * Method Método que verifica a estrutura do banco de dados após a inclusão de dados.
	 * @param tabelas String[] a lista de tabelas que serão verificadas.
	 * @throws Exception
	 */
	public void verificarEstruturaBancoAposInclusao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessário informar o arquivo de comparacao", nomeArquivoAposInclusao);
		verificarEstruturaBanco(nomeArquivoAposInclusao, tabelas);
	}
	
	/**
	 * Método que verifica a estrutura do banco de dados após a alteração de dados.
	 * @param tabelas String[] a lista de tabelas que serão verificadas.
	 * @throws Exception
	 */
	public void verificarEstruturaBancoAposAlteracao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoAposAlteracao);
		verificarEstruturaBanco(nomeArquivoAposAlteracao, tabelas);
	}
	
	/**
	 * Método que verifica a estrutura do banco de dados após a exclusão de dados.
	 * @param tabelas String[] a lista de tabelas que serão verificadas.
	 * @throws Exception
	 */
	public void verificarEstruturaBancoAposExclusao(String... tabelas) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoAposExclusao);
		verificarEstruturaBanco(nomeArquivoAposExclusao, tabelas);
	}
	
	/**
	 * Método que retorna um objeto que representa uma tabela no dbunit.
	 * @param nomeTabela String - o nome da tabela do banco de dados.
	 * @return ITable
	 * @throws Exception
	 */
	public ITable getTabelaBase(String nomeTabela) throws Exception{
		assertNotNull("Para realizar a comparacao de dados apos a operacao é necessario informar o arquivo de comparacao", nomeArquivoBase);
		return new FlatXmlDataSetBuilder().build(new File(nomeArquivoBase)).getTable(nomeTabela);
	}
}