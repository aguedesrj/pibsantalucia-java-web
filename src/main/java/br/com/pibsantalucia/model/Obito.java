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
@Table(name = "TBL_OBITO")
public class Obito implements Serializable {

	private static final long serialVersionUID = -87901204074008724L;

	@Id
	@Column(name = "OBI_CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int obiCodigo;

	@Column(name = "OBI_DTCADASTRO")
	private Calendar obiDtCadastro;

	@Column(name = "OBI_CAUSA")
	private String obiCausa;

	@Column(name = "OBI_OBSERVACAO")
	private String obiObservacao;

	@Column(name = "OBI_DTOBITO")
	private Date obiDtObito;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	public int getObiCodigo() {
		return obiCodigo;
	}

	public void setObiCodigo(int obiCodigo) {
		this.obiCodigo = obiCodigo;
	}

	public Calendar getObiDtCadastro() {
		return obiDtCadastro;
	}

	public void setObiDtCadastro(Calendar obiDtCadastro) {
		this.obiDtCadastro = obiDtCadastro;
	}

	public String getObiCausa() {
		return obiCausa;
	}

	public void setObiCausa(String obiCausa) {
		this.obiCausa = obiCausa;
	}

	public String getObiObservacao() {
		return obiObservacao;
	}

	public void setObiObservacao(String obiObservacao) {
		this.obiObservacao = obiObservacao;
	}

	public Date getObiDtObito() {
		return obiDtObito;
	}

	public void setObiDtObito(Date obiDtObito) {
		this.obiDtObito = obiDtObito;
	}

	public UsuarioMembro getUsuarioMembro() {
		return usuarioMembro;
	}

	public void setUsuarioMembro(UsuarioMembro usuarioMembro) {
		this.usuarioMembro = usuarioMembro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
