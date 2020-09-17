<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- css -->
<link href="../resources/css/tabs.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/formRequired.css" rel="stylesheet" type="text/css" />
<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>

<script type="text/javascript" src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>
<script src="../resources/js/tabs.js"></script>
<script src="../resources/js/pages/congregado/congregadoCadastro.js"></script>
<script src="../resources/js/pages/congregado/congregadoCadastroContato.js"></script>

<!-- Mensagem -->
<s:hidden id="ERRO_CADASTRO_CONTATO_TIPO_OBRIGATORIO" 	 value="%{getText('ERRO.CADASTRO.CONTATO.TIPO.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_CONTATO_DDD_OBRIGATORIO" 	 value="%{getText('ERRO.CADASTRO.CONTATO.DDD.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_CONTATO_DDD_INVALIDO" 		 value="%{getText('ERRO.CADASTRO.CONTATO.DDD.INVALIDO')}"/>
<s:hidden id="ERRO_CADASTRO_CONTATO_NUMERO_OBRIGATORIO"  value="%{getText('ERRO.CADASTRO.CONTATO.NUMERO.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_CONTATO_NUMERO_INVALIDO" 	 value="%{getText('ERRO.CADASTRO.CONTATO.NUMERO.INVALIDO')}"/>
<s:hidden id="ERRO_CADASTRO_EMAIL_OBRIGATORIO" 	 		 value="%{getText('ERRO.CADASTRO.EMAIL.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_EMAIL_INVALIDO" 	 	 	 value="%{getText('ERRO.CADASTRO.EMAIL.INVALIDO')}"/>
<s:hidden id="ERRO_CEP_NAO_ENCONTRADO" 	 				 value="%{getText('ERRO.CEP.NAO.ENCONTRADO')}"/>
<s:hidden id="ERRO_BUSCANDO_CEP" 		 				 value="%{getText('ERRO.BUSCANDO.CEP')}"/>
<s:hidden id="ERRO_CADASTRO_NOME_OBRIGATORIO" 		 	 value="%{getText('ERRO.CADASTRO.NOME.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_NOME_INVALIDO" 		 		 value="%{getText('ERRO.CADASTRO.NOME.INVALIDO')}"/>
<s:hidden id="ERRO_CADASTRO_DATA_NASCIMENTO_OBRIGATORIO" value="%{getText('ERRO.CADASTRO.DATA.NASCIMENTO.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_SEXO_OBRIGATORIO" 		 	 value="%{getText('ERRO.CADASTRO.SEXO.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_CPF_OBRIGATORIO" 		 	 value="%{getText('ERRO.CADASTRO.CPF.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_MEMBRO_IGREJA_OBRIGATORIO" 	 value="%{getText('ERRO.CADASTRO.MEMBRO.IGREJA.OBRIGATORIO')}"/>
<s:hidden id="ERRO_CADASTRO_DATA_BATISMO_OBRIGATORIO" 	 value="%{getText('ERRO.CADASTRO.DATA.BATISMO.OBRIGATORIO')}"/>
<s:hidden id="MSG_BUSCANDO_CEP" 		 				 value="%{getText('MSG.BUSCANDO.CEP')}"/>
<s:hidden id="MSG_BUSCANDO_CEP_SUCESSO"  				 value="%{getText('MSG.BUSCANDO.CEP.SUCESSO')}"/>
<s:hidden id="MSG_CADASTRO_AGUARDE"  				 	 value="%{getText('MSG.CADASTRO.AGUARDE')}"/>

