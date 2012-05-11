package richard.falconrh.entity.pessoa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.localizacao.Telefone;
import richard.falconrh.modelo.enums.EstadoCivil;
import richard.falconrh.modelo.enums.Etnia;
import richard.falconrh.modelo.enums.Nacionalidade;
import richard.falconrh.modelo.enums.Sexo;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="PESSOAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_PESSOAS") })
@Inheritance(strategy =InheritanceType.JOINED)
public class Pessoa extends Parent implements Cloneable{
	private static final long serialVersionUID = 7713203491119053888L;

	private String nome;
	private Date dataNascimento;
	private Sexo sexo;
	private Etnia etnia;
	private EstadoCivil estadoCivil;
	private Nacionalidade nacionalidade;
	private String email;
//	private Endereco endereco;
	private Set<Telefone> listaTelefones;
	private Boolean deficienteFisico;
	
	public Pessoa(){
	}
	
	/**
	 * Constructor for Pessoa.
	 * @param id Long
	 */
	public Pessoa(Long id) {
		this.id = id;
	}

	/**
	 * Method getNome.
	
	 * @return String */
	@Column(length=255,  nullable=false)
	public String getNome() {
		return nome;
	}

	/**
	 * Method getDataNascimento.
	
	 * @return Date */
	@Temporal(value=TemporalType.DATE)
	@Column(nullable=false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Method getSexo.
	
	 * @return Sexo */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Method getEtnia.
	
	 * @return Etnia */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public Etnia getEtnia() {
		return etnia;
	}

	/**
	 * Method getEstadoCivil.
	
	 * @return EstadoCivil */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=10)
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Method getNacionalidade.
	
	 * @return Nacionalidade */
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false, length=20)
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}
	
	/**
	 * Method getEmail.
	
	 * @return String */
	@Column(nullable=true, length=255)
	public String getEmail() {
		return email;
	}

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="ID_ENDERECO", nullable=true)//FIXME MUDAR PARA nullable=true ap�s incluir o endere�o na tela
//	public Endereco getEndereco() {
//		return endereco;
//	}
	
	/**
	 * Method getListaTelefones.
	
	 * @return Set<Telefone> */
	@ManyToMany
	@JoinTable(name="PESSOAS_TELEFONES", joinColumns = @JoinColumn(name="ID_PESSOA", referencedColumnName="ID"), inverseJoinColumns= @JoinColumn(name="ID_TELEFONE", referencedColumnName="ID"))
	public Set<Telefone> getListaTelefones(){
		return listaTelefones;
	}
	
	/**
	 * Method getDeficienteFisico.
	
	 * @return Boolean */
	@Column(nullable=false)
	public Boolean getDeficienteFisico(){
		return deficienteFisico;
	}
	
	
	/**
	 * Method setNome.
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Method setDataNascimento.
	 * @param dataNascimento Date
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Method setSexo.
	 * @param sexo Sexo
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}


	/**
	 * Method setEtnia.
	 * @param etnia Etnia
	 */
	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}
	
	/**
	 * Method setEstadoCivil.
	 * @param estadoCivil EstadoCivil
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * Method setNacionalidade.
	 * @param nacionalidade Nacionalidade
	 */
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	/**
	 * Method setEmail.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

//	public void setEndereco(Endereco endereco) {
//		this.endereco = endereco;
//	}
	
	/**
	 * Method setListaTelefones.
	 * @param listaTelefones Set<Telefone>
	 */
	public void setListaTelefones(Set<Telefone> listaTelefones){
		this.listaTelefones = listaTelefones;
	}
	
	/**
	 * Method setDeficienteFisico.
	 * @param deficienteFisico Boolean
	 */
	public void setDeficienteFisico(Boolean deficienteFisico){
		this.deficienteFisico = deficienteFisico;
	}

	/**
	 * Method adicionarTelefone.
	 * @param telefone Telefone
	 */
	public void adicionarTelefone(Telefone telefone) {
		if(getListaTelefones()==null){
			setListaTelefones(new HashSet<Telefone>());
		}
		getListaTelefones().add(telefone);
	}
	
	/**
	 * Method removerTelefone.
	 * @param telefone Telefone
	
	 * @return boolean */
	public boolean removerTelefone(Telefone telefone){
		if(getListaTelefones()==null){
			setListaTelefones(new HashSet<Telefone>());
		}else if(!getListaTelefones().isEmpty()){
			return getListaTelefones().remove(telefone);
		}
		return false;
	}

	/**
	 * Method hashCode.
	
	 * @return int */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	
	 * @return boolean */
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
	
	

}