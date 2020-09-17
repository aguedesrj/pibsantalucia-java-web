<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- css -->
<link href="../resources/css/tabs.css" rel="stylesheet" type="text/css" />
<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>

<!-- javascript -->
<script src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>
<script src="../resources/js/pages/congregado/congregadoDesligamento.js"></script>

<!-- Mensagem -->
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO" 	  value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_DESLIGAMENTO_DATA_OBRIGATORIO"  value="%{getText('ERRO.DESLIGAMENTO.DATA.OBRIGATORIO')}"/>

<s:form class="ui form" namespace="CongregadoDesligamento" id="formCongregado" name="formCongregado" theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="pesCodigo" name="desligamentoVO.viewMembroVO.pesCodigo"/>
	<div class="col-sm-12">
		<div class="row">
			<div class="col-md-7 form-group">
				<label>Nome</label>
				<s:textfield class="form-control" name="desligamentoVO.viewMembroVO.pesNome" id="pesNome" maxlength="80" theme="simple" placeholder="Nome completo da pessoa"/>
			</div>
			<div class="col-sm-3 form-group">
				<label>Data do desligamento</label>
				<s:textfield class="form-control" name="desligamentoVO.desDtDesligamento" id="desDtDesligamento" maxlength="10" theme="simple" placeholder="Data do desligamento" data-mask="00/00/0000" data-mask-reverse="true"/>
			</div>			
		</div>
		<div class="row">
			<div class="col-sm-12 form-group">
				<label>Obervação</label>
				<s:textarea class="form-control" name="desligamentoVO.desObservacao" id="desObservacao" maxlength="500" cols="80" rows="2" theme="simple" placeholder="Caso tenha informações relevante escreva aqui."/>
			</div>
		</div>
		<button style="margin-top: 10px;" type="submit" class="btn btn-primary">Salvar</button>					
	</div>	
</s:form>