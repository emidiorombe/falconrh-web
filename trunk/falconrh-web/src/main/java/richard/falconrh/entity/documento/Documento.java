package richard.falconrh.entity.documento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoDocumento;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name="DOCUMENTOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_DOCUMENTOS") })
public class Documento extends Parent {
	private static final long serialVersionUID = 1274918261316232739L;

	private Date dataEmissao;
	private String numero;
	private TipoDocumento tipoDocumento;
	private Pessoa pessoa;
	
	public Documento(){
	}

	/**
	 * Constructor for Documento.
	 * @param tipoDocumento TipoDocumento
	 * @param numero String
	 */
	public Documento(TipoDocumento tipoDocumento, String numero){
		this.tipoDocumento = tipoDocumento;
		this.numero = numero;
	}
	
	/**
	 * Method getDataEmissao.
	
	 * @return Date */
	@Temporal(value=TemporalType.DATE)
	public Date getDataEmissao() {
		return dataEmissao;
	}
	
	/**
	 * Method getNumero.
	
	 * @return String */
	@Column(length=15, nullable=false)
	public String getNumero() {
		return numero;
	}

	/**
	 * Method getTipoDocumento.
	
	 * @return TipoDocumento */
	@Enumerated(value=EnumType.STRING)
	@Column(length=30, nullable=false)
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Method getPessoa.
	
	 * @return Pessoa */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PESSOA", nullable=false)
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	/**
	 * Method setDataEmissao.
	 * @param dataEmissao Date
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	/**
	 * Method setNumero.
	 * @param numero String
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Method setTipoDocumento.
	 * @param tipoDocumento TipoDocumento
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Method setPessoa.
	 * @param pessoa Pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
