package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class ReconciliacaoVO implements Serializable {

	private static final long serialVersionUID = 7777887800841213941L;
	
	private long pesCodigo;
	private String recDtCadastro;
	private String recDtReconciliacao;
	private String recObservacao;
	private UsuarioMembroVO usuarioMembroVO;
	
	public String getRecDtCadastro() {
		return recDtCadastro;
	}

	public void setRecDtCadastro(String recDtCadastro) {
		this.recDtCadastro = recDtCadastro;
	}

	public String getRecDtReconciliacao() {
		return recDtReconciliacao;
	}

	public void setRecDtReconciliacao(String recDtReconciliacao) {
		this.recDtReconciliacao = recDtReconciliacao;
	}

	public String getRecObservacao() {
		return recObservacao;
	}

	public void setRecObservacao(String recObservacao) {
		this.recObservacao = recObservacao;
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
