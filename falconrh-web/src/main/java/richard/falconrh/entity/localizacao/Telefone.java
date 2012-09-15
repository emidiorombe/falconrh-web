package richard.falconrh.entity.localizacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Pessoa;
import richard.falconrh.modelo.enums.TipoTelefone;

/**
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TELEFONES")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TELEFONES") })
public class Telefone extends Parent{
	private static final long serialVersionUID = -1779180061373431255L;

	private TipoTelefone tipoTelefone;
	private Integer ddd;
	private Long numero;	
	private List<Pessoa> listaPessoas;

	public Telefone() {
	}

	/**
	 * Constructor for Telefone.
	 * @param tipoTelefone TipoTelefone
	 * @param ddd Integer
	 * @param numero Long
	 */
	public Telefone(TipoTelefone tipoTelefone, Integer ddd, Long numero) {
		this.tipoTelefone = tipoTelefone;
		this.ddd = ddd;
		this.numero = numero;
	}

	/**
	 * Method getTipoTelefone.
	 * @return TipoTelefone */
	@Enumerated(value = EnumType.STRING)
	@Column(length = 30, nullable = false)
	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	/**
	 * Method getDdd.
	 * @return Integer */
	@Column(length = 3, nullable = false)
	public Integer getDdd() {
		return ddd;
	}

	/**
	 * Method getNumero.
	 * @return Long */
	@Column(length = 8, nullable = false)
	public Long getNumero() {
		return numero;
	}
	
	/**
	 * Method getListaPessoas.
	 * @return List<Pessoa> */
	@ManyToMany(targetEntity=Pessoa.class, mappedBy="listaTelefones", cascade=CascadeType.ALL)
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}
	
	/**
	 * Method setTipoTelefone.
	 * @param tipoTelefone TipoTelefone
	 */
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	/**
	 * Method setDdd.
	 * @param ddd Integer
	 */
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	/**
	 * Method setNumero.
	 * @param numero Long
	 */
	public void setNumero(Long numero) {
		this.numero = numero;
	}

	/**
	 * Method setListaPessoas.
	 * @param listaPessoas List<Pessoa>
	 */
	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	@Transient
	public String getNumeroFormatado(){
		if(getDdd()!=null && getNumero()!=null){
			String n = String.valueOf(getNumero());
			return "(" + getDdd() + ") " + n.substring(0,4)+"-"+n.substring(4,8);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof Telefone)) {
			return false;
		}
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null) {
				return false;
			}
		} else if (!ddd.equals(other.ddd)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}

	
}