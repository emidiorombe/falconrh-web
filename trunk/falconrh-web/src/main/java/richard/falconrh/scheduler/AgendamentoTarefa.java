package richard.falconrh.scheduler;

import java.util.Date;

import richard.falconrh.entity.Parent;
import richard.falconrh.entity.pessoa.Usuario;

public class AgendamentoTarefa extends Parent implements Comparable<AgendamentoTarefa>{
	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa;
	private Date dataAgendamento;
	private Date dataHoraExecucao;
	private Usuario responsavelAgendamento;
	private PeriodicidadeTarefa periodicidadeTarefa;
	private StatusAgendamento statusAgendamento;
	private String ipResponsavelAgendamento;
	
	public AgendamentoTarefa(){}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public Date getDataHoraExecucao() {
		return dataHoraExecucao;
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
	
	public String getIpResponsavelAgendamento(){
		return ipResponsavelAgendamento;
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

	public void setResponsavelAgendamento(Usuario responsavelAgendamento) {
		this.responsavelAgendamento = responsavelAgendamento;
	}
	
	public void setPeriodicidadeTarefa(PeriodicidadeTarefa periodicidadeTarefa) {
		this.periodicidadeTarefa = periodicidadeTarefa;
	}
	
	public void setStatusAgendamento(StatusAgendamento statusAgendamento){
		this.statusAgendamento = statusAgendamento;
	}
	
	public void setIpResponsavelAgendamento(String ipResponsavelAgendamento){
		this.ipResponsavelAgendamento = ipResponsavelAgendamento;
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
		AgendamentoTarefa other = (AgendamentoTarefa) obj;
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
	public int compareTo(AgendamentoTarefa a) {
		if(a!=null && a.getTarefa()!=null){
			if(this.getTarefa()!=null){
				return this.getTarefa().getNome().toUpperCase().compareTo(a.getTarefa().getNome().toUpperCase());
			}
		}
		return 0;
	}
}
