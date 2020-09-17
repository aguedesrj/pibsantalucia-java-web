$(document).ready(function() {

});

function remover(pesCodigo) {
	alert("Em desenvolvimento");
};

function alterar(pesCodigo) {
	$("#pesCodigo").val(pesCodigo);
	
	$("#formCongregado").attr("action", "AlteraCongregado.action");
	$("#formCongregado").submit();
}

function changeMembro(pesFglMembro) {
	$("#pesFglMembro").val(pesFglMembro);
	
	$("#formCongregado").attr("action", "PesquisaCongregado.action");
	$("#formCongregado").submit();
}