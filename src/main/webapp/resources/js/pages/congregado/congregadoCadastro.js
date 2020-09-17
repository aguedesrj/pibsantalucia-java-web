$(document).ready(function() {
	//foco
	$("#pesNome").focus();
	
	// máscaras
	$('#pesCpf').mask('000.000.000-00', {
		reverse: true
	});
	$('#pesDtNascimento').mask("00/00/0000", {
		selectOnFocus: true
	});
	$('#pesDtCasamento').mask("00/00/0000", {
		selectOnFocus: true
	});
	$('#pesDtBatismo').mask("00/00/0000", {
		selectOnFocus: true
	});
	$('#ctoDdd').mask("00", {
		selectOnFocus: true
	});
	$('#endCep').mask("00.000-000", {
		selectOnFocus: true
	});
	
	$('#btnBuscarCep').click(function() {
		buscarEnderecoPorCep();
	});

	// ******************** FIM - SALVAR ********************	
	// validação do formulário principal
    $('#formCongregado').formValidation({
        framework: 'bootstrap',
        excluded: [':disabled'],
        fields: {
        	'pessoaVO.pesNome': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_NOME_OBRIGATORIO").val()
                    },
                    stringLength: {
                        min: 5,
                        max: 80,	
                        message: $("#ERRO_CADASTRO_NOME_INVALIDO").val()
                    }
//                    regexp: {
//                        regexp: /^[a-zA-Z0-9_\.]+$/,
//                        message: 'The username can only consist of alphabetical, number, dot and underscore'
//                    }
                }
            },
        	'pessoaVO.pesDtNascimento': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_DATA_NASCIMENTO_OBRIGATORIO").val()
                    }
                }
            },
        	'pessoaVO.pesSexo': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_SEXO_OBRIGATORIO").val()
                    }
                }
            },
//        	'pessoaVO.pesCpf': {
//        		validators: {
//                    notEmpty: {
//                        message: $("#ERRO_CADASTRO_CPF_OBRIGATORIO").val()
//                    }
//                }
//            },
        	'pessoaVO.pesFglMembro': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_MEMBRO_IGREJA_OBRIGATORIO").val()
                    }
                }
            }
//            'pessoaVO.pesDtBatismo': {
//                validators: {
//                    callback: {
//                        message: $("#ERRO_CADASTRO_DATA_BATISMO_OBRIGATORIO").val(),
//                        callback: function(value, validator, $field) {
//                            var channel = $('#formCongregado').find('[name="pessoaVO.pesFglMembro"]:checked').val();
//                            return (channel !== 'S') ? true : (value !== '');
//                        }
//                    }
//                }
//            }
        }
    })
