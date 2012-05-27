package richard.falconrh.entity.seguranca;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
public class Funcionalidade extends Parent{
	private static final long serialVersionUID = 5242868664531412945L;
	
	private String nome;
	private String link;
	private Boolean ativa;
	private List<Acao> listaAcoes;
	
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

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="FUNCIONALIDADE_ACOES", joinColumns = @JoinColumn(name="ID_FUNCIONALIDADE", referencedColumnName="ID"), inverseJoinColumns= @JoinColumn(name="ID_ACAO", referencedColumnName="ID"))
	public List<Acao> getListaAcoes() {
		return listaAcoes;
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
	
	
}
