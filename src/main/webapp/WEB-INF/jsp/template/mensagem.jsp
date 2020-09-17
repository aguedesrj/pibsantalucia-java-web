<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- Modal -->
<div class="modal fade" id="modalPergunta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        	<h4 class="modal-title">::: PIB Santa Lúcia :::</h4>
	      	</div>
	      	<div class="modal-body">
	        	<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span>  <span id="spanMensagemModal"></span></div>
	      	</div>
	      	<div class="modal-footer">
	        	<button id="buttonModalNao" type="button" class="btn btn-default" data-dismiss="modal">Não</button>
	        	<button id="buttonModalSim" type="button" class="btn btn-primary">Sim</button>
	      	</div>
    	</div>
  	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modalAlert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        	<h4 class="modal-title">::: PIB Santa Lúcia :::</h4>
	      	</div>
	      	<div class="modal-body">
	        	<p><span id="spanMensagemModalAlert"></span></p>
	      	</div>
	      	<div class="modal-footer">
	        	<button id="buttonModalSim" type="button" class="btn btn-primary">OK</button>
	      	</div>
    	</div>
  	</div>
</div>