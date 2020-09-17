<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- javascript -->
<script src="../resources/js/pages/congregado/congregadoPesquisa.js"></script>

<!-- Mensagem -->


<s:form class="ui form" namespace="Congregado" id="formCongregado" name="formCongregado" 
		theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="pesCodigo" name="pessoaVO.pesCodigo"/>
	<s:hidden id="pesFglMembro" name="pessoaVO.pesFglMembro"/>
	<div style="margin-left: 30px; margin-right: 30px;" class="table-responsive">
		<div class="col-sm-7 form-group">
			<label style="font-size: 16px;">Filtro da pesquisa</label>
			<br></br>
			<label>Membro:</label>
			<div>
				<s:if test='pessoaVO.pesFglMembro == "N"'>
					<label class="radio-inline">
						<input id="fglMembro" type="radio" value="S" tabindex="0" onclick="javascript:changeMembro('S');">
		      				Sim
		     				</label> 
		     				<label class="radio-inline">
		      				<input id="fglMembro" type="radio" value="N" tabindex="0" checked="checked" onclick="javascript:changeMembro('N');">
		      				Não
		     				</label> 
				</s:if>
				<s:else>
					<label class="radio-inline">
						<input id="fglMembro" type="radio" value="S" tabindex="0" checked="checked" onclick="javascript:changeMembro('S');">
		      				Sim
		     				</label> 
		     				<label class="radio-inline">
		      				<input id="fglMembro" type="radio" value="N" tabindex="0" onclick="javascript:changeMembro('N');">
		      				Não
		     				</label> 							
				</s:else>			        				
			</div>
		</div>	
		<table id="tableListaCongregados" class="table table-striped table-condensed cf">
			<thead>
				<tr style="font-style: italic; font-size: 16px; font-weight: bold;">
	    				<th><strong>Nome</strong></th>
	    				<th style="width: 3%;">Alterar</th>
	    			</tr>
			</thead>
			<tbody class="tbodyTabelaCliente">
	  			<s:if test="%{listaViewMembroVO != null}">
	  				<s:iterator value="listaViewMembroVO">
	  					<tr>
               				<td>
               					<label style="margin-top: 7px;">
               						<s:if test="%{pesFotoPath==null}">
               							<img style="border-radius: 50%" width="50px" height="50px" src='../resources/photo/congregados/default.png'>
               						</s:if>
               						<s:else>
               							<img style="border-radius: 50%; border-width: 2px; border-color: #4a90e2;" width="50px" height="50px" src='<s:property value="pesFotoPath"/>'>
               						</s:else>
               						<span style='padding-left: 15px;'><s:property value="pesNome"/></span>
               					</label>
               				</td>
               				<td style='padding-left: 15px; padding-top: 30px;'>
               					<a href='javascript:alterar(<s:property value="pesCodigo"/>);'>
               						<span class='glyphicon glyphicon-pencil'></span>
               					</a>
               				</td>							
                			</tr>
	  				</s:iterator>
				</s:if>			
			</tbody>
		</table>
	</div>	
</s:form>