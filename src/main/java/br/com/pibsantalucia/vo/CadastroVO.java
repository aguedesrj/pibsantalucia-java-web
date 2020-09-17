package br.com.pibsantalucia.vo;

import java.io.Serializable;
import java.util.List;

public class CadastroVO implements Serializable {

	private static final long serialVersionUID = -1080924946719702506L;

	private List<TipoContatoVO> listaTipoContatoVO;

	public List<TipoContatoVO> getListaTipoContatoVO() {
		return listaTipoContatoVO;
	}

	public void setListaTipoContatoVO(List<TipoContatoVO> listaTipoContatoVO) {
		this.listaTipoContatoVO = listaTipoContatoVO;
	}
}
