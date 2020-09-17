$(document).ready(function() {
	
	// obter lista de contatos...
	$.ajax({
		url: 'BuscarContatos.action',
		type: 'POST',
		cache: false,
		dataType: "json",
		success: function(data, status, request) {
			if (status == "success" && data.mensagemUsuarioVO == null) {
				atualizarTabelaContato(data.listaContatoVO);
			}
		}       
	});
	
    $('#formContato').formValidation({
        framework: 'bootstrap',
        fields: {
        	'contatoVO.tipoContatoVO.tpcCodigo': {
        		validators: {
                    greaterThan: {
                        value: 0,
                        inclusive: false,
                        message: $("#ERRO_CADASTRO_CONTATO_TIPO_OBRIGATORIO").val()
                    }
                }
            },
        	'contatoVO.ctoDdd': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_CONTATO_DDD_OBRIGATORIO").val()
                    },
                    stringLength: {
                        min: 2,
                        max: 2,	
                        message: $("#ERRO_CADASTRO_CONTATO_DDD_INVALIDO").val()
                    }
                }
            },            
        	'contatoVO.ctoNumeroTelefone': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_CONTATO_NUMERO_OBRIGATORIO").val()
                    },
                    stringLength: {
                        min: 8,
                        max: 9,	
                        message: $("#ERRO_CADASTRO_CONTATO_NUMERO_INVALIDO").val()
                    }
                }
            },
        	'contatoVO.ctoDescricaoEmail': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_EMAIL_OBRIGATORIO").val()
                    },
                    emailAddress: {
                        message: $("#ERRO_CADASTRO_EMAIL_INVALIDO").val()
                    }
                }
            }
        }
    })
    .on('success.field.fv', function(e, data) {
    	data.fv.disableSubmitButtons(false);
    })
    .on('success.form.fv', function(e) {
    	// cancela submit.
    	e.preventDefault();
    	
    	$("#tpcDescricao").val(document.getElementById('tpcCodigo').
   			options[document.getElementById('tpcCodigo').selectedIndex].innerText);
    	
    	$('#formContato').find('.buttonModalContatoIncluir').attr('disabled', 'disabled');
    	$('#formContato').find('.buttonModalContatoIncluir').removeAttr('disabled');
    	
    	// submeter
       	$.ajax({
       		url: 'IncluiContato.action',
       		data: $('#formContato').serialize(),
       		type: 'POST',
       		cache: false,
       		contentType: "application/x-www-form-urlencoded",
       		beforeSend: function(){
       			waitingDialog.show('aguarde, inserindo dados do contato...', {dialogSize: 'sm', progressType: 'warning'});
       		},
       	    complete:function() {
       	    	waitingDialog.hide();
       	    },
       		success: function(data, status, request) {
       			if (status == "success" && data.mensagemUsuarioVO == null) {
       				atualizarTabelaContato(data.listaContatoVO);
       			} else {
       				exibirModalErroSimples(data.mensagemUsuarioVO.erroVO.mensagem);
       			}
       		},
       		error: function (request, error) {
       			exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
       		}        
       	});
    });
    
    // Exibir Modal Contato
    var validatorFormContato;
	$('#btnIncluirContato').click(function() {
		// reset form
		$('#formContato').formValidation('resetForm', true);
		// default valeu the field tpcCodigo
		$("#tpcCodigo").val(0);
		// clear fields
		limparCampoModalContato();
		// exibir modal dos contatos.
		$('#modalContato').modal('show');
	});
	
	$('#btnContatoCancela').click(function() {
		// reset form
		$('#formContato').formValidation('resetForm', true);
	    // default valeu the field tpcCodigo
		$("#tpcCodigo").val(0);
		// clear fields
		limparCampoModalContato();
	});
});

function onChangeComboTipoTelefone() {
	// limpa os campos...
	limparCampoModalContato();
	
	// obter o valor da combo.
	var valor = $("#tpcCodigo").val();
	$("#divContatoTipoEmail").hide();
	$("#divContatoTipoTelefone").hide();
	if (valor == 1) {
		// Email
		$("#divContatoTipoEmail").show();
		$("#divContatoTipoTelefone").hide();
		$("#ctoDescricaoEmail").focus();
	} else if (valor > 1) {
		// Telefone
		$("#divContatoTipoTelefone").show();
		$("#divContatoTipoEmail").hide();
		$("#ctoDdd").focus();
	}
}

/**
 * 
 * @param listaContatoVO
 */
function atualizarTabelaContato(listaContatoVO) {
//	$("#spanTotalContatos").html("0");
	$("#tableListaContatos tbody").html("");
	if (listaContatoVO != undefined && listaContatoVO.length > 0) {
//		$("#spanTotalContatos").html(listaContatoVO.length);
		var html = "";
		for (var i = 0; listaContatoVO.length > i; i++) {
			var contatoVO = listaContatoVO[i];
			if (contatoVO.excluidoContato == false) {
				html = "";
				html = "<tr>";
				html = html+ "<td>"+contatoVO.tipoContatoVO.tpcDescricao+"</td>";
				if (contatoVO.tipoContatoVO.tpcCodigo == 1) {
					html = html+ "<td>"+contatoVO.ctoDescricaoEmail+"</td>";
				} else {
					html = html+ "<td>("+contatoVO.ctoDdd+") ";
					html = html+ contatoVO.ctoNumeroTelefone+ "</td>";
				}			
				html = html+ "<td style='padding-left: 15px;'><a href='javascript:exibirModalAlterar("+contatoVO.ctoCodigo+");'>	<span class='glyphicon glyphicon-pencil'></span></a></td>";
				html = html+ "<td style='padding-left: 15px;'><a href='javascript:exibirModalExcluir("+contatoVO.ctoCodigo+");'>	<span class='glyphicon glyphicon-remove'></span></a></td>";
				html = html+ "</tr>";
				$("#tableListaContatos tbody").append(html);
			}
		}
		$('#modalContato').modal('hide');
	}
}

