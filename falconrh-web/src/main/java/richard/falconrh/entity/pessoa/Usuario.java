package richard.falconrh.entity.pessoa;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import richard.falconrh.util.FalconRHUtils;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "USUARIOS")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName="id")
public class Usuario extends Pessoa implements Principal{
	private static final long serialVersionUID = 3007913276013523787L;

	private String login;
	private String senha;

	/**
	 * Method getLogin.
	
	 * @return String */
	@Column(nullable=false, length=50)
	public String getLogin() {
		return login;
	}

	/**
	 * Method getSenha.
	
	 * @return String */
	@Column(nullable=false, length=44)
	public String getSenha() {
		return senha;
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

	/**
	 * Method getName.
	
	
	 * @return String * @see java.security.Principal#getName() */
	@Transient
	public String getName() {
		//TODO VERIFICAR POSSIBILIDADE DE RETORNAR O NOME
		return getLogin();
	}

	/**
	 * Method hashCode.
	
	
	 * @return int * @see java.security.Principal#hashCode() */
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
	
	
	 * @return boolean * @see java.security.Principal#equals(Object) */
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
