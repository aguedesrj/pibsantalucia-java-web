$(document).ready(function() {
    $('.required-icon').tooltip({
        placement: 'left',
        title: 'Required field'
    });
    
	// Novo Cadastro
	$('#btnNovoCadastro').click(function() {
		window.location = "CadastroUsuario.action";
	});    
    
	//foco
	$("#usuNome").focus();
	
    $('#formUsuario').formValidation({
        framework: 'bootstrap',
        fields: {
        		'viewUsuarioVO.usuNome': {
        			validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_NOME_OBRIGATORIO").val()
                    }
                }
        		},
            	'viewUsuarioVO.usuEmail': {
            		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_EMAIL_OBRIGATORIO").val()
                    },
                    emailAddress: {
                        message: $("#ERRO_CADASTRO_EMAIL_INVALIDO").val()
                    }
                }
            }, 
	        	'viewUsuarioVO.usuStatus': {
	        		validators: {
                    notEmpty: {
                        message: $("#ERRO_USUARIO_STATUS_OBRIGATORIO").val()
                    }
	            }
	        },            
        		'viewUsuarioVO.usuLogin': {
        			validators: {
                    notEmpty: {
                        message: $("#ERRO_LOGIN_OBRIGATORIO").val()
                    },
                    stringLength: {
                        min: 6,
                        max: 15,	
                        message: $("#ERRO_LOGIN_INVALIDO").val()
                    }
                }
            },
        		'viewUsuarioVO.usuSenha': {
        			validators: {
                    notEmpty: {
                        message: $("#ERRO_SENHA_OBRIGATORIA").val()
                    },
                    stringLength: {
                        min: 6,
                        max: 15,	
                        message: $("#ERRO_SENHA_INVALIDA").val()
                    }
                }
            },
	    		'viewUsuarioVO.usuConfirmacaoSenha': {
	    			validators: {
	                notEmpty: {
	                    message: $("#ERRO_SENHA_OBRIGATORIA").val()
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
    	
    		$.ajax({
       		url: 'SalvaUsuario.action',
       		data: $('#formUsuario').serialize(),
       		type: 'POST',
       		cache: false,
       		contentType: "application/x-www-form-urlencoded",
       		beforeSend: function(){
       			waitingDialog.show($("#MSG_CADASTRO_AGUARDE").val(), {dialogSize: 'sm', progressType: 'warning'});
       		},
       	    complete:function() {
       	    		waitingDialog.hide();
       	    },
       		success: function(data, status, request) {
	    			// verifica se tem erro
	    			if (status != "success") {
	    				exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
	    				return;
	    			}
	    			// verifica se tem mensagem de erro do servidor.
	    			if (data.mensagemUsuarioVO.mensagemErro.length > 0) {
	    				var mensagem = "";
	    				for (var i = 0; data.mensagemUsuarioVO.mensagemErro.length > i; i++) {
	    					mensagem = mensagem + data.mensagemUsuarioVO.mensagemErro[i];
	    				}
	    				exibirModalErroSimples(mensagem);
	    				return;
	    			}
				// sucesso
	    			BootstrapDialog.show({
	    		        type: BootstrapDialog.TYPE_SUCCESS,
	    		        title: $("#PIB_SANTA_LUCIA").val(),
	    		        message: data.mensagemUsuarioVO.mensagem,
	    		        closable: false,
	    		        buttons: [{
	    		            label: 'Fechar',
	    		            action: function(dialogItself){
	    		                dialogItself.close();
	    		                window.location = "PesquisaUsuario";
	    		            }
	    		        }]
	    		    });
       		},
       		error: function (request, error) {
       			exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
       		}        
       	});    	
    });	
});