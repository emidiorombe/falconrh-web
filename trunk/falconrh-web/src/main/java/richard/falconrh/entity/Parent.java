package richard.falconrh.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe que será utilizada como base para criação de entidades do sistema
 * @author Richard Mendes Madureira
 * @version $Revision: 1.0 $
 */
@MappedSuperclass
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence")
public abstract class Parent implements Serializable{
	private static final long serialVersionUID = 2887056027368829436L;

	protected Long id;

	/**
	 * Método que retorna o identificador único do objeto
	 * @return Long a variável que representa o identificador único do objeto;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	public Long getId() {
		return id;
	}

	/**
	 * Método que seta o identificador único do objeto
	 * @param id parametro Long que representa o identificador único que será passado para o objeto.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Método que calcula o hash do objeto Parent
	 * @return int - número que representa o hash do objeto Parent
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Método utilizado para comparar duas instâncias da classe Parent
	 * @param obj - o Objeto que será comparado a classe
	 * @return boolean, sendo true para objetos iguais e false para objetos diferentes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Parent)) {
			return false;
		}
		Parent other = (Parent) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
