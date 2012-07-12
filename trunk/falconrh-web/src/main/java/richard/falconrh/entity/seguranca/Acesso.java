package richard.falconrh.entity.seguranca;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Usuario;

@Entity
@Table(name = "ACESSOS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_ACESSOS") })
public class Acesso extends Parent {
	private static final long serialVersionUID = 2615676672040034879L;

	private Usuario usuario;
	private String ipUsuario;
	private String ipServidorAplicacao;
	private Date dataLogon;
	private Date dataLogoff;
	private String idSessao;

	public Acesso() {
	}

	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	public Usuario getUsuario() {
		return usuario;
	}

	@Column(name = "IP_USUARIO", length = 39, nullable = false)
	public String getIpUsuario() {
		return ipUsuario;
	}

	@Column(name = "IP_SERV_APLI", length = 39, nullable = false)
	public String getIpServidorAplicacao() {
		return ipServidorAplicacao;
	}

	@Column(nullable = true, name = "DATA_LOGON")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataLogon() {
		return dataLogon;
	}

	@Column(nullable = true, name = "DATA_LOGOFF")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataLogoff() {
		return dataLogoff;
	}

	@Column(name = "ID_SESSAO", length = 64, nullable = true)
	public String getIdSessao() {
		return idSessao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDataLogoff(Date dataLogoff) {
		this.dataLogoff = dataLogoff;
	}

	public void setDataLogon(Date dataLogon) {
		this.dataLogon = dataLogon;
	}

	public void setIpServidorAplicacao(String ipServidorAplicacao) {
		this.ipServidorAplicacao = ipServidorAplicacao;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataLogoff == null) ? 0 : dataLogoff.hashCode());
		result = prime * result + ((dataLogon == null) ? 0 : dataLogon.hashCode());
		result = prime * result + ((idSessao == null) ? 0 : idSessao.hashCode());
		result = prime * result + ((ipServidorAplicacao == null) ? 0 : ipServidorAplicacao.hashCode());
		result = prime * result + ((ipUsuario == null) ? 0 : ipUsuario.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (!(obj instanceof Acesso)) {
			return false;
		}
		Acesso other = (Acesso) obj;
		if (dataLogoff == null) {
			if (other.dataLogoff != null) {
				return false;
			}
		} else if (!dataLogoff.equals(other.dataLogoff)) {
			return false;
		}
		if (dataLogon == null) {
			if (other.dataLogon != null) {
				return false;
			}
		} else if (!dataLogon.equals(other.dataLogon)) {
			return false;
		}
		if (idSessao == null) {
			if (other.idSessao != null) {
				return false;
			}
		} else if (!idSessao.equals(other.idSessao)) {
			return false;
		}
		if (ipServidorAplicacao == null) {
			if (other.ipServidorAplicacao != null) {
				return false;
			}
		} else if (!ipServidorAplicacao.equals(other.ipServidorAplicacao)) {
			return false;
		}
		if (ipUsuario == null) {
			if (other.ipUsuario != null) {
				return false;
			}
		} else if (!ipUsuario.equals(other.ipUsuario)) {
			return false;
		}
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

}
