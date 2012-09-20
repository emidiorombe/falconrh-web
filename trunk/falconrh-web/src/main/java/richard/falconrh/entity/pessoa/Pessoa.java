package richard.falconrh.entity.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.documento.Documento;
import richard.falconrh.entity.localizacao.Endereco;
import richard.falconrh.entity.localizacao.Telefone;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="PESSOAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_PESSOAS") })
@Inheritance(strategy =InheritanceType.JOINED)
public class Pessoa extends Parent implements Cloneable, Comparable<Pessoa>{
	private static final long serialVersionUID = 7713203491119053888L;

	private String nome;
	private Date dataNascimento;
	private Sexo sexo;
	private Etnia etnia;
	private EstadoCivil estadoCivil;
	private Nacionalidade nacionalidade;
	private String email;
	private Endereco endereco;
	private List<Telefone> listaTelefones;
	private List<Documento> listaDocumentos;
	private Boolean deficienteFisico;
	
	public Pessoa(){
	}
	
	/**
	 * Método construtor para Pessoa.
	 * @param id - o identificador único da Pessoa (chave primária).
	 */
	public Pessoa(Long id) {
		this.id = id;
	}

	/**
	 * Método que obtem o nome da pessoa.
	 * @return String que representa o nome da pessoa.
	 */
	@Column(length=255,  nullable=false)
	public String getNome() {
		return nome;
	}

