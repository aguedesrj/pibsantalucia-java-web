package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_PASTORAL")
public class Pastoral implements Serializable {
	
	private static final long serialVersionUID = -8858172534188601191L;

	@Id
	@Column(name = "PAS_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pasCodigo;

	@Column(name = "PAS_DTCADASTRO")
	private Calendar pasDtCadastro;
	
	@Column(name = "PAS_DTREFERENCIA")
	private Date pasDtReferencia;
	
	@Column(name = "PAS_PASTORAL")
	private String pasPastoral;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	public long getPasCodigo() {
		return pasCodigo;
	}

	public void setPasCodigo(long pasCodigo) {
		this.pasCodigo = pasCodigo;
	}

	public Calendar getPasDtCadastro() {
		return pasDtCadastro;
	}

	public void setPasDtCadastro(Calendar pasDtCadastro) {
		this.pasDtCadastro = pasDtCadastro;
	}

	public Date getPasDtReferencia() {
		return pasDtReferencia;
	}

	public void setPasDtReferencia(Date pasDtReferencia) {
		this.pasDtReferencia = pasDtReferencia;
	}

	public String getPasPastoral() {
		return pasPastoral;
	}

	public void setPasPastoral(String pasPastoral) {
		this.pasPastoral = pasPastoral;
	}

	public UsuarioMembro getUsuarioMembro() {
		return usuarioMembro;
	}

	public void setUsuarioMembro(UsuarioMembro usuarioMembro) {
		this.usuarioMembro = usuarioMembro;
	}
}
