package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.AgendamentoTarefa;

@Remote
public interface AgendamentoTarefaServices{
	public void agendarTarefa(AgendamentoTarefa tarefaAgendada) throws ServicesException;
	public void cancelarTarefaAgendada(AgendamentoTarefa tarefaAgendada) throws ServicesException;
	public void excluirTarefaAgendada(AgendamentoTarefa tarefaAgendada) throws ServicesException;
	public void cancelarTarefaEmExecucao(AgendamentoTarefa tarefaAgendada) throws ServicesException;
	public Set<AgendamentoTarefa> obterListaTarefasAgendadas() throws ServicesException;
}
