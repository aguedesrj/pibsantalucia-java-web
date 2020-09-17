package br.com.pibsantalucia.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.dao.PastoralDao;
import br.com.pibsantalucia.model.Pastoral;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.BusinessException;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.PastoralVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class PastoralFacadeImpl extends HibernateDao implements PastoralFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(PastoralFacadeImpl.class);
	
	@Autowired
	private PastoralDao pastoralDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(PastoralVO pastoralVO) throws Exception {
		try {
			Pastoral pastoral = new Pastoral();
			
			pastoral.setUsuarioMembro(new UsuarioMembro());
			pastoral.getUsuarioMembro().setUmeCodigo(pastoralVO.getUsuarioMembroVO().getUmeCodigo());
			
			pastoral.setPasCodigo(pastoralVO.getPasCodigo());
			pastoral.setPasDtCadastro(Calendar.getInstance());
			pastoral.setPasDtReferencia(Util.converterStringParaDate("15/"+pastoralVO.getPasDtReferencia()));
			pastoral.setPasPastoral(pastoralVO.getPasPastoral());
			
			getSession().saveOrUpdate(pastoral);
			getSession().flush();

		} catch (Exception exception) {
			if (exception instanceof ConstraintViolationException) {
				throw new BusinessException("Já existe uma Pastoral para a data informada.");
			}
			LOGGER.error("Erro salvar pastoral.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PastoralVO> lista() throws Exception {
		List<Pastoral> list = pastoralDao.lista();
		if (list != null) {
			List<PastoralVO> listaRetorno = new ArrayList<PastoralVO>();
			for (Pastoral pastoral : list) {
				PastoralVO pastoralVO = new PastoralVO();
				pastoralVO.setPasCodigo(pastoral.getPasCodigo());
				String pasDtReferencia = Util.converterDateParaString(pastoral.getPasDtReferencia());
				String[] arrayPasDtReferencia = pasDtReferencia.split("/");
				
				pastoralVO.setPasDtReferencia(arrayPasDtReferencia[1]+"/"+arrayPasDtReferencia[2]);
				pastoralVO.setPasPastoral(pastoral.getPasPastoral());
				

				listaRetorno.add(pastoralVO);
			}
			return listaRetorno;
		}
		return null;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PastoralVO obterPorId(long pasCodigo) throws Exception {
		Pastoral pastoral = pastoralDao.obterPorId(pasCodigo);
		PastoralVO pastoralVO = new PastoralVO();
		
		pastoralVO.setPasCodigo(pastoral.getPasCodigo());
		String pasDtReferencia = Util.converterDateParaString(pastoral.getPasDtReferencia());
		String[] arrayPasDtReferencia = pasDtReferencia.split("/");
		
		pastoralVO.setPasDtReferencia(arrayPasDtReferencia[1]+"/"+arrayPasDtReferencia[2]);
		pastoralVO.setPasPastoral(pastoral.getPasPastoral());
		
		return pastoralVO;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PastoralVO obterPorDataReferencia(String pasReferencia) throws Exception {
		Pastoral pastoral = pastoralDao.obterPorDataReferencia(pasReferencia);
		PastoralVO pastoralVO = new PastoralVO();
		
		pastoralVO.setPasCodigo(pastoral.getPasCodigo());
		String pasDtReferencia = Util.converterDateParaString(pastoral.getPasDtReferencia());
		String[] arrayPasDtReferencia = pasDtReferencia.split("/");
		
		pastoralVO.setPasDtReferencia(arrayPasDtReferencia[1]+"/"+arrayPasDtReferencia[2]);
		pastoralVO.setPasPastoral(pastoral.getPasPastoral());
		
		return pastoralVO;
	}
}