/**
 * 
 * @param conCodigo
 */
function exibirModalExcluir(ctoCodigo) {
	BootstrapDialog.show({
        type: BootstrapDialog.TYPE_WARNING,
        title: $("#PIB_SANTA_LUCIA").val(),
        message: 'Confirma excluir o contato selecionado?',
        buttons: [{
            label: 'Fechar',
            action: function(dialogItself){
                dialogItself.close();
            }
        }, {
            label: 'Confirmar',
            cssClass: 'btn-primary',
            action: function(dialogItself) {
            	$("#ctoCodigo").val(ctoCodigo);
			    $.ajax({
					url: 'ExcluiContato.action',
					data: $('#formContato').serialize(),
					type: 'POST',
					cache: false,
					contentType: "application/x-www-form-urlencoded",
					beforeSend: function() {
						dialogItself.close();
						waitingDialog.show('aguarde, removendo contato...', {dialogSize: 'sm', progressType: 'warning'});
					},
					success: function(data, status, request) {
						if (status == "success" && data.mensagemUsuarioVO == null) {
							atualizarTabelaContato(data.listaContatoVO);
						} else {
							exibirModalErroSimples(data.mensagemUsuarioVO.erroVO.mensagem);
						}
						waitingDialog.hide();
					},
					error: function (request, error) {
						exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
					}        
				});
            }
        }]
    });
}

/**
 * 
 * @param conCodigo
 */
function exibirModalAlterar(ctoCodigo) {
	$("#ctoCodigo").val(ctoCodigo);
    $.ajax({
		url: 'AlteraContato.action',
		data: $('#formContato').serialize(),
		type: 'POST',
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		beforeSend: function(){
			waitingDialog.show('aguarde, alterando dados do contato...', {dialogSize: 'sm', progressType: 'warning'});
		},
	    complete:function() {
	    	waitingDialog.hide();
	    },
		success: function(data, status, request) {
			if (status == "success" && data.mensagemUsuarioVO == null) {
				// reset form
				$('#formContato').formValidation('resetForm', true);
				
				$('#tpcCodigo').val(data.contatoVO.tipoContatoVO.tpcCodigo).change();
				$("#ctoDescricaoEmail").val(data.contatoVO.ctoDescricaoEmail);
				$("#ctoDdd").val(data.contatoVO.ctoDdd);
				$("#ctoNumeroTelefone").val(data.contatoVO.ctoNumeroTelefone);
				$("#ctoCodigo").val(data.contatoVO.ctoCodigo);
				$("#divContatoTipoEmail").hide();
				$("#divContatoTipoTelefone").hide();
				if ($("#tpcCodigo").val() == 1) {
					// Email
					$("#divContatoTipoEmail").show();
					$("#divContatoTipoTelefone").hide();
					$("#ctoDescricaoEmail").focus();
				} else if ($("#tpcCodigo").val() > 1) {
					// Telefone
					$("#divContatoTipoTelefone").show();
					$("#divContatoTipoEmail").hide();
					$("#ctoDdd").focus();
				}
				// abrir modal...
				$('#modalContato').modal('show');
			} else {
				exibirModalErroSimples(data.mensagemUsuarioVO.erroVO.mensagem);
			}
		},
		error: function (request, error) {
			exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
		}        
	});	
}

function salvarContato() {
	$("#tpcDescricao").val(document.getElementById('tpcCodigo').
			options[document.getElementById('tpcCodigo').selectedIndex].innerText);
	
	$.ajax({
		url: 'IncluiContato.action',
		data: $('#formContato').serialize(),
		type: 'POST',
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		beforeSend: function(){
			waitingDialog.show('aguarde, inserindo dados do contato...', {dialogSize: 'sm', progressType: 'warning'});
		},
	    complete:function() {
	    	waitingDialog.hide();
	    },
		success: function(data, status, request) {
			if (status == "success" && data.mensagemUsuarioVO == null) {
				atualizarTabelaContato(data.listaContatoVO);
			} else {
				exibirModalErroSimples(data.mensagemUsuarioVO.erroVO.mensagem);
			}
		},
		error: function (request, error) {
			exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
		}        
	}); 
}

function limparCampoModalContato() {
	$("#ctoCodigo").val("");
	$("#tpcDescricao").val("");
	$("#ctoDdd").val("");
	$("#ctoNumeroTelefone").val("");
	$("#ctoDescricaoEmail").val("");
	$("#divContatoTipoEmail").hide();
	$("#divContatoTipoTelefone").hide();
}