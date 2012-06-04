package richard.falconrh.entity.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.NivelAcesso;

@Entity
@Table(name = "PERFIS_ACESSOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_PERFIS_ACESSOS") })
public class PerfilAcesso extends Parent {
	private static final long serialVersionUID = -5625864565514095633L;

	private String nome;
	private NivelAcesso nivelAcesso;
	private Boolean ativo;

	@Column(length = 255, nullable = false)
	public String getNome() {
		return nome;
	}

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	@Column(nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!(obj instanceof PerfilAcesso)) {
			return false;
		}
		PerfilAcesso other = (PerfilAcesso) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

}