//    .on('change', '[name="pessoaVO.pesFglMembro"]', function(e) {
//    		$('#formCongregado').formValidation('revalidateField', 'pessoaVO.pesDtBatismo');
//    })
    .on('success.field.fv', function(e, data) {
    		data.fv.disableSubmitButtons(false);
    })    
    .on('err.field.fv', function(e, data) {
        var $invalidFields = data.fv.getInvalidFields().eq(0);
        var $tabPane     = $invalidFields.parents('.tab-pane'),
            invalidTabId = $tabPane.attr('id');

        if (!$tabPane.hasClass('active')) {
            $tabPane.parents('.tab-content')
                    .find('.tab-pane')
                    .each(function(index, tab) {
                        var tabId = $(tab).attr('id'),
                            $li   = $('a[href="#' + tabId + '"][data-toggle="tab"]').parent();
                        if (tabId === invalidTabId) {
                            $(tab).addClass('active');
                            $li.addClass('active');
                        } else {
                            $(tab).removeClass('active');
                            $li.removeClass('active');
                        }
                    });
            $invalidFields.focus();
        }
    })
    .on('success.form.fv', function(e) {
	    	// cancela submit.
	    	e.preventDefault();
    	
    		$.ajax({
       		url: 'SalvaCongregado.action',
       		data: $('#formCongregado').serialize(),
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
//				exibirModalSucessoSimples(data.mensagemUsuarioVO.mensagem);
	    			BootstrapDialog.show({
	    		        type: BootstrapDialog.TYPE_SUCCESS,
	    		        title: $("#PIB_SANTA_LUCIA").val(),
	    		        message: data.mensagemUsuarioVO.mensagem,
	    		        buttons: [{
	    		            label: 'Fechar',
	    		            action: function(dialogItself){
	    		                dialogItself.close();
	    		                window.location = "CadastroCongregado.action";
	    		            }
	    		        }]
	    		    });
				$("#pesCodigo").val(data.pessoaVO.pesCodigo);
       		},
       		error: function (request, error) {
       			exibirModalErroSimples($("#ERRO_SISTEMA_INDISPONIVEL").val());
       		}        
       	});    	
    });
    // ******************** FIM - SALVAR ********************  

	// ******************** FOTO ********************
	$('#imgPhoto').click(function() {
		// limpa os campos...
//		$("#messageErroModalFoto").html("");
//	    $('#image_preview').css("display", "block");
////	    $('#imgPhotoModal').attr('src', $("#pesFotoPath").val());
//	    // abrir modal de foto.
//		$('#modalFoto').modal('show');
	});
	
	$('#btnFotoEnvia').click(function() {
		$("#messageErroModalFoto").html("");
		var fileData = $("#pesFotoPathModal").prop("files")[0];
    		// verifica se tem foto para este membro...
	    	if (fileData == undefined) {
	    		$("#messageErroModalFoto").html("Por favor selecione uma foto que seja do tipo: JPEG, JPG ou PNG.");
	    		return;
	    	}
	    	// continue...
//	    	var formData = new FormData();                 
//	    	formData.append("pessoaVO.pesFotoPath", fileData);
//    	formData.append("pessoaVO.pesCodigo", 101);
	    	
	    	
//	    	$("#formFoto").ajaxForm({
//	            url: 'SalvaPhoto.action',
//	            cache: false,
//	            contentType: false,
//	            processData: false,
//	            type: 'POST',
//				success:function(data) {
//		        	if (data.mensagemUsuarioVO.mensagemErro.length >= 1) {
//		        		$("#messageErroModalFoto").html(data.mensagemUsuarioVO.mensagemErro[0]);
//		        		waitingDialog.hide();
//		        		return;
//		        	}
//		        	$("#pessoaFoto").val(data.pessoaVO.pesFotoPath+"?timestamp="+ new Date().getTime());
//		        	$('#imgPhoto').attr('src', $("#pessoaFoto").val());
//		        	$('#imgPhotoModal').attr('src', $("#pessoaFoto").val());
//		        	waitingDialog.hide();
//		        	
////					$('#fotoSrcColaborador').removeAttr("src");
////					$('.fotoSrcColaborador').removeAttr("src");
////				    $('#fotoSrcColaborador').attr("src",$.url.get('caminhoFotos') + '/' + $('#idColaborador').val());
////				    $('.fotoSrcColaborador').attr("src",$.url.get('caminhoFotos') + '/' + $('#idColaborador').val());
////					$('#divmensagens').html(msgs);
////					$.unblockUI();
//				},
//				dataType : "text"
//			}).submit();	
	    	
//	    $("#formFoto").ajax({
//            url: 'SalvaPhoto.action',
//            cache: false,
//            contentType: false,
//            processData: false,
//            type: 'POST',
//	    		beforeSend: function() {
//	    			waitingDialog.show('aguarde, enviando foto...', {dialogSize: 'sm', progressType: 'warning'});
//	    		},
//	    	    complete:function() {
//	    	    	//
//	    	    },
//	    	    success: function(data) {
//		        	if (data.mensagemUsuarioVO.mensagemErro.length >= 1) {
//		        		$("#messageErroModalFoto").html(data.mensagemUsuarioVO.mensagemErro[0]);
//		        		waitingDialog.hide();
//		        		return;
//		        	}
//		        	$("#pessoaFoto").val(data.pessoaVO.pesFotoPath+"?timestamp="+ new Date().getTime());
//		        	$('#imgPhoto').attr('src', $("#pessoaFoto").val());
//		        	$('#imgPhotoModal').attr('src', $("#pessoaFoto").val());
//		        	waitingDialog.hide();
//	    	    },
//	    		error: function (request, error) {
//	    			waitingDialog.hide();
//	    			exibirModal($("#ERRO_SISTEMA_INDISPONIVEL").val());
//	    		}
//        });		
	});
	 
    $("#pesFotoPathModal").change(function() {
    		$("#messageErroModalFoto").html("");
		var file = this.files[0];
		var imagefile = file.type;
		var match= ["image/jpeg","image/png","image/jpg"];	
		if(!((imagefile==match[0]) || (imagefile==match[1]) || (imagefile==match[2]))) {
			$('#imgPhotoModal').attr('src','noimage.png');
			$("#messageErroModalFoto").html("Por favor selecione uma foto válida. Tipo JPEG, JPG ou PNG.");
			return false;
		}
        else {
            var reader = new FileReader();	
            reader.onload = imageIsLoaded;
            reader.readAsDataURL(this.files[0]);
        }		
    });
    // ******************** FIM - FOTO ********************	
    
	// Novo Cadastro
	$('#btnNovoCadastro').click(function() {
		window.location = "CadastroCongregado.action";
	});
	
//	$('#proDescricao').change(function() {
//		var current = $('#proDescricao').typeahead("getActive");
//		if (current != undefined) {
//			$("#proCodigo").val(current.id);
//		}
//	});
});

function buscarEnderecoPorCep() {
    $.ajax({
		url: 'BuscarCep.action',
		data: $('#formCongregado').serialize(),
		type: 'POST',
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		beforeSend: function(){
			$("#spanLabelBuscarCep").html($("#MSG_BUSCANDO_CEP").val());
		},
		success: function(data, status, request) {
			if (data.viaCepVO != null) {
				$("#endLogradouro").val(data.viaCepVO.logradouro);
				$("#endComplemento").val(data.viaCepVO.complemento);
				$("#endBairro").val(data.viaCepVO.bairro);
				$("#endCidade").val(data.viaCepVO.localidade);
				$("#endEstado").val(data.viaCepVO.estado);
				$("#spanLabelBuscarCep").html($("#MSG_BUSCANDO_CEP_SUCESSO").val());
				$("#endComplemento").focus();
			} else {
				$("#spanLabelBuscarCep").html($("#ERRO_CEP_NAO_ENCONTRADO").val());
			}
		},
		error: function (request, error) {
			$("#spanLabelBuscarCep").html($("#ERRO_BUSCANDO_CEP").val());
		}        
	});	
}

function imageIsLoaded(e) {
    $('#image_preview').css("display", "block");
    $('#imgPhotoModal').attr('src', e.target.result);
	$('#imgPhotoModal').attr('width', '70px');
	$('#imgPhotoModal').attr('height', '70px');
};