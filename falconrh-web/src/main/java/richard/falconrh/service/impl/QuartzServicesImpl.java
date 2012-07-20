package richard.falconrh.service.impl;

import java.util.Properties;

import javax.ejb.Singleton;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import richard.falconrh.exception.ServicesException;
import richard.falconrh.quartz.StatusQuartz;
import richard.falconrh.service.QuartzServices;

@Singleton(name="ejb/QuartzServices", mappedName="QuartzServices")
public class QuartzServicesImpl implements QuartzServices {
	private SchedulerFactory schedulerFactory;
	
	public SchedulerFactory getSchedulerFactory() throws SchedulerException{
		if(schedulerFactory==null){
			schedulerFactory = new StdSchedulerFactory(getQuartzPropertiesRAM());
		}
		return schedulerFactory;
	}
	
	public Scheduler getScheduler() throws SchedulerException{
		return getSchedulerFactory().getScheduler();
	}
	
	private Properties getQuartzPropertiesRAM(){
		Properties properties = new Properties();
		properties.put("org.quartz.scheduler.instanceName", "FalconRHScheduler");
		properties.put("org.quartz.threadPool.threadCount", "3");
		properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
		return properties;
	}
	
	protected Properties getQuartzProperties() {
		Properties properties = new Properties();
		properties.put("org.quartz.scheduler.instanceName", "FalconRHScheduler");
		properties.put("org.quartz.threadPool.threadCount", "3");
		properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcstore.JobStoreCMT");
		properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
		properties.put("org.quartz.jobStore.dataSource", "FalconRHDS");
		
		
		properties.put("org.quartz.dataSource.FalconRHDS.driver", "falconrh00");
		properties.put("org.quartz.dataSource.FalconRHDS.URL", "jdbc:postgresql://localhost:5432/falconrhdb");
		properties.put("org.quartz.dataSource.FalconRHDS.user", "falconrh");
		properties.put("org.quartz.dataSource.FalconRHDS.password", "falconrh00");
		properties.put("org.quartz.dataSource.FalconRHDS.maxConnections", "20");
		properties.put("org.quartz.dataSource.FalconRHDS.jndiURL", "java:jboss/datasources/FalconRHDS");
		
//		properties.put("org.quartz.dataSource.myOtherDS.jndiURL", "jdbc/myDataSource");
//		properties.put("org.quartz.dataSource.myOtherDS.java.naming.factory.initial", "com.evermind.server.rmi.RMIInitialContextFactory");
//		properties.put("org.quartz.dataSource.myOtherDS.java.naming.provider.url", "ormi://localhost");
//		properties.put("org.quartz.dataSource.myOtherDS.java.naming.security.principal", "admin");
//		properties.put("org.quartz.dataSource.myOtherDS.java.naming.security.credentials", "123");
		return properties;
	}

	@Override
	public StatusQuartz iniciarQuartz() throws ServicesException {
		try {
			getScheduler().start();
			return obterStatusQuartz();
		} catch (SchedulerException e) {
			throw new ServicesException(e);
		}
	}

	@Override
	public StatusQuartz desligarQuartz() throws ServicesException {
		try {
			if(getScheduler().getCurrentlyExecutingJobs().isEmpty()){
				getScheduler().shutdown();
			}else{
				throw new ServicesException("Impossível Desligar o quartz porque há tarefas em execução");
			}
			return obterStatusQuartz();
		} catch (SchedulerException e) {
			throw new ServicesException(e);
		}
	}

	@Override
	public StatusQuartz hibernarQuartz() throws ServicesException {
		try {
			getScheduler().standby();
			return obterStatusQuartz();
		} catch (SchedulerException e) {
			throw new ServicesException(e);
		}
	}

	@Override
	public StatusQuartz obterStatusQuartz() throws ServicesException {
			try{
				if(getScheduler()!=null){
					if(getScheduler().isStarted()) return StatusQuartz.INICIADO;
					if(getScheduler().isShutdown()) return StatusQuartz.DESLIGADO;
					if(getScheduler().isInStandbyMode()) return StatusQuartz.HIBERNADO;
				}
			}catch(SchedulerException e){
				throw new ServicesException(e);
			}
		return null;
	}

}
