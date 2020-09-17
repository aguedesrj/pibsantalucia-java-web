package br.com.pibsantalucia.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pibsantalucia.dao.AnuncioDao;
import br.com.pibsantalucia.dao.HibernateDao;
import br.com.pibsantalucia.model.Anuncio;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.Util;
import br.com.pibsantalucia.vo.AnuncioVO;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class AnuncioFacadeImpl extends HibernateDao implements AnuncioFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(AnuncioFacadeImpl.class);
	
	@Autowired
	private AnuncioDao anuncioDao;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<AnuncioVO> lista() throws Exception {
		
		List<AnuncioVO> listaRetorno = new ArrayList<AnuncioVO>();
		List<Anuncio> lista = anuncioDao.lista();
		for (Anuncio anuncio: lista) {
			AnuncioVO anuncioVO = new AnuncioVO();
			anuncioVO.setAnuCodigo(anuncio.getAnuCodigo());
			anuncioVO.setAnuDescricao(anuncio.getAnuDescricao());
			anuncioVO.setAnuDtLimite(Util.converterDateParaString(anuncio.getAnuDtLimite()));
			
			listaRetorno.add(anuncioVO);
		}
		return listaRetorno;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(AnuncioVO anuncioVO) throws Exception {
		try {
			Anuncio anuncio = new Anuncio();
			anuncio.setAnuCodigo(anuncioVO.getAnuCodigo());
			anuncio.setAnuDescricao(anuncioVO.getAnuDescricao());
			anuncio.setAnuDtLimite(Util.converterStringParaDate(anuncioVO.getAnuDtLimite()));
			anuncio.setAnuDtCadastro(Calendar.getInstance());
			
			anuncio.setUsuarioMembro(new UsuarioMembro());
			anuncio.getUsuarioMembro().setUmeCodigo(anuncioVO.getUsuarioMembroVO().getUmeCodigo());
			
			getSession().saveOrUpdate(anuncio);
			getSession().flush();
		} catch (Exception exception) {
			LOGGER.error("Erro salvar anúncio.", exception);
			throw new Exception("ERRO.SISTEMA.INDISPONIVEL");
		}
	}
}
