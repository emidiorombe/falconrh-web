package richard.falconrh.entity.seguranca;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.modelo.enums.NivelAcesso;

@Entity
@Table(name = "PERFIS_ACESSOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_PERFIS_ACESSOS") })
public class PerfilAcesso extends Parent implements Group, Principal {
	private static final long serialVersionUID = -5625864565514095633L;

	private String nome;
	private NivelAcesso nivelAcesso;
	private Boolean ativo;
	private Set<PerfilaAcessoFuncionalidadeAcao> listaPerfisAcessosFuncionalidadesAcoes;

	public PerfilAcesso(){}

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
	
	@OneToMany(targetEntity=PerfilaAcessoFuncionalidadeAcao.class, fetch=FetchType.LAZY, mappedBy="perfilAcesso")
	public Set<PerfilaAcessoFuncionalidadeAcao> getListaPerfisAcessosFuncionalidadesAcoes() {
		return listaPerfisAcessosFuncionalidadesAcoes;
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
	
	public void setListaPerfisAcessosFuncionalidadesAcoes(Set<PerfilaAcessoFuncionalidadeAcao> listaPerfisAcessosFuncionalidadesAcoes) {
		this.listaPerfisAcessosFuncionalidadesAcoes = listaPerfisAcessosFuncionalidadesAcoes;
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

	@Override
	@Transient
	public String getName() {
		return this.getNome();
	}

	@Override
	public boolean addMember(Principal role) {
		if(role instanceof PerfilaAcessoFuncionalidadeAcao){
			return getListaPerfisAcessosFuncionalidadesAcoes().add((PerfilaAcessoFuncionalidadeAcao) role);
		}
		return false;
	}

	@Override
	public boolean removeMember(Principal role) {
		if(role instanceof PerfilaAcessoFuncionalidadeAcao){
			return getListaPerfisAcessosFuncionalidadesAcoes().remove(role);
		}
		return false;
	}

	@Override
	public boolean isMember(Principal membro) {
		return getListaPerfisAcessosFuncionalidadesAcoes().contains(membro);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<? extends Principal> members() {
		return (Enumeration<? extends Principal>) Collections.enumeration(getListaPerfisAcessosFuncionalidadesAcoes());
	}
}