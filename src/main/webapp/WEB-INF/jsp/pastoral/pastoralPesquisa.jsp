<%@ taglib prefix="s"  uri="/struts-tags" %>

<!-- javascript -->
<script src="../resources/js/pages/pastoral/pastoralPesquisa.js"></script>

<!-- Mensagem -->


<s:form class="ui form" namespace="Pastoral" id="formPastoral" name="formPastoral" 
		theme="simple" data-fv-framework="bootstrap">
	<s:hidden id="pasCodigo" name="pastoralVO.pasCodigo"/>
	<div style="margin-left: 30px; margin-right: 30px;" class="table-responsive">
		<table id="tableListaPastoral" class="table table-striped table-condensed cf">
			<thead>
				<tr style="font-style: italic; font-size: 16px; font-weight: bold;">
	    				<th><strong>Mês</strong></th>
	    				<th style="width: 3%;">Alterar</th>
	    			</tr>
			</thead>
			<tbody class="tbodyTabelaPastoral">
	  			<s:if test="%{listaPastoralVO != null}">
	  				<s:iterator value="listaPastoralVO">
	  					<tr>
               				<td>
               					<label style="margin-top: 7px;">
               						<span><s:property value="pasDtReferencia"/></span>
               					</label>
               				</td>              				
               				<td style='padding-left: 15px; padding-top: 15px;'>
               					<a href='javascript:alterar(<s:property value="pasCodigo"/>);'>
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