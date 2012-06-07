package richard.falconrh.entity.pessoa;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import richard.falconrh.entity.seguranca.PerfilAcesso;
import richard.falconrh.util.FalconRHUtils;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "USUARIOS")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName="id")
@ForeignKey(name="fk_pessoa")
public class Usuario extends Pessoa implements Principal{
	private static final long serialVersionUID = 3007913276013523787L;

	private String login;
	private String senha;
	private Boolean ativo;
	private PerfilAcesso perfilAcesso;

	/**
	 * Method getLogin.
	 * @return String
	 */
	@Column(nullable=false, length=50, unique=true)
	public String getLogin() {
		return login;
	}

	/**
	 * Method getSenha.
	 * @return String
	 */
	@Column(nullable=false, length=44)
	public String getSenha() {
		return senha;
	}
	
	@Column(nullable=false)
	public Boolean getAtivo(){
		return ativo;
	}
	
	@ManyToOne
	@JoinColumn(name="ID_PERFIL_ACESSO", nullable=true)
	@ForeignKey(name="fk_perfilAcesso")
	public PerfilAcesso getPerfilAcesso(){
		return perfilAcesso;
	}

	/**
	 * Method setLogin.
	 * @param login String
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Method setSenha.
	 * @param senha String
	 */
	public void setSenha(String senha) {
		this.senha = FalconRHUtils.getHashSenha(senha);
	}
	
	public void setAtivo(Boolean ativo){
		this.ativo = ativo;
	}
	
	public void setPerfilAcesso(PerfilAcesso perfilAcesso){
		this.perfilAcesso = perfilAcesso;
	}

	/**
	 * Method getName.
	 * @return String * @see java.security.Principal#getName()
	 */
	@Transient
	public String getName() {
		//TODO VERIFICAR POSSIBILIDADE DE RETORNAR O NOME
		return getLogin();
	}

	/**
	 * Method hashCode.
	 * @return int * @see java.security.Principal#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	 * @return boolean * @see java.security.Principal#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		return true;
	}
}