package br.com.pibsantalucia.model;

import java.io.Serializable;
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
@Table(name = "TBL_USUARIO_MEMBRO")
public class UsuarioMembro implements Serializable {

	private static final long serialVersionUID = -451000011456958906L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UME_CODIGO", updatable = false, nullable = false)
	private long umeCodigo;

	@Column(name = "UME_DTCADASTRO")
	private Calendar usuDtCadastro;

	@Column(name = "UME_SENHA")
	private String umeSenha;
	
	@Column(name = "UME_SENHA_TEMP")
	private Boolean umeSenhaTemp;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PES_CODIGO")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PER_CODIGO")
	private Perfil perfil;

	public long getUmeCodigo() {
		return umeCodigo;
	}

	public void setUmeCodigo(long umeCodigo) {
		this.umeCodigo = umeCodigo;
	}

	public Calendar getUsuDtCadastro() {
		return usuDtCadastro;
	}

	public void setUsuDtCadastro(Calendar usuDtCadastro) {
		this.usuDtCadastro = usuDtCadastro;
	}

	public String getUmeSenha() {
		return umeSenha;
	}

	public void setUmeSenha(String umeSenha) {
		this.umeSenha = umeSenha;
	}

	public Boolean getUmeSenhaTemp() {
		return umeSenhaTemp;
	}

	public void setUmeSenhaTemp(Boolean umeSenhaTemp) {
		this.umeSenhaTemp = umeSenhaTemp;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
