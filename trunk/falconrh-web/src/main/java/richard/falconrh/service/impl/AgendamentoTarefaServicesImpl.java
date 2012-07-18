package richard.falconrh.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.scheduler.StatusAgendamento;
import richard.falconrh.scheduler.AgendamentoTarefa;
import richard.falconrh.service.AgendamentoTarefaServices;

@Singleton(name = "ejb/AgendamentoTarefaServices", mappedName = "AgendamentoTarefaServices")
@Startup
public class AgendamentoTarefaServicesImpl extends AbstractServicesImpl<AgendamentoTarefa> implements AgendamentoTarefaServices{
	private static final Logger LOGGER = Logger.getLogger(AgendamentoTarefaServicesImpl.class);

	@Resource
	private TimerService timerService;
	
	@Override
	public void agendarTarefa(AgendamentoTarefa agenda) throws ServicesException{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Iniciando agendamento de tarefa");
		}

		try{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"), new Locale("pt", "BR"));
			calendar.setTime(agenda.getDataAgendamento());
			
			TimerConfig timerConfig = new TimerConfig();
			timerConfig.setPersistent(true);
			timerConfig.setInfo(agenda);
			
			ScheduleExpression schedule = new ScheduleExpression();
			
			switch(agenda.getPeriodicidadeTarefa()){
			case UNICA:
				schedule.start(calendar.getTime());
				timerService.createSingleActionTimer(agenda.getDataHoraExecucao(), timerConfig);
				break;
			case HORARIA:
				timerService.createIntervalTimer(agenda.getDataHoraExecucao(), 1000*15, timerConfig);
				break;
			case DIARIA:
				schedule.hour(calendar.get(Calendar.HOUR));
				schedule.minute(calendar.get(Calendar.MINUTE));
				schedule.second(calendar.get(Calendar.SECOND));
				break;
			case SEMANAL:
				schedule.hour(calendar.get(Calendar.HOUR));
				schedule.minute(calendar.get(Calendar.MINUTE));
				schedule.second(calendar.get(Calendar.SECOND));
				schedule.dayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
				break;
			case MENSAL:
				schedule.hour(calendar.get(Calendar.HOUR));
				schedule.minute(calendar.get(Calendar.MINUTE));
				schedule.second(calendar.get(Calendar.SECOND));
				schedule.dayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
				break;
			case ANUAL:
				schedule.hour(calendar.get(Calendar.HOUR));
				schedule.minute(calendar.get(Calendar.MINUTE));
				schedule.second(calendar.get(Calendar.SECOND));
				schedule.month(calendar.get(Calendar.MONTH));
			}
		}catch(Exception e){
			LOGGER.error("Erro ao agendar tarefa");
			throw new ServicesException(e);
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Sucesso ao agendar tarefa");
		}
	}
	
	@Override
	public void cancelarTarefaAgendada(AgendamentoTarefa tarefaAgendada) throws ServicesException{
		for(Timer timer :  timerService.getTimers()){
			timer.cancel();
		}
	}
	
	@Override
	public void cancelarTarefaEmExecucao(AgendamentoTarefa tarefaAgendada) throws ServicesException{
		for(Timer timer :  timerService.getTimers()){
			timer.cancel();
		}
	}
	
	@Override
	public void excluirTarefaAgendada(AgendamentoTarefa tarefaAgendada) throws ServicesException {
		try{
			Collection<Timer> timers = timerService.getTimers();
			AgendamentoTarefa ta = null;
			for(Timer timer :  timers){
				ta = (AgendamentoTarefa) timer.getInfo();
				if(ta.getTarefa().getNome().equals(ta.getTarefa().getNome())){
					timer.cancel();
					break;
				}
			}
		} catch(Exception e){
			throw new ServicesException(e);
		}
	}
	
	@Override
	public Set<AgendamentoTarefa> obterListaTarefasAgendadas() throws ServicesException{
		Set<AgendamentoTarefa> lista = new TreeSet<AgendamentoTarefa>();
		Collection<Timer> timers = timerService.getTimers();
		AgendamentoTarefa agendamentoTarefa = null;
		for(Timer timer :  timers){
			agendamentoTarefa = (AgendamentoTarefa) timer.getInfo();
			lista.add(agendamentoTarefa);
		}
		return lista;		
	}
	
	@Timeout
	public void execute(Timer timer){
		try{
			Thread.sleep(60000L);
		}catch(Exception e){}
		AgendamentoTarefa tarefaAgendada = (AgendamentoTarefa) timer.getInfo();
		System.out.println(tarefaAgendada.getTarefa().getNome());
		System.out.println(tarefaAgendada.getTarefa().getDescricao());
		System.out.println(tarefaAgendada.getDataAgendamento());
		System.out.println(tarefaAgendada.getDataHoraExecucao());
		System.out.println(tarefaAgendada.getPeriodicidadeTarefa().getDescricao());
		tarefaAgendada.setStatusAgendamento(StatusAgendamento.EM_EXECUCAO);
		if(timer.isCalendarTimer()){
			System.out.println(timer.getSchedule().getStart());
			System.out.println(timer.getSchedule().getEnd());
		}
		
	}
	
}
