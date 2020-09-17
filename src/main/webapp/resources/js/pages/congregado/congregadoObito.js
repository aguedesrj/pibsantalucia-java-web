$(document).ready(function() {
	//foco
	$("#pesNome").focus();
	
	// máscaras
	$('#obiDtObito').mask('00/00/0000', {
		reverse: true
	});
	
	$('#pesNome').typeahead({
        autoSelect: true,
        minLength: 3,
        delay: 200,
        method: "post",
        source: function (query, process) {
    			$.ajax({
           		url: 'ObitoPesquisaNome.action',
           		data: $('#formCongregado').serialize(),
           		type: 'POST',
           		cache: false,
           		contentType: "application/x-www-form-urlencoded",
           		success: function(data, status, request) {
	    				var listaCongregados = [];
	    				$.each(data.listaViewMembroVO, function(i, viewMembroVO) {
	    					listaCongregados.push({id: viewMembroVO.pesCodigo, name: viewMembroVO.pesNome});
	    				});
	    				return process(listaCongregados);
           		},
           		error: function (request, error) {
           			// verifica se tem erro
           		}        
           	});
        }
    });	
	
	$('#pesNome').change(function() {
		var current = $('#pesNome').typeahead("getActive");
		if (current != undefined) {
			$("#pesCodigo").val(current.id);
		}
	});
	
    $('#formCongregado').formValidation({
        framework: 'bootstrap',
        fields: {
        		'obitoVO.pesNome': {
	    			validators: {
	                notEmpty: {
	                    message: $("#ERRO_CADASTRO_NOME_OBRIGATORIO").val()
	                }
	            }
        	},
        		'obitoVO.obiDtObito': {
        			validators: {
                    notEmpty: {
                        message: $("#ERRO_OBITO_DATA_OBRIGATORIO").val()
                    }
                }
            },
        		'obitoVO.obiCausa': {
        			validators: {
                    notEmpty: {
                        message: $("#ERRO_OBITO_CAUSA_OBRIGATORIO").val()
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
       		url: 'SalvaObito.action',
       		data: $('#formCongregado').serialize(),
       		type: 'POST',
       		cache: false,
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
	    		                window.location = "Obito.action";
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