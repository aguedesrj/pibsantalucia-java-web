package br.com.pibsantalucia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.facade.PessoaFacade;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.vo.CadastroVO;
import br.com.pibsantalucia.vo.MensagemUsuarioVO;
import br.com.pibsantalucia.vo.PessoaVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@RestController
@RequestMapping("/CongregadoRest")
public class CongregadoController {

	private final Logger LOGGER = LoggerFactory.getLogger(CongregadoController.class);

	@Autowired
	private PessoaFacade pessoaFacade;

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/Lista", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T listar() {
		try {
			List<ViewMembroVO> lista = pessoaFacade.listaCongregados(null, null, "S");
			return (T) lista;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/ListaCadastro", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T listarCadastro() {
		try {
			CadastroVO cadastroVO = new CadastroVO();
			cadastroVO.setListaTipoContatoVO(pessoaFacade.listaTipoContatos());
//			cadastroVO.setListaEstadoVO(pessoaFacade.listaEstados());
//			cadastroVO.setListaProfissaoVO(pessoaFacade.listaProfissoes());
			return (T) cadastroVO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/GetCep", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T getCep(@RequestParam(value = "cep") String cep) {
		try {
			return (T) pessoaFacade.buscarCep(cep);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/GetCongregado", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T getCongregado(@RequestHeader(value = "pesCodigo") long pesCodigo) {
		try {
			PessoaVO pessoaVO = pessoaFacade.obterCongregado(pesCodigo);
			return (T) pessoaVO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/Salva", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody <T> T salvar(@RequestBody PessoaVO pessoaVO) {
		try {
			pessoaFacade.salvar(pessoaVO);

			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			if (pessoaVO.getPesCodigo() == 0) {
				mensagemUsuarioVO.setMensagem("Cadastro realizado com sucesso!");
			} else {
				mensagemUsuarioVO.setMensagem("Atualização realizado com sucesso!");
			}
			return (T) mensagemUsuarioVO;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			MensagemUsuarioVO mensagemUsuarioVO = new MensagemUsuarioVO();
			mensagemUsuarioVO.getMensagemErro().add(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return (T) mensagemUsuarioVO;
		}
	}
}
