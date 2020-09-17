$(document).ready(function() {

});

function alterar(pasCodigo) {
	$("#pasCodigo").val(pasCodigo);
	
	$("#formPastoral").attr("action", "AlteraPastoral.action");
	$("#formPastoral").submit();
}