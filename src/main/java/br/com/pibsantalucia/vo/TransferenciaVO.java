package br.com.pibsantalucia.vo;

import java.io.Serializable;

public class TransferenciaVO implements Serializable {

	private static final long serialVersionUID = -5569041201872763681L;
	
	private long pesCodigo;
	private String traDtCadastro;
	private String traMotivo;
	private String traNomeIgreja;
	private String traStatus;
	private String traObservacao;
	private String traDtTransferencia;
	private UsuarioMembroVO usuarioMembroVO;
	
	public String getTraDtCadastro() {
		return traDtCadastro;
	}
	
	public void setTraDtCadastro(String traDtCadastro) {
		this.traDtCadastro = traDtCadastro;
	}
	
	public String getTraMotivo() {
		return traMotivo;
	}
	
	public void setTraMotivo(String traMotivo) {
		this.traMotivo = traMotivo;
	}
	
	public String getTraNomeIgreja() {
		return traNomeIgreja;
	}
	
	public void setTraNomeIgreja(String traNomeIgreja) {
		this.traNomeIgreja = traNomeIgreja;
	}
	
	public String getTraStatus() {
		return traStatus;
	}
	
	public void setTraStatus(String traStatus) {
		this.traStatus = traStatus;
	}
	
	public String getTraObservacao() {
		return traObservacao;
	}
	
	public void setTraObservacao(String traObservacao) {
		this.traObservacao = traObservacao;
	}
	
	public String getTraDtTransferencia() {
		return traDtTransferencia;
	}
	
	public void setTraDtTransferencia(String traDtTransferencia) {
		this.traDtTransferencia = traDtTransferencia;
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
