package richard.falconrh.entity.empresa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.localizacao.Endereco;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "EMPRESAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_EMPRESAS") })
public class Empresa extends Parent {
	private static final long serialVersionUID = 8726335235365615532L;

	private String nomeEmpresarial;
	private String titulo;
	private Long cnpj;
	private Endereco endereco;

	public Empresa() {
	}

	@Column(nullable=false)
	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	@Column(nullable=false)
	public String getTitulo() {
		return titulo;
	}

	@Column(nullable=false)
	public Long getCnpj() {
		return cnpj;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ENDERECO", nullable=true)
	public Endereco getEndereco() {
		return endereco;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((nomeEmpresarial == null) ? 0 : nomeEmpresarial.hashCode());
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
		if (!(obj instanceof Empresa)) {
			return false;
		}
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null) {
				return false;
			}
		} else if (!cnpj.equals(other.cnpj)) {
			return false;
		}
		if (nomeEmpresarial == null) {
			if (other.nomeEmpresarial != null) {
				return false;
			}
		} else if (!nomeEmpresarial.equals(other.nomeEmpresarial)) {
			return false;
		}
		return true;
	}

}
