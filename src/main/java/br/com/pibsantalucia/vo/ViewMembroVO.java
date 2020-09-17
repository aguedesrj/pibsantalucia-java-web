package br.com.pibsantalucia.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ViewMembroVO implements Serializable {

	private static final long serialVersionUID = 7965345372072350788L;

	private long pesCodigo;
	private String pesNome;
	private String pesSexo;
	private String aniversarioFmt;
	private String dtAniversarioFmt;
	@JsonIgnore
	private byte[] pesFoto;
	private String pesFotoPath;
	private boolean pesFglMembro;
	private boolean pesObito;
	private boolean pesDesligamento;
	private boolean pesTransferencia;

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public String getPesFotoPath() {
		return pesFotoPath;
	}

	public void setPesFotoPath(String pesFotoPath) {
		this.pesFotoPath = pesFotoPath;
	}

	public byte[] getPesFoto() {
		return pesFoto;
	}

	public void setPesFoto(byte[] pesFoto) {
		this.pesFoto = pesFoto;
	}

	public boolean isPesFglMembro() {
		return pesFglMembro;
	}

	public void setPesFglMembro(boolean pesFglMembro) {
		this.pesFglMembro = pesFglMembro;
	}

	public boolean isPesObito() {
		return pesObito;
	}

	public void setPesObito(boolean pesObito) {
		this.pesObito = pesObito;
	}

	public boolean isPesDesligamento() {
		return pesDesligamento;
	}

	public void setPesDesligamento(boolean pesDesligamento) {
		this.pesDesligamento = pesDesligamento;
	}

	public boolean isPesTransferencia() {
		return pesTransferencia;
	}

	public void setPesTransferencia(boolean pesTransferencia) {
		this.pesTransferencia = pesTransferencia;
	}

	public String getAniversarioFmt() {
		return aniversarioFmt;
	}

	public void setAniversarioFmt(String aniversarioFmt) {
		this.aniversarioFmt = aniversarioFmt;
	}

	public String getDtAniversarioFmt() {
		return dtAniversarioFmt;
	}

	public void setDtAniversarioFmt(String dtAniversarioFmt) {
		this.dtAniversarioFmt = dtAniversarioFmt;
	}
}
