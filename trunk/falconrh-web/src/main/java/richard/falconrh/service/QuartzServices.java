package richard.falconrh.service;

import javax.ejb.Remote;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.quartz.StatusQuartz;

@Remote
public interface QuartzServices {
//	public void agendarTarefa(AgendamentoTarefa agendamentoTarefa) throws ServicesException;
//	public void desagendarTarefa(AgendamentoTarefa agendamentoTarefa) throws ServicesException;
//	public void pausarTarefaEmExecucao(AgendamentoTarefa agendamentoTarefa) throws ServicesException;
//	public void interromperTarefaEmExecucao(AgendamentoTarefa agendamentoTarefa) throws ServicesException;
//	public void continuarTarefaPausada(AgendamentoTarefa agendamentoTarefa) throws ServicesException;
//	public Set<AgendamentoTarefa> obterListaTarefas() throws ServicesException;
	public StatusQuartz iniciarQuartz() throws ServicesException;
	public StatusQuartz desligarQuartz() throws ServicesException;
	public StatusQuartz hibernarQuartz() throws ServicesException;
	public StatusQuartz obterStatusQuartz() throws ServicesException;
}
