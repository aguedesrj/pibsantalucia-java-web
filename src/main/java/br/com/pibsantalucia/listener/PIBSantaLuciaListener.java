package br.com.pibsantalucia.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class PIBSantaLuciaListener implements ServletContextListener {

	private final Logger LOGGER = LoggerFactory.getLogger(PIBSantaLuciaListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("Iniciando Context!!!");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("Context destruído!!!");
	}
}
