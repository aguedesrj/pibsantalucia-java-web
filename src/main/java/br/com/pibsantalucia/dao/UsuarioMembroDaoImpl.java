package br.com.pibsantalucia.dao;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.pibsantalucia.model.Perfil;
import br.com.pibsantalucia.model.Pessoa;
import br.com.pibsantalucia.model.UsuarioMembro;
import br.com.pibsantalucia.util.BusinessException;

@Repository
public class UsuarioMembroDaoImpl extends HibernateDao implements UsuarioMembroDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioMembroDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.pibsantalucia.dao.UsuarioDao#efetuarLogin(java.lang.String, java.lang.String)
	 */
	public UsuarioMembro login(final String usuLogin, final String usuSenha) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT U.UME_CODIGO, U.PER_CODIGO, U.UME_SENHA_TEMP, P.PES_CODIGO, P.PES_NOME, P.PES_NAMEPHOTO ");
			sql.append("FROM TBL_USUARIO_MEMBRO U, TBL_PESSOA P ");
			sql.append("WHERE U.UME_SENHA = '"+usuSenha+"' ");
			sql.append("AND U.PES_CODIGO = P.PES_CODIGO ");
			sql.append("AND P.PES_FLGMEMBRO = 'S' ");
			sql.append("AND (P.PES_CPF = '"+usuLogin+"' ");
			sql.append("OR EXISTS (SELECT C.PES_CODIGO FROM pibsantalucia.TBL_CONTATO C ");
			sql.append("WHERE C.PES_CODIGO = U.PES_CODIGO AND C.CTO_DESCRICAOEMAIL ");
			sql.append("= '"+usuLogin+"' AND TPC_CODIGO = 1))");

			UsuarioMembro usuarioMembro = new UsuarioMembro();
			Object[] result = (Object []) getSession().createNativeQuery(sql.toString()).getSingleResult();
			
			Perfil perfil = new Perfil();
			perfil.setPerCodigo((Integer) result[1]);
			
			usuarioMembro.setPerfil(perfil);
			usuarioMembro.setUmeSenhaTemp((Boolean) result[2]);
			
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo((Integer) result[3]);
			pessoa.setPesNome((String) result[4]);
			pessoa.setPesNamePhoto((String) result[5]);
			
			usuarioMembro.setUmeCodigo((Integer) result[0]);
			usuarioMembro.setPerfil(perfil);
			usuarioMembro.setPessoa(pessoa);

			return usuarioMembro;
		} catch (Exception exception) {
			LOGGER.error("Erro ao efetuar login.", exception);
			if (exception instanceof NoResultException) {
				throw new BusinessException();
			}
			throw exception;
		}
	}
	
	public UsuarioMembro dadosLoginExiste(final String login) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT P.PES_CODIGO, P.PES_NOME ");
			sql.append("FROM TBL_PESSOA P ");
			sql.append("WHERE P.PES_FLGMEMBRO = 'S' ");
			sql.append("AND (P.PES_CPF = '"+login+"' ");
			sql.append("OR EXISTS (SELECT C.PES_CODIGO FROM pibsantalucia.TBL_CONTATO C ");
			sql.append("WHERE C.PES_CODIGO = P.PES_CODIGO AND C.CTO_DESCRICAOEMAIL ");
			sql.append("= '"+login+"' AND TPC_CODIGO = 1))");

			UsuarioMembro usuarioMembro = new UsuarioMembro();
			Object[] result = (Object []) getSession().createNativeQuery(sql.toString()).getSingleResult();
			
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo((Integer) result[0]);
			pessoa.setPesNome((String) result[1]);

			usuarioMembro.setPessoa(pessoa);

			return usuarioMembro;
		} catch (Exception exception) {
			LOGGER.error("CPF/Email não encontrados na base de dados.", exception);
			if (exception instanceof NoResultException) {
				return null;
			}
			throw exception;
		}
	}
	
	public UsuarioMembro obterUsuarioMembro(long pesCodigo) throws Exception {
		try {
			return getSession().createQuery("from UsuarioMembro where pessoa.pesCodigo = "+pesCodigo, 
					UsuarioMembro.class).getSingleResult();
		} catch (Exception exception) {
			LOGGER.error("Erro ao obter usuário/membro.", exception);
			if (exception instanceof NoResultException) {
				LOGGER.error("return null");
				return null;
			}
			LOGGER.error("throw exception");
			throw exception;
		}
	}
	
	public UsuarioMembro verificaLoginExistente(final String login) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT U.UME_CODIGO, U.PER_CODIGO, U.UME_SENHA_TEMP, P.PES_CODIGO, P.PES_NOME, P.PES_NAMEPHOTO ");
			sql.append("FROM TBL_USUARIO_MEMBRO U, TBL_PESSOA P ");
			sql.append("WHERE U.PES_CODIGO = P.PES_CODIGO ");
			sql.append("AND P.PES_FLGMEMBRO = 'S' ");
			sql.append("AND (P.PES_CPF = '"+login+"' ");
			sql.append("OR EXISTS (SELECT C.PES_CODIGO FROM pibsantalucia.TBL_CONTATO C ");
			sql.append("WHERE C.PES_CODIGO = U.PES_CODIGO AND C.CTO_DESCRICAOEMAIL ");
			sql.append("= '"+login+"' AND TPC_CODIGO = 1))");

			UsuarioMembro usuarioMembro = new UsuarioMembro();
			Object[] result = (Object []) getSession().createNativeQuery(sql.toString()).getSingleResult();
			
			Perfil perfil = new Perfil();
			perfil.setPerCodigo((Integer) result[1]);
			
			usuarioMembro.setPerfil(perfil);
			usuarioMembro.setUmeSenhaTemp((Boolean) result[2]);
			
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo((Integer) result[3]);
			pessoa.setPesNome((String) result[4]);
			pessoa.setPesNamePhoto((String) result[5]);
			
			usuarioMembro.setUmeCodigo((Integer) result[0]);
			usuarioMembro.setPerfil(perfil);
			usuarioMembro.setPessoa(pessoa);

			return usuarioMembro;
		} catch (Exception exception) {
			LOGGER.error("Erro ao efetuar login.", exception);
			if (exception instanceof NoResultException) {
				return null;
			}
			throw exception;
		}
	}
}
