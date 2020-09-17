package br.com.pibsantalucia.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import br.com.pibsantalucia.jobs.JobPibSantaLucia;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.UsuarioMembroVO;

public class PibSantaLuciaInterceptor implements Interceptor {

	private final Logger LOGGER = LoggerFactory.getLogger(PibSantaLuciaInterceptor.class);
	private static final long serialVersionUID = -2249772441810187265L;
	
//	@Autowired
//	private MembroFacade membroFacade;

	public void destroy() {
		LOGGER.info("Destroying PibSantaLuciaInterceptor...");
	}

	public void init() {
		LOGGER.info("Initializing PibSantaLuciaInterceptor...");
		System.out.println("******************: "+System.getProperty("catalina.base"));

		JobPibSantaLucia jobPibSantaLucia = new JobPibSantaLucia();
		jobPibSantaLucia.agendarJobs();
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		if (invocation.getProxy().getActionName().equals("IniciaLogin") || 
				invocation.getProxy().getActionName().equals("EfetuaLogin")) {
			return invocation.invoke();
		}
		
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		UsuarioMembroVO usuarioMembroVO = (UsuarioMembroVO) session.get(Constantes.KEY_USUARIO_SESSION);
        if (usuarioMembroVO == null) {
        	return Action.LOGIN;
        } 
        else {
        	return invocation.invoke();
        }
	}
}
