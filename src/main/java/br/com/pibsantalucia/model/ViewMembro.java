package br.com.pibsantalucia.model;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "VIW_MEMBRO")
public class ViewMembro implements Serializable {

	private static final long serialVersionUID = -7391434504791572758L;

	@Id
	@Column(name = "PES_CODIGO")
	private int pesCodigo;

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
	
	@Column(name = "PES_NAMEPHOTO")
	private String pesNamePhoto;

	@Column(name = "PES_DTBATISMO")
	private Date pesDtBatismo;

	@Column(name = "PES_DTCADASTRO")
	private Calendar pesDtCadastro;

	@Column(name = "PES_FLGMEMBRO")
	private String pesFglMembro;
	
	@Column(name = "PES_PROFISSAO")
	private String pesProfissao;

	@Column(name = "END_CODIGO")
	private Long endCodigo;

	@Column(name = "END_LOGRADOURO")
	private String endLogradouro;

	@Column(name = "END_COMPLEMENTO")
	private String endComplemento;

	@Column(name = "END_NUMERO")
	private String endNumero;

	@Column(name = "END_BAIRRO")
	private String endBairro;

	@Column(name = "END_CIDADE")
	private String endCidade;

	@Column(name = "END_CEP")
	private String endCep;
	
	@Column(name = "END_ESTADO")
	private String endEstado;

	public int getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(int pesCodigo) {
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

	public String isPesFglMembro() {
		return pesFglMembro;
	}

	public void setPesFglMembro(String pesFglMembro) {
		this.pesFglMembro = pesFglMembro;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
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

	public Long getEndCodigo() {
		return endCodigo;
	}

	public void setEndCodigo(Long endCodigo) {
		this.endCodigo = endCodigo;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public String getEndComplemento() {
		return endComplemento;
	}

	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getPesProfissao() {
		return pesProfissao;
	}

	public void setPesProfissao(String pesProfissao) {
		this.pesProfissao = pesProfissao;
	}

	public String getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(String endEstado) {
		this.endEstado = endEstado;
	}

	public String getPesNamePhoto() {
		return pesNamePhoto;
	}

	public void setPesNamePhoto(String pesNamePhoto) {
		this.pesNamePhoto = pesNamePhoto;
	}
}
