<!DOCTYPE html>
<!-- Tag Struts -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta charset="utf-8" />
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<%@ page contentType="text/html; charset=UTF-8" %>
		<title>::: PIB Santa Lúcia :::</title>
		
		<!-- CSS -->
		<link href="../resources/frameworks/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- JavaScripts -->
		<script src="../resources/js/jquery-3.1.1.min.js"></script>
		<script src="../resources/frameworks/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		
		<script src="../resources/js/util.js"></script>
		<script src="../resources/js/loading.js"></script>
		<script src="../resources/js/pages/login.js"></script>
		

		<!-- Website CSS style -->

		<!-- Website Font style -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		
		<!-- Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
		
		<!-- Validator -->
    	<link rel="stylesheet" href="../resources/frameworks/bootstrap-validator/css/bootstrapValidator.css"/>
    	<script type="text/javascript" src="../resources/frameworks/bootstrap-validator/js/bootstrapValidator.js"></script>
		
		<style type="text/css">
		
		body, html{
     height: 100%;
 	background-repeat: no-repeat;
 	background-color: #d3d3d3;
 	font-family: 'Oxygen', sans-serif;
}

.main{
 	margin-top: 70px;
}

h1.title { 
	font-size: 50px;
	font-family: 'Passion One', cursive; 
	font-weight: 400; 
}

hr{
	width: 10%;
	color: #fff;
}

.form-group{
	margin-bottom: 15px;
}

label{
	margin-bottom: 15px;
}

input,
input::-webkit-input-placeholder {
    font-size: 11px;
    padding-top: 3px;
}

.main-login{
 	background-color: #fff;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

}

.main-center{
 	margin-top: 30px;
 	margin: 0 auto;
 	max-width: 330px;
    padding: 40px 40px;

}

.login-button{
	margin-top: 5px;
}

.login-register{
	font-size: 11px;
	text-align: center;
}
		</style>
		
	</head>
<body>
	<s:hidden id="ERRO_SISTEMA_INDISPONIVEL" 	   value="%{getText('ERRO.SISTEMA.INDISPONIVEL')}"/>
	<s:hidden id="ERRO_LOGIN_OBRIGATORIO" 		   value="%{getText('ERRO.LOGIN.OBRIGATORIO')}"/>
	<s:hidden id="ERRO_SENHA_OBRIGATORIA" 		   value="%{getText('ERRO.SENHA.OBRIGATORIA')}"/>
	<s:hidden id="ERRO_LOGIN_INVALIDO"    		   value="%{getText('ERRO.LOGIN.INVALIDO')}"/>
	<s:hidden id="ERRO_SENHA_INVALIDA"    		   value="%{getText('ERRO.SENHA.INVALIDA')}"/>
	<s:hidden id="ERRO_LOGIN_CARACTERES_ESPECIAIS" value="%{getText('ERRO.LOGIN.CARACTERES.ESPECIAIS')}"/>

	<div class="container">
		<div class="row main">
			<div class="main-login main-center">
				<s:form class="form-horizontal" namespace="Usuario" id="formLogin" name="formLogin" theme="simple">
					<div class="form-group">
						<label class="control-label">Login</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<s:textfield class="form-control" name="viewUsuarioVO.login" id="login" size="20" maxlength="15" theme="simple" placeholder="Entre com seu login"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">Senha</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<s:password class="form-control" name="viewUsuarioVO.senha" id="senha" size="15" maxlength="10" theme="simple" placeholder="Entre com a sua senha"/>
						</div>
					</div>
					<div class="form-group ">
<!-- 						<div id="btnEntrar" class="btn btn-primary btn-lg btn-block login-button" 
			      			data-content="Clique aqui para entrar no sistema.">Entrar</div> -->
			      			<button type="button" id="btnEntrar" class="btn btn-primary btn-lg btn-block login-button">Entrar</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	
	
  <!-- Modal -->
  <div class="modal fade" id="modalLogin" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">::: PIB Santa Lúcia :::</h4>
        </div>
        <div class="modal-body">
          <p><span id="spanMensagemModal"></span></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div>	
	
</body>
</html>
