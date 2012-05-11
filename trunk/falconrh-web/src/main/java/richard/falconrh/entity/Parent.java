package richard.falconrh.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author richard
 * @version $Revision: 1.0 $
 */
@MappedSuperclass
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence")
public abstract class Parent implements Serializable{
	private static final long serialVersionUID = 2887056027368829436L;

	public static final String nmcp_id = "id";

	protected Long id;

	/**
	 * Method getId.
	
	 * @return Long */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	public Long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method hashCode.
	
	 * @return int */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * @param obj Object
	
	 * @return boolean */
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
