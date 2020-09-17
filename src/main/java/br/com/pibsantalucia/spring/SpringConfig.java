package br.com.pibsantalucia.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.pibsantalucia.facade.PessoaFacade;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "br.com.pibsantalucia.spring")
public class SpringConfig extends WebMvcConfigurerAdapter {

	private final Logger LOGGER = LoggerFactory.getLogger(SpringConfig.class);

	@Autowired
	private PessoaFacade pessoaFacade;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		try {
			LOGGER.info("Iniciar o carregamento das fotos.");
			pessoaFacade.carregarFotosCongregados();
			LOGGER.info("Fim do carregamento das fotos.");
		} catch (Exception exception) {
			// não faz nada...
			LOGGER.error("Erro ao carregar os fotos dos congregados.", exception);
			System.out.println(exception);
		}
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