	/**
	 * Método que retorna a data de nascimento da pessoa.
	 * @return Date a data de nascimento da pessoa.
	 */
	@Temporal(value=TemporalType.DATE)
	@Column(nullable=false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Método que retorna o sexo da pessoa.
	 * @return Sexo - o sexo da pessoa .
	 */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Método que retorna a Etnia da pessoa.
	 * @return a Etnia da pessoa.
	 */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public Etnia getEtnia() {
		return etnia;
	}

	/**
	 * Método que retorna o estado civil da pessoa.
	 * @return EstadoCivil o Estado civil da pessoa.
	 */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Método que retorna a nacionalidade da pessoa
	 * @return Nacionalidade - enum que contém a nacionalidade da pessoa.
	 */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=20)
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}
	
	/**
	 * Método que retorna o email da pessoa..
	 * @return String que representa o email da pessoa.
	 */
	@Column(nullable=true, length=255)
	public String getEmail() {
		return email;
	}

	/**
	 * Método que retorna o endereço da pessoa.
	 * @return um objeto Endereco que contém o endereço da pessoa.
	 */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ENDERECO", nullable=true)//FIXME MUDAR PARA nullable=true após incluir o endereço na tela
	public Endereco getEndereco() {
		return endereco;
	}
	
	/**
	 * Método que retorna a lista de telefones da Pessoa.
	 * @return instância de Set<Telefone> que contém os telefones da pessoa.
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="PESSOAS_TELEFONES", joinColumns = @JoinColumn(name="ID_PESSOA", referencedColumnName="ID"), inverseJoinColumns= @JoinColumn(name="ID_TELEFONE", referencedColumnName="ID"))
	public List<Telefone> getListaTelefones(){
		return listaTelefones;
	}
	
	/**
	 * Método que retorna a lista de documentos da pessoa.
	 * @return instância de List<Documento> que contém a os documentos da pessoa.
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pessoa", targetEntity=Documento.class)
	public List<Documento> getListaDocumentos(){
		return listaDocumentos;
	}
	
	/**
	 * Método que retorna um booleano que informa se a pessoa é portadora de alguma necessidade especial.
	 * @return Boolean , sendo <code>true</code>em caso positovo e <code>false</code> em caso contrário.
	 */
	@Column(nullable=false)
	public Boolean getDeficienteFisico(){
		return deficienteFisico;
	}
	
	/**
	 * Método que altera o nome da pessoa.
	 * @param nome String que será o novo nome da pessoa.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método que altera a data de nascimento da pessoa
	 * @param dataNascimento Date
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Método que altera o sexo da pessoa.
	 * @param sexo Sexo
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/**
	 * Método que altera a Etnia da pessoa.
	 * @param etnia Etnia
	 */
	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}
	
	/**
	 * Método que altera o estado civil da pessoa.
	 * @param estadoCivil EstadoCivil
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * Método que altera a nacionalidade da pessoa.
	 * @param nacionalidade Nacionalidade
	 */
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	/**
	 * Método que altera o email da pessoa.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que altera o endereço da pessoa.
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Método que altera a lista de telefones da pessoa.
	 * @param listaTelefones Set<Telefone>
	 */
	public void setListaTelefones(List<Telefone> listaTelefones){
		this.listaTelefones = listaTelefones;
	}
	
	/**
	 * Método que altera a lista de documentos da pessoa.
	 * @param listaDocumentos
	 */
	public void setListaDocumentos(List<Documento> listaDocumentos){
		this.listaDocumentos = listaDocumentos;
	}
	
	/**
	 * Método que altera o indicador de que a pessoa é deficiente ou não.
	 * @param deficienteFisico Boolean
	 */
	public void setDeficienteFisico(Boolean deficienteFisico){
		this.deficienteFisico = deficienteFisico;
	}

	/**
	 * Método que adiciona um telefone à lista de telefones da pessoa.
	 * @param telefone Telefone
	 */
	public void adicionarTelefone(Telefone telefone) {
		if(getListaTelefones()==null){
			setListaTelefones(new ArrayList<Telefone>());
		}
		if(telefone!=null && telefone.getListaPessoas()==null){
			telefone.setListaPessoas(new ArrayList<Pessoa>());
		}
		if(telefone !=null && !telefone.getListaPessoas().contains(this)){
			telefone.getListaPessoas().add(this);
		}
		getListaTelefones().add(telefone);
	}
	
	/**
	 * Método que adiciona um documento à lista de documentos da pessoa.
	 * @param documento
	 */
	public void adicionarDocumento(Documento documento) {
		if(getListaDocumentos()==null){
			setListaDocumentos(new ArrayList<Documento>());
		}
		documento.setPessoa(this);
		getListaDocumentos().add(documento);
	}
	
	/**
	 * Método que remove um telefone da lista de telefones da pessoa.
	 * @param telefone Telefone a ser removido da lista
	 * @return boolean, sendo <code>true</code>caso a remoção seja bem sucedida e <code>false</code>caso a exclusão seja mal sucedida (ou não tenha o telefone na lista de telefones).
	 */
	public boolean removerTelefone(Telefone telefone){
		if(getListaTelefones()==null){
			setListaTelefones(new ArrayList<Telefone>());
		}else if(!getListaTelefones().isEmpty()){
			return getListaTelefones().remove(telefone);
		}
		return false;
	}
	
	/**
	 * Método que remove um documento da lista de documentos da pessoa.
	 * @param documento Documento a ser removido da lista
	 * @return boolean, sendo <code>true</code>caso a remoção seja bem sucedida e <code>false</code>caso a exclusão seja mal sucedida (ou não tenha o documento na lista de documentos).
	 */
	public boolean removerDocumento(Documento documento){
		if(getListaDocumentos()==null){
			setListaDocumentos(new ArrayList<Documento>());
		}
		if(!getListaDocumentos().contains(documento)){
			return getListaDocumentos().remove(documento);
		}
		return false;
	}

	/**
	 * Método que retorna o hash do objeto Pessoa
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	/**
	 * Método que compara dois objetos Pessoa.<br/>
	 * Uma instância de Um Objeto Pessoa será igual a outra, quando o nome, data de nascimento e sexo das pessoas forem iguais.
	 * @param obj Object a ser comparado com a Pessoa
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Pessoa)) {
			return false;
		}
		Pessoa other = (Pessoa) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null) {
				return false;
			}
		} else if (!dataNascimento.equals(other.dataNascimento)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (sexo != other.sexo) {
			return false;
		}
		return true;
	}

	/**
	 * Método que realiza a comparação entre duas instâncias da Classe Pessoa.
	 */
	@Override
	public int compareTo(Pessoa pessoa) {
		if(this.getId()!=null & pessoa.getId()!=null){
			return this.getId().compareTo(pessoa.getId());
		}
		if(StringUtils.isNotBlank(this.getNome()) && StringUtils.isNotBlank(pessoa.getNome())){
			return this.getNome().compareTo(pessoa.getNome());
		}
		return 0;
	}

}