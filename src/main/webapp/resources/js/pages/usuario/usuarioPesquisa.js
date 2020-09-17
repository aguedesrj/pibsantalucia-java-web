$(document).ready(function() {

});

function remover(usuCodigo) {
	alert("Em desenvolvimento");
};

function alterar(usuCodigo) {
	$("#usuCodigo").val(usuCodigo);
	
	$("#formUsuario").attr("action", "AlteraUsuario.action");
	$("#formUsuario").submit();
}