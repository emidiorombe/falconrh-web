package richard.falconrh.entity.ir;

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
@Table(name = "ALIQUOTAS_IR")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_ALIQUOTAS_IR") })
public class AliquotaImpostoRenda extends Parent {
	private static final long serialVersionUID = 3241457260773333391L;

	private TabelaImpostoRenda tabelaImpostoRenda;
	private String descricao;
	private Double aliquota;
	private Double valorInicial;
	private Double valorFinal;
	private Double valorParcelaADeduzir;

	public AliquotaImpostoRenda(){
	}
	
	/**
	 * Constructor for AliquotaImpostoRenda.
	 * @param id Long
	 */
	public AliquotaImpostoRenda(Long id) {
		this.id = id;
	}

	/**
	 * Method getTabelaImpostoRenda.
	
	 * @return TabelaImpostoRenda */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TABELA_IR")
	public TabelaImpostoRenda getTabelaImpostoRenda() {
		return tabelaImpostoRenda;
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
	 * Method getValorParcelaADeduzir.
	
	 * @return Double */
	@Column(nullable=false)
	public Double getValorParcelaADeduzir() {
		return valorParcelaADeduzir;
	}

	/**
	 * Method setTabelaImpostoRenda.
	 * @param tabelaImpostoRenda TabelaImpostoRenda
	 */
	public void setTabelaImpostoRenda(TabelaImpostoRenda tabelaImpostoRenda) {
		this.tabelaImpostoRenda = tabelaImpostoRenda;
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

	/**
	 * Method setValorParcelaADeduzir.
	 * @param valorParcelaADeduzir Double
	 */
	public void setValorParcelaADeduzir(Double valorParcelaADeduzir) {
		this.valorParcelaADeduzir = valorParcelaADeduzir;
	}

	/**
	 * Method hashCode.
	
	 * @return int */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((aliquota == null) ? 0 : aliquota.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((valorFinal == null) ? 0 : valorFinal.hashCode());
		result = prime * result
				+ ((valorInicial == null) ? 0 : valorInicial.hashCode());
		result = prime
				* result
				+ ((valorParcelaADeduzir == null) ? 0 : valorParcelaADeduzir
						.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	
	 * @return boolean */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof AliquotaImpostoRenda))
			return false;
		AliquotaImpostoRenda other = (AliquotaImpostoRenda) obj;
		if (aliquota == null) {
			if (other.aliquota != null)
				return false;
		} else if (!aliquota.equals(other.aliquota))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (valorFinal == null) {
			if (other.valorFinal != null)
				return false;
		} else if (!valorFinal.equals(other.valorFinal))
			return false;
		if (valorInicial == null) {
			if (other.valorInicial != null)
				return false;
		} else if (!valorInicial.equals(other.valorInicial))
			return false;
		if (valorParcelaADeduzir == null) {
			if (other.valorParcelaADeduzir != null)
				return false;
		} else if (!valorParcelaADeduzir.equals(other.valorParcelaADeduzir))
			return false;
		return true;
	}

	
}