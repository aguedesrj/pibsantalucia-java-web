package br.com.pibsantalucia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "VIW_ESCALA_MEMBRO")
public class ViewEscalaMembro implements Serializable {

	private static final long serialVersionUID = 3311287741646189169L;

	@Id
	@Column(name = "PES_CODIGO")
	private long pesCodigo;
	
	@Column(name = "ESC_CODIGO")
	private long escCodigo;

	@Column(name = "PES_NOME")
	private String pesNome;
	
	@Column(name = "PES_NAMEPHOTO")
	private String pesNamePhoto;

	public long getEscCodigo() {
		return escCodigo;
	}

	public void setEscCodigo(long escCodigo) {
		this.escCodigo = escCodigo;
	}

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

	public String getPesNamePhoto() {
		return pesNamePhoto;
	}

	public void setPesNamePhoto(String pesNamePhoto) {
		this.pesNamePhoto = pesNamePhoto;
	}
}
