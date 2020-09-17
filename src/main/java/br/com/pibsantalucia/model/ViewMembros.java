package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "VIW_MEMBROS")
public class ViewMembros implements Serializable {

	private static final long serialVersionUID = -4196420742692446137L;

	@Id
	@Column(name = "PES_CODIGO")
	private long pesCodigo;

	@Column(name = "PES_NOME")
	private String pesNome;

	@Column(name = "PES_SEXO")
	private String pesSexo;
	
	@Column(name = "PES_DTNASCIMENTO")
	private Date pesDtNascimento;

	@Column(name = "PES_FLGMEMBRO")
	private boolean pesFglMembro;

	@Column(name = "PES_FOTO")
	private byte[] pesFoto;
	
	@Column(name = "PES_NAMEPHOTO")
	private String pesNamePhoto;
	
	@Column(name = "PES_OBITO")
	private boolean pesObito;
	
	@Column(name = "PES_DESLIGAMENTO")
	private boolean pesDesligamento;
	
	@Column(name = "PES_TRANSFERENCIA")
	private boolean pesTransferencia;

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

	public byte[] getPesFoto() {
		return pesFoto;
	}

	public void setPesFoto(byte[] pesFoto) {
		this.pesFoto = pesFoto;
	}

	public String getPesNamePhoto() {
		return pesNamePhoto;
	}

	public void setPesNamePhoto(String pesNamePhoto) {
		this.pesNamePhoto = pesNamePhoto;
	}

	public long getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(long pesCodigo) {
		this.pesCodigo = pesCodigo;
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

	public Date getPesDtNascimento() {
		return pesDtNascimento;
	}

	public void setPesDtNascimento(Date pesDtNascimento) {
		this.pesDtNascimento = pesDtNascimento;
	}
}
