package richard.falconrh.entity.seguranca;

import java.security.Principal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "FUNCIONALIDADES")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_FUNCIONALIDADES") })
public class Funcionalidade extends Parent implements Principal{
	private static final long serialVersionUID = 5242868664531412945L;
	
	private String nome;
	private String link;
	private Boolean ativa;
	private List<Acao> listaAcoes;
	private Funcionalidade funcionalidadePai;
	
	public Funcionalidade(){}

	@Column(name="NOME", nullable=false)
	public String getNome() {
		return nome;
	}

	@Column(name="LINK", nullable=false)
	public String getLink() {
		return link;
	}
	@Column(name="ATIVA", nullable=false)
	public Boolean getAtiva() {
		return ativa;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="FUNCIONALIDADE_ACOES", joinColumns = @JoinColumn(name="ID_FUNCIONALIDADE", referencedColumnName="ID"), inverseJoinColumns= @JoinColumn(name="ID_ACAO", referencedColumnName="ID"))
	public List<Acao> getListaAcoes() {
		return listaAcoes;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_FUNCIONALIDADE_PAI")
	@ForeignKey(name="fk_funcionalidadePai")
	public Funcionalidade getFuncionalidadePai(){
		return funcionalidadePai;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public void setListaAcoes(List<Acao> listaAcoes) {
		this.listaAcoes = listaAcoes;
	}
	
	public void setFuncionalidadePai(Funcionalidade funcionalidadePai){
		this.funcionalidadePai = funcionalidadePai;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Funcionalidade)) {
			return false;
		}
		Funcionalidade other = (Funcionalidade) obj;
		if (link == null) {
			if (other.link != null) {
				return false;
			}
		} else if (!link.equals(other.link)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	@Transient
	public String getName() {
		return getNome();
	}
}
