$(document).ready(function() {

});

function novaFuncionalidade() {
	alert('Em desenvolvimento...');
}

function actionSair() {
	BootstrapDialog.show({
        type: BootstrapDialog.TYPE_WARNING,
        title: $("#PIB_SANTA_LUCIA").val(),
        message: $("#MSG_SAIR_SISTEMA").val(),
        buttons: [{
            label: 'Fechar',
            action: function(dialogItself){
                dialogItself.close();
            }
        }, {
            label: 'Confirmar',
            cssClass: 'btn-primary',
            action: function(dialogItself) {
            	dialogItself.close();
            	window.location = "Logout.action";
            }
        }]
    });
}