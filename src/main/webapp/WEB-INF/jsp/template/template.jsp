<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<head>
		<link rel="shortcut icon" href="../img/logo.png" type="image/png" />
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate, private, pre-check=0, post-check=0, max-age=0"/>
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		
		<!-- CSS -->
		<link href="../resources/frameworks/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="../resources/frameworks/Bootstrap-3-Dialog/bootstrap-dialog.css" rel="stylesheet" type="text/css" />
		
		<!-- JavaScripts -->
		<script src="../resources/js/jquery-3.1.1.min.js"></script>
		<script src="../resources/frameworks/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script src="../resources/frameworks/Bootstrap-3-Dialog/bootstrap-dialog.js"></script>
		
		<script src="../resources/js/jquery.mask.min.js"></script>
		<script src="../resources/js/jquery.form.js"></script>
		<script src="../resources/js/pages/template.js"></script>
		<script src="../resources/js/loading.js"></script>
		<script src="../resources/js/util.js"></script>
		<script src="../resources/js/jquery.blockUI.js"></script>
		<script src="../resources/js/pages/mensagem.js"></script>
		
		<!-- Website Font style -->
		<link rel="stylesheet" href="../resources/frameworks/font-awesome-4.7.0/css/font-awesome.min.css">
		
		<!-- Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
		
		<!-- Navbar -->
	   	<link rel="stylesheet" href="../resources/navbar/navbar.css"/>
	   	<script type="text/javascript" src="../resources/navbar/navbar.js"></script>
		
		<link rel="stylesheet" href="../resources/frameworks/font-awesome-4.7.0/css/font-awesome.min.css">
		<script src="../resources/js/pages/mensagem.js"></script>
		
		<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
	</head>
<body style="font-family: 'Roboto Condensed', sans-serif;">
	<s:hidden id="PIB_SANTA_LUCIA" 			 value="%{getText('PIB.SANTA.LUCIA')}"/>
	<s:hidden id="ERRO_SISTEMA_INDISPONIVEL" value="%{getText('ERRO.SISTEMA.INDISPONIVEL')}"/>
	
	<!-- JSTL -->
	<div class="main ui container" style="width: 900px;">
		<div id="tilesTopo"><tiles:insertAttribute name="topo"/></div>
		<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
		<div id="tilesMensagem"><tiles:insertAttribute name="mensagem"/></div>
		<div class="panel panel-default" style="margin-left: 10px; margin-right: 10px;">
  			<div class="panel-heading">
    				<h3 class="panel-title"><tiles:insertAttribute name="titlepanel"></tiles:insertAttribute></h3>
  			</div>
  			<div class="panel-body">
				<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>
			</div>
		</div>	
		<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
	</div>
</body>
</html>