package br.com.pibsantalucia.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pibsantalucia.api.vo.ReturnApiObject;
import br.com.pibsantalucia.facade.EscalaFacade;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.util.Constantes;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.EscalaMesesVO;

@RestController
@RequestMapping("/Api/EscalaRest")
public class EscalaRest extends BaseRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(EscalaRest.class);
	
	@Autowired
	private EscalaFacade escalaFacade;
	
	@RequestMapping(path = "/ProximasEscalas", method = RequestMethod.GET)
	public void sendPush() {
		try {
			LOGGER.info("Preparando as próximas escalas...");
			
			List<EscalaMesesVO> listaProximosMeses = proximosMeses();
			
			List<Escala> listaEscalas = preperarEscalas(listaProximosMeses);
			
			escalaFacade.salvarEscalarFuturas(listaEscalas);
		} catch (Exception exception) {
			LOGGER.error("Erro ao preparar as próximas escalas.", exception);
		}
	}
	
	private List<EscalaMesesVO> proximosMeses() throws Exception {
		
		List<EscalaMesesVO> lista = new ArrayList<EscalaMesesVO>();
		Calendar calendar = Calendar.getInstance(new Locale("pt","BR"));
		// próximos 5 meses
		for (int i = 1; Constantes.QUANTIDADE_MES_ESCALAS >= i; i++) {
			EscalaMesesVO escalaMesesVO = new EscalaMesesVO();
			
			switch (calendar.get(Calendar.MONTH)+1) {
			case 1: {
				escalaMesesVO.setMesDescricao("Janeiro");
				break;
			}
			case 2: {
				escalaMesesVO.setMesDescricao("Fevereiro");
				break;
			}
			case 3: {
				escalaMesesVO.setMesDescricao("Março");
				break;
			}
			case 4: {
				escalaMesesVO.setMesDescricao("Abril");
				break;
			}
			case 5: {
				escalaMesesVO.setMesDescricao("Maio");
				break;
			}
			case 6: {
				escalaMesesVO.setMesDescricao("Junho");
				break;
			}
			case 7: {
				escalaMesesVO.setMesDescricao("Julho");
				break;
			}
			case 8: {
				escalaMesesVO.setMesDescricao("Agosto");
				break;
			}
			case 9: {
				escalaMesesVO.setMesDescricao("Setembro");
				break;
			}
			case 10: {
				escalaMesesVO.setMesDescricao("Outubro");
				break;
			}
			case 11: {
				escalaMesesVO.setMesDescricao("Novembro");
				break;
			}
			default:
				escalaMesesVO.setMesDescricao("Dezembro");
				break;
			}
			escalaMesesVO.setMes(calendar.get(Calendar.MONTH)+1);
			escalaMesesVO.setAno(calendar.get(Calendar.YEAR));
			
			lista.add(escalaMesesVO);
			calendar.add(Calendar.MONTH, +1);
		}
		return lista;
	}
	
	private List<Escala> preperarEscalas(List<EscalaMesesVO> listaProximosMeses) throws Exception {
		List<Escala> listaRetorno = new ArrayList<Escala>();
		int countSunday = 0;
		
		// tipos de escalas
		Integer[] tiposEscalas = {1, 2, 3, 4};
		// 1 - DataShow
		// 2 - Música
		// 3 - Portaria
		// 4 - Som
		for (int escFlgTipo: tiposEscalas) {
			
			// percorre os meses de cada escala
			for (EscalaMesesVO escalaMesesVO: listaProximosMeses) {
				
				StringBuilder dataInicial = new StringBuilder();
				dataInicial.append("01/");
				dataInicial.append(escalaMesesVO.getMes());
				dataInicial.append("/");
				dataInicial.append(escalaMesesVO.getAno());
				
				Date dateInicial = Util.converterStringParaDate(dataInicial.toString(), "dd/MM/yyyy");
				
				Calendar calend = Calendar.getInstance(new Locale("pt","BR"));
				calend.setTime(dateInicial);
				
				countSunday = 0;
				StringBuilder escDtEscalaString = new StringBuilder();
				for (int i = 0; 31 > i; i++) {				
					if (calend.SUNDAY == calend.get(Calendar.DAY_OF_WEEK)) {
						
						escDtEscalaString.setLength(0);
						escDtEscalaString.append(calend.get(Calendar.DAY_OF_MONTH));
						escDtEscalaString.append("/");
						escDtEscalaString.append(calend.get(Calendar.MONTH)+1);
						escDtEscalaString.append("/");
						escDtEscalaString.append(calend.get(Calendar.YEAR));
						
						String anoMesString = String.valueOf(escalaMesesVO.getAno()) + String.valueOf(escalaMesesVO.getMes());
						int anoMes = Integer.parseInt(anoMesString);
						
						java.sql.Date escDtEscala = Util.converterStringParaDate(escDtEscalaString.toString());
						
						if (escFlgTipo == 2) { // escala de música

							switch (countSunday) {
							case 0:
								Escala escala = new Escala();
								escala.setEscDescricao("1° Domingo - Manhã");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								
								escala = new Escala();
								escala.setEscDescricao("1° Domingo - Noite");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								countSunday++;
								break;
							case 1:
								escala = new Escala();
								escala.setEscDescricao("2° Domingo - Manhã");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								
								escala = new Escala();
								escala.setEscDescricao("2° Domingo - Noite");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								countSunday++;
								break;
							case 2:
								escala = new Escala();
								escala.setEscDescricao("3° Domingo - Manhã");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								
								escala = new Escala();
								escala.setEscDescricao("3° Domingo - Noite");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								countSunday++;
								break;
							case 3:
								escala = new Escala();
								escala.setEscDescricao("4° Domingo - Manhã");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								
								escala = new Escala();
								escala.setEscDescricao("4° Domingo - Noite");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								countSunday++;
								break;
							case 4:
								escala = new Escala();
								escala.setEscDescricao("5° Domingo - Manhã");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								
								escala = new Escala();
								escala.setEscDescricao("5° Domingo - Noite");
								escala.setEscDtEscala(escDtEscala);
								escala.setEscFlgTipo(escFlgTipo);
								escala.setEscAnoMes(anoMes);
								
								listaRetorno.add(escala);
								countSunday++;
								break;
							default:
								break;
							}
							
						} else {
							Escala escala = new Escala();
							escala.setEscDtEscala(escDtEscala);
							escala.setEscFlgTipo(escFlgTipo);
							escala.setEscAnoMes(anoMes);
							
							switch (countSunday) {
							case 0:
								escala.setEscDescricao("1° Domingo");
								break;
							case 1:
								escala.setEscDescricao("2° Domingo");
								break;
							case 2:
								escala.setEscDescricao("3° Domingo");
								break;
							case 3:
								escala.setEscDescricao("4° Domingo");
								break;
							case 4:
								escala.setEscDescricao("5° Domingo");
								break;
							default:
								break;
							}
							
							listaRetorno.add(escala);
							countSunday++;
						}
					}
					
					calend.add(Calendar.DATE, +1);
					
					if (escalaMesesVO.getMes() != calend.get(Calendar.MONTH)+1) {
						break;
					}
				}
			}
		}
		return listaRetorno;
	}
	
	@RequestMapping(path = "/Lista", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ReturnApiObject> lista(@RequestParam(value = "escFlgTipo") int escFlgTipo) {
		try {
			List<EscalaMesesVO> lista = escalaFacade.lista(proximosMeses(), escFlgTipo);
			
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
	public @ResponseBody ResponseEntity<ReturnApiObject> salvar(@RequestBody List<EscalaMesesVO> lista) {
		try {
			escalaFacade.salvar(lista);

			getObject().setMessage("Cadastro realizado com sucesso!");
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.OK);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			getObject().setCode(HttpStatus.BAD_REQUEST.value());
			getObject().setMessage(Constantes.SYSTEM_UNAVAILABLE_MOMENT);
			return new ResponseEntity<ReturnApiObject>(getObject(), HttpStatus.BAD_REQUEST);
		}
	}
}
