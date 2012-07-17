package richard.falconrh.scheduler;

import java.util.Date;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Usuario;

public class TarefaAgendada extends Parent implements Comparable<TarefaAgendada>{
	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa;
	private Date dataAgendamento;
	private Date dataHoraExecucao;
	private Boolean ativa;
	private Usuario responsavelAgendamento;
	private PeriodicidadeTarefa periodicidadeTarefa;
	private StatusAgendamento statusAgendamento;
	
	public TarefaAgendada(){}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public Date getDataHoraExecucao() {
		return dataHoraExecucao;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public Usuario getResponsavelAgendamento() {
		return responsavelAgendamento;
	}
	
	public PeriodicidadeTarefa getPeriodicidadeTarefa() {
		return periodicidadeTarefa;
	}
	
	public StatusAgendamento getStatusAgendamento(){
		return statusAgendamento;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public void setDataHoraExecucao(Date dataHoraExecucao) {
		this.dataHoraExecucao = dataHoraExecucao;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public void setResponsavelAgendamento(Usuario responsavelAgendamento) {
		this.responsavelAgendamento = responsavelAgendamento;
	}
	
	public void setPeriodicidadeTarefa(PeriodicidadeTarefa periodicidadeTarefa) {
		this.periodicidadeTarefa = periodicidadeTarefa;
	}
	
	public void setStatusAgendamento(StatusAgendamento statusAgendamento){
		this.statusAgendamento = statusAgendamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataHoraExecucao == null) ? 0 : dataHoraExecucao.hashCode());
		result = prime * result + ((tarefa == null) ? 0 : tarefa.hashCode());
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
		TarefaAgendada other = (TarefaAgendada) obj;
		if (dataHoraExecucao == null) {
			if (other.dataHoraExecucao != null)
				return false;
		} else if (!dataHoraExecucao.equals(other.dataHoraExecucao))
			return false;
		if (tarefa == null) {
			if (other.tarefa != null)
				return false;
		} else if (!tarefa.equals(other.tarefa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TarefaAgendada [tarefa=" + tarefa.getNome() + ", dataHoraExecucao="+ dataHoraExecucao + "]";
	}
	
	@Override
	public int compareTo(TarefaAgendada a) {
		if(a!=null && a.getTarefa()!=null){
			if(this.getTarefa()!=null){
				return this.getTarefa().getNome().toUpperCase().compareTo(a.getTarefa().getNome().toUpperCase());
			}
		}
		return 0;
	}
}
