package br.com.pibsantalucia.facade;

import java.util.List;

import br.com.pibsantalucia.model.Anuncio;
import br.com.pibsantalucia.model.Contato;
import br.com.pibsantalucia.model.Desligamento;
import br.com.pibsantalucia.model.DeviceMobile;
import br.com.pibsantalucia.model.Endereco;
import br.com.pibsantalucia.model.Escala;
import br.com.pibsantalucia.model.EscalaMembro;
import br.com.pibsantalucia.model.Obito;
import br.com.pibsantalucia.model.Pastoral;
import br.com.pibsantalucia.model.Perfil;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.Reconciliacao;
import br.com.pibsantalucia.model.Transferencia;
import br.com.pibsantalucia.model.UsuarioMembro;

public interface BackupFacade {

	public List<Endereco> listaEndereco() throws Exception;
	
	public List<Obito> listaObito() throws Exception;
	
	public List<Desligamento> listaDesligamento() throws Exception;
	
	public List<Contato> listaContato() throws Exception;
	
	public List<Pessoa> listaPessoa() throws Exception;
	
	public List<UsuarioMembro> listaUsuarioMembro() throws Exception;
	
	public List<Transferencia> listaTransferencia() throws Exception;
	
	public List<Reconciliacao> listaReconciliacao() throws Exception;
	
	public List<Perfil> listaPerfil() throws Exception;
	
	public List<DeviceMobile> listaDevice() throws Exception;
	
	public List<Pastoral> listaPastoral() throws Exception;
	
	public List<Escala> listaEscala() throws Exception;
	
	public List<EscalaMembro> listaEscalaMembro() throws Exception;
	
	public List<Anuncio> listaAnuncio() throws Exception;
}
