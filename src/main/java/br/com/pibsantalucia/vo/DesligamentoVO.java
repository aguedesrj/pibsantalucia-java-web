package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class DesligamentoVO implements Serializable {

	private static final long serialVersionUID = -1575826691845090129L;

	private long pesCodigo;
	private String desDtCadastro;
	private String desDtDesligamento;
	private String desObservacao;
	private UsuarioMembroVO usuarioMembroVO;

	public String getDesDtCadastro() {
		return desDtCadastro;
	}

	public void setDesDtCadastro(String desDtCadastro) {
		this.desDtCadastro = desDtCadastro;
	}

	public String getDesDtDesligamento() {
		return desDtDesligamento;
	}

	public void setDesDtDesligamento(String desDtDesligamento) {
		this.desDtDesligamento = desDtDesligamento;
	}

	public String getDesObservacao() {
		return desObservacao;
	}

	public void setDesObservacao(String desObservacao) {
		this.desObservacao = desObservacao;
	}

	public UsuarioMembroVO getUsuarioMembroVO() {
		return usuarioMembroVO;
	}

	public void setUsuarioMembroVO(UsuarioMembroVO usuarioMembroVO) {
		this.usuarioMembroVO = usuarioMembroVO;
	}

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
}
