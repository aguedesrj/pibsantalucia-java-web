package br.com.pibsantalucia.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.api.vo.ReturnApiObject;
import br.com.pibsantalucia.facade.AnuncioFacade;
import br.com.pibsantalucia.security.JWTUtil;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.AnuncioVO;
import br.com.pibsantalucia.vo.UsuarioMembroVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/Api/AnuncioRest")
public class AnuncioRest extends BaseRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AnuncioRest.class);
	
	@Autowired
	private AnuncioFacade anuncioFacade;
	
	@RequestMapping(path = "/Lista", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> lista() {
		try {
			List<AnuncioVO> lista = anuncioFacade.lista();
			if (lista.isEmpty()) {
				getObject().setCode(HttpStatus.BAD_REQUEST.value());
				getObject().setMessage("Nenhum anúncio encontrado até o momento.");
				return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
			}
			
			getObject().setObject(lista);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/Salva", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> salvar(@RequestBody AnuncioVO anuncioVO, @RequestHeader(value="Authentication") String token) {
		try {
			
			if (!Util.isCampoPreenchido(anuncioVO.getAnuDtLimite())) {
				throw new BusinessException("A data limite é obrigatório.");
			} else if (!Util.isDataValida(anuncioVO.getAnuDtLimite())) {
				throw new BusinessException("A data limite é inválida.");
			}
			
			if (!Util.isCampoPreenchido(anuncioVO.getAnuDescricao())) {
				throw new BusinessException("A descrição do Anúncio é obrigatório.");
			}
			
			Jws<Claims> jws = JWTUtil.decode(token);
			String umeCodigo = jws.getBody().getSubject();
			
			UsuarioMembroVO usuarioMembroVO = new UsuarioMembroVO();
			usuarioMembroVO.setUmeCodigo(Long.parseLong(umeCodigo));
			anuncioVO.setUsuarioMembroVO(usuarioMembroVO);
			
			anuncioFacade.salvar(anuncioVO);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			LOGGER.error(exception.getMessage());
			if (exception instanceof BusinessException) {
				getObject().setMessage(exception.getMessage());
			} else {
				getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			}
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
}
