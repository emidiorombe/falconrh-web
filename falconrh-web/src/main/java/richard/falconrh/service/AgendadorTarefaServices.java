package richard.falconrh.service;

import java.util.Set;

import javax.ejb.Remote;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.TarefaAgendada;

@Remote
public interface AgendadorTarefaServices{
	public void agendarTarefa(TarefaAgendada tarefaAgendada) throws ServicesException;
	public void cancelarTarefaAgendada(TarefaAgendada tarefaAgendada) throws ServicesException;
	public void excluirTarefaAgendada(TarefaAgendada tarefaAgendada) throws ServicesException;
	public void cancelarTarefaEmExecucao(TarefaAgendada tarefaAgendada) throws ServicesException;
	public Set<TarefaAgendada> obterListaTarefasAgendadas() throws ServicesException;
}
