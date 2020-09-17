package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
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
@Table(name = "TBL_ANUNCIO")
public class Anuncio implements Serializable {

	private static final long serialVersionUID = -4923008917003310336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANU_CODIGO", updatable = false, nullable = false)
	private long anuCodigo;
	
	@Column(name = "ANU_DESCRICAO")
	private String anuDescricao;

	@Column(name = "ANU_DTCADASTRO")
	private Calendar anuDtCadastro;
	
	@Column(name = "ANU_DTLIMITE")
	private Date anuDtLimite;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	public long getAnuCodigo() {
		return anuCodigo;
	}

	public void setAnuCodigo(long anuCodigo) {
		this.anuCodigo = anuCodigo;
	}

	public String getAnuDescricao() {
		return anuDescricao;
	}

	public void setAnuDescricao(String anuDescricao) {
		this.anuDescricao = anuDescricao;
	}

	public Calendar getAnuDtCadastro() {
		return anuDtCadastro;
	}

	public void setAnuDtCadastro(Calendar anuDtCadastro) {
		this.anuDtCadastro = anuDtCadastro;
	}

	public Date getAnuDtLimite() {
		return anuDtLimite;
	}

	public void setAnuDtLimite(Date anuDtLimite) {
		this.anuDtLimite = anuDtLimite;
	}

	public UsuarioMembro getUsuarioMembro() {
		return usuarioMembro;
	}

	public void setUsuarioMembro(UsuarioMembro usuarioMembro) {
		this.usuarioMembro = usuarioMembro;
	}
}
