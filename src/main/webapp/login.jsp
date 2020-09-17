<!DOCTYPE html>
<!-- Tag Struts -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>Login | PIB Santa Lúcia</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate, private, pre-check=0, post-check=0, max-age=0"/>
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">		
		
 		<!-- JavaScripts -->
		<script src="../resources/diffdash-master/assets/vendor/jquery/jquery.min.js"></script>
		<script src="../resources/diffdash-master/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../resources/frameworks/Bootstrap-3-Dialog/bootstrap-dialog.js"></script>
		<script src="../resources/diffdash-master/assets/vendor/metisMenu/metisMenu.js"></script>
		<script src="../resources/diffdash-master/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script src="../resources/diffdash-master/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="../resources/diffdash-master/assets/vendor/parsleyjs/js/parsley.js"></script>
		<script src="../resources/diffdash-master/assets/scripts/common.js"></script>
		<script src="../resources/js/loading.js"></script>
		<script src="../resources/js/util.js"></script>
		<script src="../resources/js/pages/login.js"></script>
		
		<!-- diffdash-master CSS -->
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../resources/frameworks/Bootstrap-3-Dialog/bootstrap-dialog.css"/>
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/linearicons/style.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/metisMenu/metisMenu.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/vendor/parsleyjs/css/parsley.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/css/main.css">
		<link rel="stylesheet" href="../resources/diffdash-master/assets/css/demo.css">
		<link rel="stylesheet" href="../resources/css/loading.css">
		
		<!-- GOOGLE FONTS -->
		<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
		
		<!-- ICONS -->
		<link rel="apple-touch-icon" sizes="76x76" href="../resources/img/logo-pibsantalucia.png">
		<link rel="icon" type="image/png" sizes="96x96" href="../resources/img/logo-pibsantalucia.png">	
		
	</head>
	<body>		
		<div class="loading"></div>
		<s:hidden id="PIB_SANTA_LUCIA" 	               value="%{getText('PIB.SANTA.LUCIA')}"/>
		<s:hidden id="ERRO_SISTEMA_INDISPONIVEL" 	   value="%{getText('ERRO.SISTEMA.INDISPONIVEL')}"/>
		<div id="wrapper" style="padding-top: 50px;">
			<div class="main-login main-center">
				<div class="auth-box">
					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors" status="pos">
							<div class="alert alert-danger alert-dismissible" role="alert">
								<button type="button" id="buttonActionErrors" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<s:actionerror />
							</div>
						</s:iterator>
					</s:if>
					<div class="content">
						<div class="header">
							<div class="logo text-center"><img src="../resources/img/logo-pibsantalucia.png"></div>
							<p class="lead">Faça login na sua conta</p>
						</div>
						<s:form class="form-auth-small" namespace="Usuario" id="formLogin" name="formLogin" theme="simple" data-parsley-validate="novalidate">
							<div class="form-group">
								<s:textfield class="form-control" required="required" data-parsley-minlength="6" name="login.cpfOrEmail" id="cpfOrEmail" size="80" maxlength="80" theme="simple" placeholder="CPF ou Email"/>
							</div>
							<div class="form-group">
								<s:password class="form-control" required="required" data-parsley-minlength="6" name="login.password" id="password" size="15" maxlength="45" theme="simple" placeholder="Senha"/>
							</div>
							<div class="form-group ">
								<s:submit id="btnEntrar" class="btn btn-primary btn-lg btn-block" value="LOGIN"/>
							</div>
						</s:form>						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
