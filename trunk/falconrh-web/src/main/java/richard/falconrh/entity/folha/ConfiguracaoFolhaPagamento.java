package richard.falconrh.entity.folha;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.banco.Agencia;

@Entity
@Table(name = "CONFIG_FOLHA_PAGAMENTO")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_CONFIG_FOLHA_PAGAMENTO") })
public class ConfiguracaoFolhaPagamento extends Parent {
	private static final long serialVersionUID = -2875701515644057854L;

	private String descricao;
	private Date dataInicioVigencia;
	private Date dataTerminoVigencia;

	private Agencia agenciaFGTS;
	private Integer diaPagamentoFolhaNormal;
	private Integer diaPagamentoFolha13;
	private Integer diaPagamentoFolhaAdiantamentoFerias;
	private Integer diaPagamentoFolhaAdiantamento13;
	private Integer mesCobrancaContribuicaoSindical;
	private Integer mesLimitePagamentoFolhaAdiantamento13;
	private Integer mesPagamentoFolhaAdiantamento13;

	private Integer diaPagamentoFolhaNormalManual;
	private Integer diaPagamentoFolhaAdiantamentoFeriasManual;
	private Boolean forcarDiaPagamentoFolhaNormalManual;
	private Boolean forcarDiaPagamentoFolhaAdiantamentoFeriasManual;

	@Column(nullable=false, length=255)
	public String getDescricao() {
		return descricao;
	}

	@Column(nullable=false)
	@Temporal(value=TemporalType.DATE)
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}
	@Column(nullable=true)
	@Temporal(value=TemporalType.DATE)
	public Date getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}

	@OneToOne
	@JoinColumn(name="ID_AGENCIA_FGTS", nullable=false)
	public Agencia getAgenciaFGTS() {
		return agenciaFGTS;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolhaNormal() {
		return diaPagamentoFolhaNormal;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolha13() {
		return diaPagamentoFolha13;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolhaAdiantamentoFerias() {
		return diaPagamentoFolhaAdiantamentoFerias;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolhaAdiantamento13() {
		return diaPagamentoFolhaAdiantamento13;
	}

	@Column(nullable=false, length=2)
	public Integer getMesCobrancaContribuicaoSindical() {
		return mesCobrancaContribuicaoSindical;
	}

	@Column(nullable=false, length=2)
	public Integer getMesLimitePagamentoFolhaAdiantamento13() {
		return mesLimitePagamentoFolhaAdiantamento13;
	}

	@Column(nullable=false, length=2)
	public Integer getMesPagamentoFolhaAdiantamento13() {
		return mesPagamentoFolhaAdiantamento13;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolhaNormalManual() {
		return diaPagamentoFolhaNormalManual;
	}

	@Column(nullable=false, length=2)
	public Integer getDiaPagamentoFolhaAdiantamentoFeriasManual() {
		return diaPagamentoFolhaAdiantamentoFeriasManual;
	}

	@Column(nullable=false)
	public Boolean getForcarDiaPagamentoFolhaNormalManual() {
		return forcarDiaPagamentoFolhaNormalManual;
	}

	@Column(nullable=false)
	public Boolean getForcarDiaPagamentoFolhaAdiantamentoFeriasManual() {
		return forcarDiaPagamentoFolhaAdiantamentoFeriasManual;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public void setDataTerminoVigencia(Date dataTerminoVigencia) {
		this.dataTerminoVigencia = dataTerminoVigencia;
	}

	public void setAgenciaFGTS(Agencia agenciaFGTS) {
		this.agenciaFGTS = agenciaFGTS;
	}

	public void setDiaPagamentoFolhaNormal(Integer diaPagamentoFolhaNormal) {
		this.diaPagamentoFolhaNormal = diaPagamentoFolhaNormal;
	}

	public void setDiaPagamentoFolha13(Integer diaPagamentoFolha13) {
		this.diaPagamentoFolha13 = diaPagamentoFolha13;
	}

	public void setDiaPagamentoFolhaAdiantamentoFerias(Integer diaPagamentoFolhaAdiantamentoFerias) {
		this.diaPagamentoFolhaAdiantamentoFerias = diaPagamentoFolhaAdiantamentoFerias;
	}

	public void setDiaPagamentoFolhaAdiantamento13(Integer diaPagamentoFolhaAdiantamento13) {
		this.diaPagamentoFolhaAdiantamento13 = diaPagamentoFolhaAdiantamento13;
	}

	public void setMesCobrancaContribuicaoSindical(Integer mesCobrancaContribuicaoSindical) {
		this.mesCobrancaContribuicaoSindical = mesCobrancaContribuicaoSindical;
	}

	public void setMesLimitePagamentoFolhaAdiantamento13(Integer mesLimitePagamentoFolhaAdiantamento13) {
		this.mesLimitePagamentoFolhaAdiantamento13 = mesLimitePagamentoFolhaAdiantamento13;
	}

	public void setMesPagamentoFolhaAdiantamento13(Integer mesPagamentoFolhaAdiantamento13) {
		this.mesPagamentoFolhaAdiantamento13 = mesPagamentoFolhaAdiantamento13;
	}

	public void setDiaPagamentoFolhaNormalManual(Integer diaPagamentoFolhaNormalManual) {
		this.diaPagamentoFolhaNormalManual = diaPagamentoFolhaNormalManual;
	}

	public void setDiaPagamentoFolhaAdiantamentoFeriasManual(Integer diaPagamentoFolhaAdiantamentoFeriasManual) {
		this.diaPagamentoFolhaAdiantamentoFeriasManual = diaPagamentoFolhaAdiantamentoFeriasManual;
	}

	public void setForcarDiaPagamentoFolhaNormalManual(Boolean forcarDiaPagamentoFolhaNormalManual) {
		this.forcarDiaPagamentoFolhaNormalManual = forcarDiaPagamentoFolhaNormalManual;
	}

	public void setForcarDiaPagamentoFolhaAdiantamentoFeriasManual(Boolean forcarDiaPagamentoFolhaAdiantamentoFeriasManual) {
		this.forcarDiaPagamentoFolhaAdiantamentoFeriasManual = forcarDiaPagamentoFolhaAdiantamentoFeriasManual;
	}

}
