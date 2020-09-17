<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- css -->
<link href="../resources/css/formRequired.css" rel="stylesheet" type="text/css" />
<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>

<!-- javascript -->
<script src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>
<script src="../resources/js/pages/usuario/usuarioCadastro.js"></script>

<!-- Mensagem -->
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO"  				value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_LOGIN_OBRIGATORIO" 	 	  				value="%{getText('ERRO.LOGIN.OBRIGATORIO')}"/>
<s:hidden id="ERRO_SENHA_OBRIGATORIA" 	 	  				value="%{getText('ERRO.SENHA.OBRIGATORIA')}"/>
<s:hidden id="ERRO_LOGIN_INVALIDO" 	 		  				value="%{getText('ERRO.LOGIN.INVALIDO')}"/>
<s:hidden id="ERRO_SENHA_INVALIDA" 	 		  				value="%{getText('ERRO.SENHA.INVALIDA')}"/>
<s:hidden id="ERRO_LOGIN_CARACTERES_ESPECIAIS" 				value="%{getText('ERRO.LOGIN.CARACTERES.ESPECIAIS')}"/>
<s:hidden id="ERRO_CADASTRO_EMAIL_OBRIGATORIO" 				value="%{getText('ERRO.CADASTRO.EMAIL.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_EMAIL_INVALIDO" 	  				value="%{getText('ERRO.CADASTRO.EMAIL.INVALIDO')}"/>
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO"  				value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_NOME_INVALIDO" 	  				value="%{getText('ERRO.CADASTRO.NOME.INVALIDO')}"/>
<s:hidden id="ERRO_USUARIO_STATUS_OBRIGATORIO" 	  			value="%{getText('ERRO.USUARIO.STATUS.OBRIGATORIO')}"/>
<s:hidden id="ERRO_USUARIO_SENHA_DIFERE_CONFIRMACAO.SENHA" 	value="%{getText('ERRO.USUARIO.SENHA.DIFERE.CONFIRMACAO.SENHA')}"/>

<s:form class="ui form" namespace="Usuario" id="formUsuario" name="formUsuario" theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="usuCodigo" name="viewUsuarioVO.usuCodigo"/>
	<div class="col-sm-12">
		<div class="row">
			<div class="col-sm-5 form-group">
				<label>Nome</label>
				<div class="required-field-block">
					<s:textfield class="form-control" name="viewUsuarioVO.usuNome" id="usuNome" maxlength="80" theme="simple" placeholder="Informe o nome"/>
            		</div>
			</div>
			<div class="col-sm-4 form-group">
				<label>E-mail</label>
				<div class="required-field-block">
					<s:textfield class="form-control" name="viewUsuarioVO.usuEmail" id="usuEmail" maxlength="45" theme="simple" placeholder="Informe o e-mail"/>
            		</div>					
			</div>			
			<div class="col-sm-3 form-group">
				<label>Status de ativo</label>
				<div>
					<s:if test='viewUsuarioVO.usuStatus == "S"'>
						<label class="radio-inline">
							<input id="usuStatus" type="radio" value="S" name="viewUsuarioVO.usuStatus" tabindex="0" checked="checked">Sim
        					</label> 
        				<label class="radio-inline">
	        				<input id="usuStatus" type="radio" value="N" name="viewUsuarioVO.usuStatus" tabindex="0">Não
        					</label>
					</s:if>
					<s:elseif test='viewUsuarioVO.usuStatus == "N"'>
						<label class="radio-inline">
							<input id="usuStatus" type="radio" value="S" name="viewUsuarioVO.usuStatus" tabindex="0">Sim
        					</label> 
        				<label class="radio-inline">
	        				<input id="usuStatus" type="radio" value="N" name="viewUsuarioVO.usuStatus" tabindex="0" checked="checked">Não
        					</label> 								
					</s:elseif>
					<s:else>
						<label class="radio-inline">
							<input id="usuStatus" type="radio" value="S" name="viewUsuarioVO.usuStatus" tabindex="0">Sim
        					</label> 
        				<label class="radio-inline">
	        				<input id="usuStatus" type="radio" value="N" name="viewUsuarioVO.usuStatus" tabindex="0">Não
        					</label> 							
					</s:else>
				</div>				
			</div>						
		</div>
		<div class="row">
			<div class="col-sm-3 form-group">
				<label>Login</label>
				<div class="required-field-block">
					<s:textfield class="form-control" name="viewUsuarioVO.usuLogin" id="usuLogin" maxlength="15" theme="simple" placeholder="Informe o login"/>
            		</div>			
			</div>
			<div class="col-sm-3 form-group">
				<label>Senha</label>
				<div class="required-field-block">
					<s:password class="form-control" name="viewUsuarioVO.usuSenha" id="usuSenha" maxlength="15" theme="simple" placeholder="Informe a senha"/>
            		</div>			
			</div>
			<div class="col-sm-3 form-group">
				<label>Confirma Senha</label>
				<div class="required-field-block">
					<s:password class="form-control" name="viewUsuarioVO.usuConfirmacaoSenha" id="usuConfirmacaoSenha" maxlength="15" theme="simple" placeholder="Repita novamente a senha"/>
            		</div>			
			</div>			
		</div>	
		<button style="margin-top: 10px;" type="submit" class="btn btn-primary">Salvar</button>
		<button id="btnNovoCadastro" style="margin-top: 10px; margin-left: 15px;" type="button" class="btn btn-primary">Novo Cadastro</button>				
	</div>	
</s:form>