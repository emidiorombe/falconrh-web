package richard.falconrh.scheduler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import richard.falconrh.entity.Parent;

@Entity
@Table(name = "TAREFAS")
@GenericGenerator(name = "SEQ_GEN", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TAREFAS") })
public class Tarefa extends Parent implements Comparable<Tarefa>{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	
	public Tarefa(){
	}

	@Column(name="NOME", length=60, nullable=false)
	public String getNome() {
		return nome;
	}
	
	@Column(name="DESCRICAO", length=255, nullable=true)
	public String getDescricao() {
		return descricao;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equalsIgnoreCase(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tarefa [nome=" + nome + "]";
	}

	@Override
	public int compareTo(Tarefa outraTarefa) {
		if(outraTarefa!=null){
			if(StringUtils.isNotBlank(outraTarefa.getNome())){
				return this.getNome().toUpperCase().compareTo(outraTarefa.getNome().toUpperCase());
			}
		}
		return 0;
	}
}
