package br.com.pibsantalucia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.facade.DesligamentoFacade;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.DesligamentoVO;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;

@RestController
@RequestMapping("/CongregadoDesligamentoRest")
public class CongregadoDesligamentoController {
	private final Logger LOGGER = LoggerFactory.getLogger(CongregadoDesligamentoController.class);

	@Autowired
	private DesligamentoFacade desligamentoFacade;

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/Salva", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T salvar(@RequestBody DesligamentoVO desligamentoVO) {
		try {
			desligamentoFacade.salvar(desligamentoVO);

			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.setMensagem("Desligamento realizado com sucesso!");
			return (T) mensagemUsuarioVO;
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar o desligamento.", e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}
}
