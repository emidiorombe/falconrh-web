package richard.falconrh.entity.inss;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ALIQUOTAS_INSS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_ALIQUOTAS_INSS") })
public class AliquotaINSS extends Parent {
	private static final long serialVersionUID = 3241457260773333391L;

	private TabelaINSS tabelaINSS;
	private String descricao;
	private Double aliquota;
	private Double valorInicial;
	private Double valorFinal;

	public AliquotaINSS(){
	}
	
	/**
	 * Constructor for AliquotaINSS.
	 * @param id Long
	 */
	public AliquotaINSS(Long id) {
		this.id = id;
	}

	/**
	 * Method getTabelaINSS.
	
	 * @return TabelaINSS */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TABELA_INSS", nullable=false)
	public TabelaINSS getTabelaINSS() {
		return tabelaINSS;
	}

	/**
	 * Method getDescricao.
	
	 * @return String */
	@Column(length=100, nullable=false)
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Method getAliquota.
	
	 * @return Double */
	@Column(nullable=false)
	public Double getAliquota() {
		return aliquota;
	}

	/**
	 * Method getValorInicial.
	
	 * @return Double */
	@Column(nullable=false)
	public Double getValorInicial() {
		return valorInicial;
	}

	/**
	 * Method getValorFinal.
	
	 * @return Double */
	@Column(nullable=true)
	public Double getValorFinal() {
		return valorFinal;
	}

	/**
	 * Method setTabelaINSS.
	 * @param tabelaINSS TabelaINSS
	 */
	public void setTabelaINSS(TabelaINSS tabelaINSS) {
		this.tabelaINSS = tabelaINSS;
	}

	/**
	 * Method setDescricao.
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Method setAliquota.
	 * @param aliquota Double
	 */
	public void setAliquota(Double aliquota) {
		this.aliquota = aliquota;
	}

	/**
	 * Method setValorInicial.
	 * @param valorInicial Double
	 */
	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	/**
	 * Method setValorFinal.
	 * @param valorFinal Double
	 */
	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}
}
