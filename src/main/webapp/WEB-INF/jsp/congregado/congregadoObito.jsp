<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- css -->
<link href="../resources/css/tabs.css" rel="stylesheet" type="text/css" />
<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>

<!-- javascript -->
<script src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>
<script src="../resources/js/pages/congregado/congregadoObito.js"></script>

<!-- Mensagem -->
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO" value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_OBITO_DATA_OBRIGATORIO" 	 value="%{getText('ERRO.OBITO.DATA.OBRIGATORIO')}"/>
<s:hidden id="ERRO_OBITO_CAUSA_OBRIGATORIO" 	 value="%{getText('ERRO.OBITO.CAUSA.OBRIGATORIO')}"/>

<s:form class="ui form" namespace="CongregadoObito" id="formCongregado" name="formCongregado" theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="pesCodigo" name="obitoVO.pesCodigo"/>
	<div class="col-sm-12">
		<div class="row">
			<div class="col-md-7 form-group">
				<label>Nome</label>
				<s:textfield class="form-control" name="obitoVO.pesNome" id="pesNome" maxlength="80" theme="simple" placeholder="Nome completo da pessoa"/>
			</div>
			<div class="col-sm-3 form-group">
				<label>Data do óbito</label>
				<s:textfield class="form-control" name="obitoVO.obiDtObito" id="obiDtObito" maxlength="10" theme="simple" placeholder="Data do óbito" data-mask="00/00/0000" data-mask-reverse="true"/>
			</div>			
		</div>
		<div class="row">
			<div class="col-sm-7 form-group">
				<label>Causa</label>
				<s:textfield class="form-control" name="obitoVO.obiCausa" id="obiCausa" maxlength="80" theme="simple" placeholder="Causa do óbito"/>
			</div>							
		</div>	
		<div class="row">
			<div class="col-sm-12 form-group">
				<label>Obervação</label>
				<s:textarea class="form-control" name="obitoVO.obiObservacao" id="obiObservacao" maxlength="255" cols="80" rows="2" theme="simple" placeholder="Caso tenha informações relevante escreva aqui."/>
			</div>
		</div>
		<button style="margin-top: 10px;" type="submit" class="btn btn-primary">Salvar</button>					
	</div>	
</s:form>