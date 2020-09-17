package br.com.pibsantalucia.api;

import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.vo.SendPushNotificationVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@RestController
@RequestMapping("/Api/AniversarianteRest")
public class AniversariantesRest {

	private final Logger LOGGER = LoggerFactory.getLogger(AniversariantesRest.class);
	
	@Autowired
	private PessoaFacade pessoaFacade;
	
	@RequestMapping(path = "/SendPush", method = RequestMethod.GET)
	public void sendPush() {
		
		try {
			LOGGER.info("Preparando o envio do push notification...");
			List<SendPushNotificationVO> listaMembroSendPush = pessoaFacade.listMembrosSendPush();
			if (listaMembroSendPush.size() > 0) {
				List<ViewMembroVO> listAniversariantes = pessoaFacade.listAniversariantesHoje();
				if (listAniversariantes.size() > 0) {
					for (SendPushNotificationVO sendPushNotification: listaMembroSendPush) {
						for (ViewMembroVO viewMembroVO: listAniversariantes) {
							
							sendPushNotification(viewMembroVO.getPesCodigo(),
									sendPushNotification.getDemTokenFirebase(),
									viewMembroVO.getPesNome(), 
									viewMembroVO.getPesSexo(),
									sendPushNotification.getPesCodigo() == viewMembroVO.getPesCodigo());
						}
					}
				} else {
					LOGGER.info("Nenhum aniversariante no dia de hoje.");
				}
			} else {
				LOGGER.info("Nenhum dispositivo encontrado para enviar push.");
			}
		} catch (Exception exception) {
			LOGGER.error("Erro ao preparar o envio do push notification.", exception);
		}
	}
	
	private void sendPushNotification(long pesCodigo, String token, String nome, String sexo, boolean meuAniversario) {
		try {
			LOGGER.info("Preparando o envio de push dos aniversariantes do dia.");
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("");
			post.setHeader("Content-type", "application/json");
			post.setHeader("Authorization", "key=");

			JSONObject message = new JSONObject();
			message.put("to", token);
			message.put("priority", "high");
			
			StringBuilder body = new StringBuilder();
			if (meuAniversario) {
				body.append("A sua Igreja lhe dá os parabéns pelo seu aniversário e deseja muitos anos de vida, na presença de Cristo.");
			} else {
				body.append("Hoje é o aniversário ");
				if (sexo.equals("M")) {
					body.append("do irmão ");
				} else {
					body.append("da irmã ");
				}
				body.append(nome);
				body.append(", parabenize pelo seu dia.");
			}

			JSONObject jsonNotification = new JSONObject();
			jsonNotification.put("title", "Feliz Aniversário");
			jsonNotification.put("body", body.toString());
			message.put("notification", jsonNotification);
			
			JSONObject jsonData = new JSONObject();
			jsonData.put("pesCodigo", pesCodigo);
			message.put("data", jsonData);

			post.setEntity(new StringEntity(message.toString(), "UTF-8"));
			HttpResponse response = client.execute(post);
			System.out.println(response);
			System.out.println(message);
		} catch (Exception exception) {
			LOGGER.error("Error ao enviar push...", exception);
		}
	}
}
