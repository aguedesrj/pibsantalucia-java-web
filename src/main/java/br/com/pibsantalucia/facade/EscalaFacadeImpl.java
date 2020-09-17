package br.com.pibsantalucia.facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pibsantalucia.dao.EscalaDao;
import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.ViewEscalaMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.EscalaMesesVO;
import br.com.pibsantalucia.vo.EscalaVO;
import br.com.pibsantalucia.vo.ViewMembroVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class EscalaFacadeImpl extends HibernateDao implements EscalaFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(EscalaFacadeImpl.class);
	
	@Autowired
	private EscalaDao escalaDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvarEscalarFuturas(List<Escala> lista) throws Exception {
		try {
			List<Escala> listaSaveOrUpdate = new ArrayList<Escala>();
			for (Escala escala: lista) {
				List<Escala> listaPesquisa = escalaDao.getEscalaPorTipoAndAnoMes(escala);
				if (listaPesquisa.isEmpty()) {
					listaSaveOrUpdate.add(escala);
				}
			}
			
			if (!listaSaveOrUpdate.isEmpty()) {
				for (Escala escala: lista) {
					getSession().saveOrUpdate(escala);
				}
				getSession().flush();
			}
		} catch (Exception exception) {
			LOGGER.error("Erro salvar pastoral.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<EscalaMesesVO> lista(List<EscalaMesesVO> listaEscalaMeses, int escFlgTipo) throws Exception {
		
		List<EscalaMesesVO> listaRetorno = new ArrayList<EscalaMesesVO>();
		List<EscalaVO> listaEscala = new ArrayList<EscalaVO>();
		
		for (EscalaMesesVO escalaMeses: listaEscalaMeses) {
			EscalaMesesVO escalaMesesVO = new EscalaMesesVO();
			
			String escAnoMesString = String.valueOf(escalaMeses.getAno()) + String.valueOf(escalaMeses.getMes());
			int escAnoMes = Integer.parseInt(escAnoMesString);
			
			listaEscala = new ArrayList<EscalaVO>();
			List<Escala> lista = escalaDao.lista(escAnoMes, escFlgTipo);
			for (Escala escala: lista) {
				
				EscalaVO escalaVO = new EscalaVO();
				escalaVO.setEscCodigo(escala.getEscCodigo());
				escalaVO.setEscDtEscala(formatarDataEscala(escala.getEscDtEscala()));
//				escalaVO.setEscDtEscala(Util.converterDateParaString(escala.getEscDtEscala()));
				escalaVO.setEscFlgTipo(escala.getEscFlgTipo());
				escalaVO.setEscDescricao(escala.getEscDescricao());
				
				List<ViewMembroVO> listaViewMembroVO = new ArrayList<ViewMembroVO>();
				List<ViewEscalaMembro> listaViewEscalaMembro = escalaDao.listaEscalaMembro(escala.getEscCodigo());
				for (ViewEscalaMembro viewEscalaMembro: listaViewEscalaMembro) {
					ViewMembroVO viewMembroVO = new ViewMembroVO();
					
					viewMembroVO.setPesCodigo(viewEscalaMembro.getPesCodigo());
					viewMembroVO.setPesNome(viewEscalaMembro.getPesNome());
					viewMembroVO.setPesFotoPath(Util.getPathPhotoCongregado(viewEscalaMembro.getPesCodigo(), viewEscalaMembro.getPesNamePhoto()));
					
					listaViewMembroVO.add(viewMembroVO);
				}
				
				escalaVO.setLista(listaViewMembroVO);
				listaEscala.add(escalaVO);
			}
			escalaMesesVO.setAno(escalaMeses.getAno());
			escalaMesesVO.setMes(escalaMeses.getMes());
			escalaMesesVO.setMesDescricao(escalaMeses.getMesDescricao());
			escalaMesesVO.setListaEscalas(listaEscala);
			
			listaRetorno.add(escalaMesesVO);
		}
		return listaRetorno;
	}
	
	private String formatarDataEscala(Date escDtEscala) throws Exception {
		Calendar calend = Calendar.getInstance(new Locale("pt","BR"));
		calend.setTime(escDtEscala);
		
		return "Dia " + calend.get(Calendar.DAY_OF_MONTH) + "/" +(calend.get(Calendar.MONTH)+1);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(List<EscalaMesesVO> lista) throws Exception {
		for (EscalaMesesVO escalaMesesVO: lista) {
			
			for (EscalaVO escalaVO: escalaMesesVO.getListaEscalas()) {
				
				// deletar os membros na escala.
				escalaDao.deletarPorEscala(escalaVO.getEscCodigo());
				
				// inserir os novos membros na escala
				for (ViewMembroVO viewMembroVO: escalaVO.getLista()) {
					
					escalaDao.insertPorEscala(escalaVO.getEscCodigo(), viewMembroVO.getPesCodigo());
				}
			}
		}
		getSession().flush();
	}
}
