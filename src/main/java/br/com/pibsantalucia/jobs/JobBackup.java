package br.com.pibsantalucia.jobs;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.SessionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pibsantalucia.util.Constantes;

public class JobBackup implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobBackup.class);
	
	@Autowired
    SessionFactory sessionFactory;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			LOGGER.info("Preparando a chamada do serviço de Backup.");
			
			HttpGet request = new HttpGet(Constantes.INSTANCE_AWS+"/Api/BackupRest/Backup");
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			httpClient.execute(request);
		} catch (Exception exception) {
			LOGGER.error("Error ao chamar o serviço de backup...", exception);
		}
	}
}
