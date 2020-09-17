package br.com.pibsantalucia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.facade.TransferenciaFacade;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.TransferenciaVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@RestController
@RequestMapping("/CongregadoTransferenciaRest")
public class CongregadoTransferenciaController {

	@Autowired
	private PessoaFacade pessoaFacade;

	@Autowired
	private TransferenciaFacade transferenciaFacade;

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/Lista", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T listar() {
		try {
			List<ViewMembroVO> lista = pessoaFacade.listaCongregados(null, null, null);
			return (T) lista;
		} catch (Exception e) {
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/Salva", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T salvar(@RequestBody TransferenciaVO transferenciaVO) {
		try {
			transferenciaFacade.salvar(transferenciaVO);

			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.setMensagem("Transferência realizado com sucesso!");
			return (T) mensagemUsuarioVO;
		} catch (Exception e) {
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}
}
