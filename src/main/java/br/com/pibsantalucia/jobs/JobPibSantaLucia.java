package br.com.pibsantalucia.jobs;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobPibSantaLucia {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobPibSantaLucia.class);

	public void agendarJobs() {
		try {
			SchedulerFactory shFactory = new StdSchedulerFactory();
			Scheduler scheduler = shFactory.getScheduler();
			scheduler.start();
			
			// Backup
			JobDetail jobDetailBackup = JobBuilder.newJob(JobBackup.class).withIdentity("jobDetailBackup", "grupo01").build();
			Trigger triggerBackup = TriggerBuilder.newTrigger().withIdentity("validadorTRIGGER", "grupo01").withSchedule(CronScheduleBuilder.cronSchedule("0 00 03 ? * *")).build();
			
			// Envio de Push dos Aniversariantes do dia
			JobDetail jobSendPushAniversariantes = JobBuilder.newJob(JobSendPushAniversariantes.class).withIdentity("jobSendPushAniversariantes", "grupo02").build();
			Trigger triggerSendPushAniversariantes = TriggerBuilder.newTrigger().withIdentity("validadorTRIGGER", "grupo02").withSchedule(CronScheduleBuilder.cronSchedule("0 00 13 ? * *")).build();

			// Escala
			JobDetail jobEscala = JobBuilder.newJob(JobEscala.class).withIdentity("jobEscala", "grupo03").build();
			Trigger triggerEscala = TriggerBuilder.newTrigger().withIdentity("validadorTRIGGER", "grupo03").withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 JAN-DEC ? *")).build();
			
			scheduler.scheduleJob(jobDetailBackup, triggerBackup);
			scheduler.scheduleJob(jobSendPushAniversariantes, triggerSendPushAniversariantes);
			scheduler.scheduleJob(jobEscala, triggerEscala);
		} catch (Exception e) {
			LOGGER.error("ERROR ao fazer backup...", e);
		}
	}
}
