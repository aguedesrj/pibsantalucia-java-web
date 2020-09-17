package br.com.pibsantalucia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author guedes
 *
 */
public class MensagemUsuarioVO implements Serializable {

	private static final long serialVersionUID = 4896665683417546657L;
	private final Logger LOGGER = LoggerFactory.getLogger(MensagemUsuarioVO.class);

	private String mensagem;
	private List<String> mensagemErro = new ArrayList<String>();
	
	public String getMensagem() {
		LOGGER.error("Error MensagemUsuario - getMensagem: "+mensagem);
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getMensagemErro() {
		for (String message: mensagemErro) {
			LOGGER.error("Error MensagemUsuario: "+message);
		}
		return mensagemErro;
	}

	public void setMensagemErro(List<String> mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
