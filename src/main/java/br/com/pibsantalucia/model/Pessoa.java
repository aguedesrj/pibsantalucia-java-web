package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.CascadeType;
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
@Table(name = "TBL_PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -5867879050966764451L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PES_CODIGO", updatable = false, nullable = false)
	private long pesCodigo;

	@Column(name = "PES_NOME")
	private String pesNome;

	@Column(name = "PES_DTNASCIMENTO")
	private Date pesDtNascimento;

	@Column(name = "PES_SEXO")
	private String pesSexo;

	@Column(name = "PES_CPF")
	private String pesCpf;

	@Column(name = "PES_TIPOSANGUINEO")
	private String pesTipoSanguineo;

	@Column(name = "PES_NATURALIDADE")
	private String pesNaturalidade;

	@Column(name = "PES_NACIONALIDADE")
	private String pesNacionalidade;

	@Column(name = "PES_NOMEMAE")
	private String pesNomeMae;

	@Column(name = "PES_NOMEPAI")
	private String pesNomePai;

	@Column(name = "PES_DTCASAMENTO")
	private Date pesDtCasamento;

	@Column(name = "PES_FOTO")
	private byte[] pesFoto;

	@Column(name = "PES_OBSERVACAO")
	private String pesObservacao;

	@Column(name = "PES_DTBATISMO")
	private Date pesDtBatismo;

	@Column(name = "PES_DTCADASTRO")
	private Calendar pesDtCadastro;

	@Column(name = "PES_FLGMEMBRO")
	private String pesFglMembro;
	
	@Column(name = "PES_PROFISSAO")
	private String pesProfissao;
	
	@Column(name = "PES_NAMEPHOTO")
	private String pesNamePhoto;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UME_CODIGO")
	private UsuarioMembro usuarioMembro;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "END_CODIGO")
	private Endereco endereco;

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

	public Date getPesDtNascimento() {
		return pesDtNascimento;
	}

	public void setPesDtNascimento(Date pesDtNascimento) {
		this.pesDtNascimento = pesDtNascimento;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public String getPesCpf() {
		return pesCpf;
	}

	public void setPesCpf(String pesCpf) {
		this.pesCpf = pesCpf;
	}

	public String getPesTipoSanguineo() {
		return pesTipoSanguineo;
	}

	public void setPesTipoSanguineo(String pesTipoSanguineo) {
		this.pesTipoSanguineo = pesTipoSanguineo;
	}

	public String getPesNaturalidade() {
		return pesNaturalidade;
	}

	public void setPesNaturalidade(String pesNaturalidade) {
		this.pesNaturalidade = pesNaturalidade;
	}

	public String getPesNacionalidade() {
		return pesNacionalidade;
	}

	public void setPesNacionalidade(String pesNacionalidade) {
		this.pesNacionalidade = pesNacionalidade;
	}

	public String getPesNomeMae() {
		return pesNomeMae;
	}

	public void setPesNomeMae(String pesNomeMae) {
		this.pesNomeMae = pesNomeMae;
	}

	public String getPesNomePai() {
		return pesNomePai;
	}

	public void setPesNomePai(String pesNomePai) {
		this.pesNomePai = pesNomePai;
	}

	public Date getPesDtCasamento() {
		return pesDtCasamento;
	}

	public void setPesDtCasamento(Date pesDtCasamento) {
		this.pesDtCasamento = pesDtCasamento;
	}

	public byte[] getPesFoto() {
		return pesFoto;
	}

	public void setPesFoto(byte[] pesFoto) {
		this.pesFoto = pesFoto;
	}

	public String getPesObservacao() {
		return pesObservacao;
	}

	public void setPesObservacao(String pesObservacao) {
		this.pesObservacao = pesObservacao;
	}

	public Date getPesDtBatismo() {
		return pesDtBatismo;
	}

	public void setPesDtBatismo(Date pesDtBatismo) {
		this.pesDtBatismo = pesDtBatismo;
	}

	public Calendar getPesDtCadastro() {
		return pesDtCadastro;
	}

	public void setPesDtCadastro(Calendar pesDtCadastro) {
		this.pesDtCadastro = pesDtCadastro;
	}

	public String isPesFglMembro() {
		return pesFglMembro;
	}

	public void setPesFglMembro(String pesFglMembro) {
		this.pesFglMembro = pesFglMembro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getPesProfissao() {
		return pesProfissao;
	}

	public void setPesProfissao(String pesProfissao) {
		this.pesProfissao = pesProfissao;
	}

	public UsuarioMembro getUsuarioMembro() {
		return usuarioMembro;
	}

	public void setUsuarioMembro(UsuarioMembro usuarioMembro) {
		this.usuarioMembro = usuarioMembro;
	}

	public String getPesNamePhoto() {
		return pesNamePhoto;
	}

	public void setPesNamePhoto(String pesNamePhoto) {
		this.pesNamePhoto = pesNamePhoto;
	}
}