<s:form class="ui form" namespace="Congregado" id="formCongregado" name="formCongregado" 
		theme="simple" enctype="multipart/form-data" data-fv-framework="bootstrap">
	<s:hidden id="pesCodigo"   name="pessoaVO.pesCodigo"/>
	<s:hidden id="endCodigo"   name="pessoaVO.enderecoVO.endCodigo"/>
	<s:hidden id="pesFotoPath" name="pessoaVO.pesFotoPath"/>
	<s:hidden id="novaFoto"    name="pessoaVO.novaFoto"/>
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tabDadosPessoais" data-toggle="tab">Dados Pessoais</a></li>
          	<li><a href="#tabMaisInformacoes" data-toggle="tab">Mais informações</a></li>
          	<li><a href="#tabEndereco" data-toggle="tab">Endereço</a></li>
          	<li><a href="#tabContatos" data-toggle="tab">Contatos</a></li>
       	</ul>
        <div class="tab-content">
        	<div class="tab-pane active" id="tabDadosPessoais">
            	<div style="margin-top: 10px;">
 					<div class="col-md-10">
						<div class="row">
							<div class="col-md-7 form-group">
								<label>Nome</label>
								<div class="required-field-block">
									<s:textfield class="form-control" name="pessoaVO.pesNome" id="pesNome" maxlength="80" theme="simple" placeholder="Nome completo da pessoa"/>
				            		</div>							
							</div>
							<div class="col-md-3 form-group">
								<label>Data de Nascimento</label>
								<div class="required-field-block">
									<s:textfield class="form-control" name="pessoaVO.pesDtNascimento" id="pesDtNascimento" maxlength="10" theme="simple" placeholder="Data de nascimento" data-mask="00/00/0000" data-mask-reverse="true"/>
				            		</div>							
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Sexo</label>
								<div>
									<s:if test='pessoaVO.pesSexo == "F"'>
										<label class="radio-inline">
											<input id="pesSexo" type="radio" value="F" name="pessoaVO.pesSexo" tabindex="0" checked="checked">
					        				Feminino
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesSexo" type="radio" value="M" name="pessoaVO.pesSexo" tabindex="0">
					        				Masculino
				        				</label>
									</s:if>
									<s:elseif test='pessoaVO.pesSexo == "M"'>
										<label class="radio-inline">
											<input id="pesSexo" type="radio" value="F" name="pessoaVO.pesSexo" tabindex="0">
					        				Feminino
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesSexo" type="radio" value="M" name="pessoaVO.pesSexo" tabindex="0" checked="checked">
					        				Masculino
				        				</label> 								
									</s:elseif>
									<s:else>
										<label class="radio-inline">
											<input id="pesSexo" type="radio" value="F" name="pessoaVO.pesSexo" tabindex="0">
					        				Feminino
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesSexo" type="radio" value="M" name="pessoaVO.pesSexo" tabindex="0">
					        				Masculino
				        				</label> 							
									</s:else>
								</div>
							</div>	
							<div class="col-sm-3 form-group">
								<label>CPF</label>
								<div class="required-field-block">
									<s:textfield style="width: 150px;" class="form-control" name="pessoaVO.pesCpf" id="pesCpf" maxlength="12" theme="simple" placeholder="Número do CPF"/>
				            		</div>							
							</div>	
							<div class="col-sm-3 form-group">
								<label>Tipo Sanguineo</label>
								<s:textfield style="width: 150px;" class="form-control" name="pessoaVO.pesTipoSanguineo" id="pesTipoSanguineo" maxlength="5" theme="simple" placeholder="Tipo Sanguineo"/>
							</div>		
						</div>
                    </div>
                    <div class="col-md-0">
                        <div class="row">
                            <div id="image_preview" style="margin-top: 20px;">
                            	<s:if test="%{pessoaVO.pesFotoPath==null}">
                            		<img id="imgPhoto" style="border-radius: 50%" width="100px" height="100px;" src='../resources/photo/congregados/default.png'>
               					</s:if>
               					<s:else>
               						<img id="imgPhoto" style="border-radius: 50%; border-width: 2px; border-color: #4a90e2;" width="100px" height="100px;" src='<s:property value="pessoaVO.pesFotoPath"/>'>
               					</s:else>
        					</div>
                        </div>
                    </div>				           	
 					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Naturalidade</label>
								<s:textfield class="form-control" name="pessoaVO.pesNaturalidade" id="pesNaturalidade" maxlength="45" theme="simple" placeholder="Naturalidade da pessoa"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Nacionalidade</label>
								<s:textfield class="form-control" name="pessoaVO.pesNacionalidade" id="pesNacionalidade" maxlength="45" theme="simple" placeholder="Nacionalidade da pessoa"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Profissão</label>
								<s:textfield class="form-control" name="pessoaVO.pesProfissao" id="pesProfissao" maxlength="80" theme="simple" placeholder="Profissão da pessoa"/>								
							</div>							
						</div>						
					</div>           	
            	</div>
			</div>
            <!-- MAIS INFORMAÇÕES -->
            <div class="tab-pane" id="tabMaisInformacoes">
            	<div style="margin-top: 10px;">
					<div class="col-md-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Nome da Mãe</label>
								<s:textfield class="form-control" name="pessoaVO.pesNomeMae" id="pesNomeMae" maxlength="80" theme="simple" placeholder="Nome completo da mãe"/>
							</div>
							<div class="col-sm-6 form-group">
								<label>Nome do Pai</label>
								<s:textfield class="form-control" name="pessoaVO.pesNomePai" id="pesNomePai" maxlength="80" theme="simple" placeholder="Nome completo do pai"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group" style="width: 30%">
								<label>Data de Casamento</label>
								<s:textfield style="width: 150px;" class="form-control" name="pessoaVO.pesDtCasamento" id="pesDtCasamento" maxlength="10" theme="simple" placeholder="Data de casamento" data-mask="00/00/0000" data-mask-reverse="true"/>								
							</div>
							<div class="col-sm-4 form-group" style="width: 20%">
								<label>Membro da Igreja?</label>
								<div>
									<s:if test='pessoaVO.pesFglMembro == "S"'>
										<label class="radio-inline">
											<input id="pesFglMembro" type="radio" value="S" name="pessoaVO.pesFglMembro" tabindex="0" checked="checked">
					        				Sim
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesFglMembro" type="radio" value="N" name="pessoaVO.pesFglMembro" tabindex="0">
					        				Não
				        				</label>
									</s:if>
									<s:elseif test='pessoaVO.pesFglMembro == "N"'>
										<label class="radio-inline">
											<input id="pesFglMembro" type="radio" value="S" name="pessoaVO.pesFglMembro" tabindex="0">
					        				Sim
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesFglMembro" type="radio" value="N" name="pessoaVO.pesFglMembro" tabindex="0" checked="checked">
					        				Não
				        				</label> 								
									</s:elseif>
									<s:else>
										<label class="radio-inline">
											<input id="pesFglMembro" type="radio" value="S" name="pessoaVO.pesFglMembro" tabindex="0">
					        				Sim
				        				</label> 
				        				<label class="radio-inline">
					        				<input id="pesFglMembro" type="radio" value="N" name="pessoaVO.pesFglMembro" tabindex="0">
					        				Não
				        				</label> 							
									</s:else>
								</div>				      									
							</div>
							<div class="col-sm-4 form-group">
								<label>Data de Batismo</label>
								<s:textfield style="width: 150px;" class="form-control" name="pessoaVO.pesDtBatismo" id="pesDtBatismo" maxlength="10" theme="simple" placeholder="Data de batismo" data-mask="00/00/0000" data-mask-reverse="true"/>							
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 form-group">
								<label>Observação</label>
								<s:textarea class="form-control" name="pessoaVO.observacao" id="pesObservacao" maxlength="255" cols="80" rows="2" theme="simple" placeholder="Caso tenha informações relevante da pessoa descreva aqui."/>
							</div>
						</div>
            		</div>
				</div>
    		</div>			
			<!-- ENDEREÇO -->
            <div class="tab-pane" id="tabEndereco">
            	<div style="margin-top: 10px;">
 					<div class="col-md-12">
						<div class="row">
							<div class="col-md-4 form-group">
								<label>CEP</label>	
 								<div class="input-group" id="adv-search">
                					<s:textfield class="form-control" name="pessoaVO.enderecoVO.endCep" id="endCep" size="15" maxlength="9" theme="simple" placeholder="Número do CEP"/>
                					<div class="input-group-btn">
                    					<div class="btn-group" role="group">
                        					<button id="btnBuscarCep" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    					</div>
                					</div>
            					</div>							
							</div>
							<div class="col-md-4 form-group" style="margin-top: 30px;">
								<span id="spanLabelBuscarCep"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 form-group">
								<label>Logradouro</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endLogradouro" id="endLogradouro" maxlength="80" theme="simple" placeholder="Rua, avenida etc"/>
							</div>
							<div class="col-md-4 form-group">
								<label>Complemento</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endComplemento" id="endComplemento" maxlength="45" theme="simple" placeholder="Complemento..."/>
							</div>
							<div class="col-md-4 form-group">
								<label>Número</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endNumero" id="endNumero" maxlength="10" theme="simple" placeholder="Número"/>								
							</div>							
						</div>	
						<div class="row">
							<div class="col-md-4 form-group">
								<label>Bairro</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endBairro" id="endBairro" maxlength="80" theme="simple" placeholder="Bairro"/>
							</div>
							<div class="col-md-4 form-group">
								<label>Cidade</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endCidade" id="endCidade" maxlength="80" theme="simple" placeholder="Cidade"/>
							</div>
							<div class="col-md-4 form-group">
								<label>Estado</label>
								<s:textfield class="form-control" name="pessoaVO.enderecoVO.endEstado" id="endEstado" maxlength="45" theme="simple" placeholder="Estado"/>
							</div>							
						</div>											
					</div>
            	</div>
            </div>
            <!-- CONTATOS -->
            <div class="tab-pane" id="tabContatos">
            	<div style="margin-top: 10px;">
					<div class="col-md-12">
        				<div class="table-responsive">
              				<table id="tableListaContatos" class="table table-striped table-condensed cf">
								<thead>
									<tr style="font-style: italic; font-weight: bold;">
								    		<th style="width: 30%;">Tipo de Contato</th>
								    		<th style="width: 30%;">Contato</th>
								    		<th style="width: 3%;">Alterar</th>
								      	<th style="width: 3%;">Excluir</th>
							    		</tr>
								</thead>
    							<tbody></tbody>
							</table>
							<button id="btnIncluirContato" style="margin-top: 10px; float: right;" type="button" class="btn btn-primary">Incluir novo contato</button>
            			</div>
            		</div>
				</div>
    		</div>
    	</div>
    	<button style="margin-top: 10px; margin-left: 15px;" type="submit" class="btn btn-primary">Salvar</button>
    	<button id="btnNovoCadastro" style="margin-top: 10px; margin-left: 15px;" type="button" class="btn btn-primary">Novo Cadastro</button>
    </div>	
