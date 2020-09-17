<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- css -->
<link href="../resources/css/tabs.css" rel="stylesheet" type="text/css" />
<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>

<!-- javascript -->
<script src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>
<script src="../resources/js/pages/congregado/congregadoTransferencia.js"></script>

<!-- Mensagem -->
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO" 	  		 value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_TRANSFERENCIA_DATA_OBRIGATORIO"  		 value="%{getText('ERRO.TRANSFERENCIA.DATA.OBRIGATORIO')}"/>
<s:hidden id="ERRO_TRANSFERENCIA_MOTIVO_OBRIGATORIO" 		 value="%{getText('ERRO.TRANSFERENCIA.MOTIVO.OBRIGATORIO')}"/>
<s:hidden id="ERRO_TRANSFERENCIA_STATUS_OBRIGATORIO" 		 value="%{getText('ERRO.TRANSFERENCIA.STATUS.OBRIGATORIO')}"/>
<s:hidden id="ERRO_TRANSFERENCIA_NOME_IGREJA_OBRIGATORIO" value="%{getText('ERRO.TRANSFERENCIA.NOME.IGREJA.OBRIGATORIO')}"/>

<s:form class="ui form" namespace="CongregadoTransferencia" id="formCongregado" name="formCongregado" theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="pesCodigo" name="transferenciaVO.viewMembroVO.pesCodigo"/>
	<div class="col-sm-12">
		<div class="row">
			<div class="col-md-7 form-group">
				<label>Nome</label>
				<s:textfield class="form-control" name="transferenciaVO.viewMembroVO.pesNome" id="pesNome" maxlength="80" theme="simple" placeholder="Nome completo da pessoa"/>
			</div>
			<div class="col-sm-4 form-group" style="width: 30%">
				<label>Data da transferência</label>
				<s:textfield class="form-control" name="transferenciaVO.traDtTransferencia" id="traDtTransferencia" maxlength="10" theme="simple" placeholder="Data do transferência" data-mask="00/00/0000" data-mask-reverse="true"/>
			</div>			
		</div>
		<div class="row">
			<div class="col-sm-7 form-group">
				<label>Motivo</label>
				<s:textfield class="form-control" name="transferenciaVO.traMotivo" id="traMotivo" maxlength="80" theme="simple" placeholder="Motivo da transferência"/>
			</div>
			<div class="col-sm-4 form-group">
				<label>Status da transferência</label>
				<div>
					<label class="radio-inline">
						<input id="traStatus" type="radio" value="E" name="transferenciaVO.traStatus" tabindex="0">
	        				Entrada
	       				</label> 
	       				<label class="radio-inline">
	        				<input id="traStatus" type="radio" value="S" name="transferenciaVO.traStatus" tabindex="0">
	        				Saída
	       				</label>
				</div>				      									
			</div>										
		</div>
		<div class="row">
			<div class="col-sm-7 form-group">
				<label>Igreja</label>
				<s:textfield class="form-control" name="transferenciaVO.traNomeIgreja" id="traNomeIgreja" maxlength="80" theme="simple" placeholder="Igreja da onde está vindo"/>
			</div>							
		</div>			
		<div class="row">
			<div class="col-sm-12 form-group">
				<label>Obervação</label>
				<s:textarea class="form-control" name="transferenciaVO.traObservacao" id="traObservacao" maxlength="255" cols="80" rows="2" theme="simple" placeholder="Caso tenha informações relevante escreva aqui."/>
			</div>
		</div>
		<button style="margin-top: 10px;" type="submit" class="btn btn-primary">Salvar</button>					
	</div>
</s:form>
