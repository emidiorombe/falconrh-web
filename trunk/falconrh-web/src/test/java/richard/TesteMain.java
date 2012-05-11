package richard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import richard.falconrh.entity.banco.Agencia;
import richard.falconrh.entity.banco.Banco;
import richard.falconrh.entity.banco.ContaCorrente;
import richard.falconrh.entity.inss.AliquotaINSS;
import richard.falconrh.entity.inss.TabelaINSS;
import richard.falconrh.entity.ir.AliquotaImpostoRenda;
import richard.falconrh.entity.ir.TabelaImpostoRenda;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.entity.pessoa.Usuario;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

/**
Classe Principal criada para alguns testes
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
public class TesteMain {
	public static final Logger logger = Logger.getLogger(TesteMain.class);

	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		logger.debug("Iniciando");
		logger.info("info");
		logger.warn("warn");
		logger.fatal("fatal");
		new TesteMain();
	}
	
	public TesteMain(){
		logger.debug("Iniciando");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("falconrh-ejb-test");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		logger.debug("Iniciando");
//		gravarTabelaIR(entityManager);
//		gravarTabelaINSS(entityManager);
		gravarPessoa(entityManager);
		gravarUsuario(entityManager);
		
		logger.debug("Iniciando");
		System.out.println("Fim");
		logger.debug("fim");
		System.exit(0);
	}
	
	/**
	 * Method gravarBancoAgenciaEContaCorrente.
	 * @param entityManager EntityManager
	 */
	public void gravarBancoAgenciaEContaCorrente(EntityManager entityManager){
		Banco banco = new Banco();
		banco.setCodigoFebraban("001");
		banco.setNome("Banco do Brasil S/A");
		
		Agencia agencia = new Agencia();
		agencia.setBanco(banco);
		agencia.setNumero("0016");
		agencia.setDigitoVerificador("7");
		agencia.setNome("Agência Florianópolis - Praça XV de Setembro");
		
		
		
		Set<Agencia> listaAgencias = new HashSet<Agencia>();
		listaAgencias.add(agencia);
		banco.setListaAgencias(listaAgencias);
		
		
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setAgencia(agencia);
		contaCorrente.setNumero("47554");
		contaCorrente.setDigitoVerificador("8");
		
		agencia.setBanco(banco);
		contaCorrente.setAgencia(agencia);
		
		entityManager.getTransaction().begin();
		entityManager.persist(banco);
		entityManager.persist(agencia);
		entityManager.persist(contaCorrente);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Method gravarTabelaIR.
	 * @param entityManager EntityManager
	 */
	public void gravarTabelaIR(EntityManager entityManager){
		TabelaImpostoRenda tabelaImpostoRenda = new TabelaImpostoRenda();
		tabelaImpostoRenda.setAnoBase(2010);
		tabelaImpostoRenda.setDataInicioVigencia(new Date());
		tabelaImpostoRenda.setDataTerminoVigencia(null);
		tabelaImpostoRenda.setDescricao("Tabela de Imposto de Renda 2011 (Ano Base 2010)");

		AliquotaImpostoRenda aliquota1 = new AliquotaImpostoRenda();
		aliquota1.setDescricao("Até R$ 1.499.15");
		aliquota1.setAliquota(0d);
		aliquota1.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquota1.setValorInicial(0d);
		aliquota1.setValorFinal(1499.15d);
		aliquota1.setValorParcelaADeduzir(0d);
		
		AliquotaImpostoRenda aliquota2 = new AliquotaImpostoRenda();
		aliquota2.setDescricao("De R$ 1.499.16 a R$ 2.246,75");
		aliquota2.setAliquota(7.5d);
		aliquota2.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquota2.setValorInicial(1499.16d);
		aliquota2.setValorFinal(2246.75d);
		aliquota2.setValorParcelaADeduzir(112.43d);
		
		AliquotaImpostoRenda aliquota3 = new AliquotaImpostoRenda();
		aliquota3.setDescricao("De R$ 2.246,76 a R$ 2.995,70");
		aliquota3.setAliquota(15.0d);
		aliquota3.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquota3.setValorInicial(2246.76d);
		aliquota3.setValorFinal(2995.70d);
		aliquota3.setValorParcelaADeduzir(280.94d);
		
		AliquotaImpostoRenda aliquota4 = new AliquotaImpostoRenda();
		aliquota4.setDescricao("De 2.995,71 a R$ 3.743,19");
		aliquota4.setAliquota(22.5d);
		aliquota4.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquota4.setValorInicial(2995.71d);
		aliquota4.setValorFinal(3743.19d);
		aliquota4.setValorParcelaADeduzir(505.62d);
		
		AliquotaImpostoRenda aliquota5 = new AliquotaImpostoRenda();
		aliquota5.setDescricao("Acima de R$ 3.743,19");
		aliquota5.setAliquota(27.5d);
		aliquota5.setTabelaImpostoRenda(tabelaImpostoRenda);
		aliquota5.setValorInicial(3743.19d);
		aliquota5.setValorFinal(null);
		aliquota5.setValorParcelaADeduzir(692.78);
		
		List<AliquotaImpostoRenda> listaTabelaAliquotaImpostoRenda = new ArrayList<AliquotaImpostoRenda>();
		listaTabelaAliquotaImpostoRenda.add(aliquota1);
		listaTabelaAliquotaImpostoRenda.add(aliquota2);
		listaTabelaAliquotaImpostoRenda.add(aliquota3);
		listaTabelaAliquotaImpostoRenda.add(aliquota4);
		listaTabelaAliquotaImpostoRenda.add(aliquota5);
		tabelaImpostoRenda.setListaAliquotasImpostoRenda(listaTabelaAliquotaImpostoRenda);
		
		entityManager.getTransaction().begin();
		entityManager.persist(tabelaImpostoRenda);
		entityManager.persist(aliquota1);
		entityManager.persist(aliquota2);
		entityManager.persist(aliquota3);
		entityManager.persist(aliquota4);
		entityManager.persist(aliquota5);
		
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Method gravarTabelaINSS.
	 * @param entityManager EntityManager
	 */
	public void gravarTabelaINSS(EntityManager entityManager){
		TabelaINSS tabelaINSS = new TabelaINSS();
		tabelaINSS.setDescricao("Tabela de contribuição mensal do INSS 2011");
		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JULY);
		calendar.set(Calendar.YEAR, 2011);
		tabelaINSS.setDataInicioVigencia(calendar.getTime());
		
		List<AliquotaINSS> listaAliquotasINSS = new ArrayList<AliquotaINSS>();
		
		AliquotaINSS aliquotaINSS1 = new AliquotaINSS();
		aliquotaINSS1.setDescricao("Até R$ 1.107,52");
		aliquotaINSS1.setValorInicial(0D);
		aliquotaINSS1.setValorFinal(1107.52D);
		aliquotaINSS1.setAliquota(8.00D);
		aliquotaINSS1.setTabelaINSS(tabelaINSS);
		
		AliquotaINSS aliquotaINSS2 = new AliquotaINSS();
		aliquotaINSS2.setDescricao("De R$ 1.107,52 a R$ 1.845,87");
		aliquotaINSS2.setValorInicial(1107.53);
		aliquotaINSS2.setValorFinal(1845.87);
		aliquotaINSS2.setAliquota(9.00D);
		aliquotaINSS2.setTabelaINSS(tabelaINSS);
		
		AliquotaINSS aliquotaINSS3 = new AliquotaINSS();
		aliquotaINSS3.setDescricao("De R$ 1.845,88 a R$ 3.691,74");
		aliquotaINSS3.setValorInicial(1845.88);
		aliquotaINSS3.setValorFinal(3691.74);
		aliquotaINSS3.setAliquota(11.00D);
		aliquotaINSS3.setTabelaINSS(tabelaINSS);
		
		listaAliquotasINSS.add(aliquotaINSS1);
		listaAliquotasINSS.add(aliquotaINSS2);
		listaAliquotasINSS.add(aliquotaINSS3);
		tabelaINSS.setListaAliquotasINSS(listaAliquotasINSS);
		
		entityManager.getTransaction().begin();
		entityManager.persist(tabelaINSS);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Method gravarPessoa.
	 * @param entityManager EntityManager
	 */
	private void gravarPessoa(EntityManager entityManager){
		Pessoa pessoa = criarPessoa();
		
		entityManager.getTransaction().begin();
		entityManager.persist(pessoa);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Method gravarUsuario.
	 * @param entityManager EntityManager
	 */
	private void gravarUsuario(EntityManager entityManager){
		Usuario usuario = criarUsuario();
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Method criarUsuario.
	
	 * @return Usuario */
	private Usuario criarUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNome("Richard Mendes Madureira");
		usuario.setDeficienteFisico(Boolean.FALSE);
		usuario.setDataNascimento(new Date());
		usuario.setEmail("richard.madureira@gmail.com");
		usuario.setEstadoCivil(EstadoCivil.SOLTEIRO);
		usuario.setEtnia(Etnia.NEGRA);
		usuario.setNacionalidade(Nacionalidade.BRASILEIRO);
		usuario.setSexo(Sexo.MASCULINO);
		usuario.setLogin("richard.madureira");
		usuario.setSenha("123456789");
		
		return usuario;
		
	}

	/**
	 * Method criarPessoa.
	
	 * @return Pessoa */
	private Pessoa criarPessoa(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Richard Mendes Madureira");
		pessoa.setDeficienteFisico(Boolean.FALSE);
		pessoa.setDataNascimento(new Date());
		pessoa.setEmail("richard.madureira@gmail.com");
		pessoa.setEstadoCivil(EstadoCivil.SOLTEIRO);
		pessoa.setEtnia(Etnia.NEGRA);
		pessoa.setNacionalidade(Nacionalidade.BRASILEIRO);
		pessoa.setSexo(Sexo.MASCULINO);
		return pessoa;
	}
}
