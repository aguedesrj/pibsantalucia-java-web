<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- javascript -->
<script src="../resources/js/pages/usuario/usuarioPesquisa.js"></script>

<!-- Mensagem -->


<s:form class="ui form" namespace="Usuario" id="formUsuario" name="formUsuario" 
		theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="usuCodigo" name="viewUsuarioVO.usuCodigo"/>
	<div style="margin-left: 30px; margin-right: 30px;" class="table-responsive">
		<table id="tableListaUsuarios" class="table table-striped table-condensed cf">
			<thead>
				<tr style="font-style: italic; font-size: 16px; font-weight: bold;">
	    				<th><strong>Nome</strong></th>
	    				<th style="width: 3%;">Alterar</th>
	    			</tr>
			</thead>
			<tbody class="tbodyTabelaUsuario">
	  			<s:if test="%{listaViewUsuarioVO != null}">
	  				<s:iterator value="listaViewUsuarioVO">
	  					<tr>
                				<td>
                					<label style="margin-top: 7px;">
                						<s:property value="usuNome"/>
                					</label>
                				</td>
                				<td style='padding-left: 15px; padding-top: 10px;'>
                					<a style="margin-top: 7px;" href='javascript:alterar(<s:property value="usuCodigo"/>);'>
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