</s:form>

<!-- Modal Foto -->
<div class="modal fade" id="modalFoto" tabindex="-1" role="dialog" aria-labelledby="myModalFoto" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
    		<s:form class="ui form" namespace="Congregado" id="formFoto" name="formFoto" theme="simple" enctype="multipart/form-data">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
		        	<h4 class="modal-title">::: PIB Santa Lúcia :::</h4>
		      	</div>
		      	<div class="modal-body">
					<div id="image_preview">
	        			<img id="imgPhotoModal" width="100px" height="100px;" alt="Foto..." src="<s:property value="pessoaVO.pesFotoPath"/>">
	        		</div>
	        		<s:file name="pessoaVO.fotoUpload" id="pesFotoPathModal" style="margin-top: 10px;"></s:file>
	        		<div id="messageErroModalFoto" style="color: red; margin-top: 20px;"></div>
		      	</div>
		      	<div class="modal-footer">
		        	<button id="btnFotoCancela" type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        	<button id="btnFotoEnvia" type="button" class="btn btn-primary">Enviar Foto</button>
		      	</div>
	      	</s:form>
    	</div>
  	</div>
</div>

<!-- Modal Contato -->
<div class="modal fade" id="modalContato" tabindex="-1" role="dialog" aria-labelledby="myModalContato" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
    		<s:form class="ui form" namespace="Congregado" id="formContato" name="formContato" theme="simple">
    			<s:hidden id="ctoCodigo" name="contatoVO.ctoCodigo"/>
				<s:hidden id="novoContato" name="contatoVO.novoContato"/>
				<s:hidden id="tpcDescricao" name="contatoVO.tipoContatoVO.tpcDescricao"/>
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
		        	<h4 class="modal-title">::: PIB Santa Lúcia :::</h4>
		      	</div>
				<div style="margin-left: 5px; margin-top: 5px;" class="row">
					<div class="col-md-4 form-group">
						<label>Tipo de Contatos</label>
							<s:select class="form-control" headerKey="0"
							headerValue="::: Selecione :::"
							list="listaTipoContatoVO" listKey="tpcCodigo" 
							listValue="tpcDescricao" name="contatoVO.tipoContatoVO.tpcCodigo"
							id="tpcCodigo" onchange="javascript:onChangeComboTipoTelefone();">
						</s:select>	
					</div>							
					<div id="divContatoTipoTelefone" style="display: none;">
						<div class="col-md-3 form-group">
							<label>DDD</label>
							<s:textfield class="form-control" name="contatoVO.ctoDdd" id="ctoDdd" maxlength="2" theme="simple" placeholder="DDD"/>
						</div>
						<div class="col-md-3 form-group">
							<label>Telefone</label>
							<s:textfield class="form-control" name="contatoVO.ctoNumeroTelefone" id="ctoNumeroTelefone" maxlength="9" theme="simple" placeholder="Número"/>								
						</div>									
					</div>
					<div id="divContatoTipoEmail" style="display: none;">
						<div class="col-md-7 form-group">
							<label>E-mail</label>
							<s:textfield class="form-control" name="contatoVO.ctoDescricaoEmail" id="ctoDescricaoEmail" maxlength="60" theme="simple" placeholder="E-mail..."/>
						</div>
					</div>							
				</div>
		      	<div class="modal-footer">
		        	<button id="btnContatoCancela" type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        	<button type="submit" class="btn btn-primary buttonModalContatoIncluir">Incluir</button>
		      	</div>
	      	</s:form>
    	</div>
  	</div>
</div>