$(document).ready(function() {
	$("#login").focus();
	
	$('#formLogin').bootstrapValidator({
//        message: 'Este valor não é válido.',
        submitButton: '$user_fact_form button[type="submit"]',
        submitHandler: function(validator, form, submitButton) {
        	return false;
        },
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	login: {
                selector: '#login',
                validators: {
                    notEmpty: {
                        message: $("#ERRO_LOGIN_OBRIGATORIO").val()
                    },
                    stringLength: {
                        min: 6,
                        max: 15,	
                        message: $("#ERRO_LOGIN_INVALIDO").val()
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: $("#ERRO_LOGIN_CARACTERES_ESPECIAIS").val()
                    }
                }
            },
            senha: {
            	selector: '#senha',
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
            }            
//            },
//            country: {
//                validators: {
//                    notEmpty: {
//                        message: 'The country is required and can\'t be empty'
//                    }
//                }
//            },
//            acceptTerms: {
//                validators: {
//                    notEmpty: {
//                        message: 'You have to accept the terms and policies'
//                    }
//                }
//            },
//            email: {
//                validators: {
//                    notEmpty: {
//                        message: 'The email address is required and can\'t be empty'
//                    },
//                    emailAddress: {
//                        message: 'The input is not a valid email address'
//                    }
//                }
//            },
//            website: {
//                validators: {
//                    uri: {
//                        allowLocal: true,
//                        message: 'The input is not a valid URL'
//                    }
//                }
//            },
//            phoneNumberUS: {
//                validators: {
//                    phone: {
//                        message: 'The input is not a valid US phone number'
//                    }
//                }
//            },
//            phoneNumberUK: {
//            	validators: {
//            		phone: {
//            			message: 'The input is not a valid UK phone number',
//            			country: 'GB'
//            		}
//            	}
//            },
//            color: {
//                validators: {
//                    color: {
//                        type: ['hex', 'rgb', 'hsl', 'keyword'],
//                        message: 'Must be a valid %s color'
//                    }
//                }
//            },
//            colorAll: {
//                validators: {
//                    color: { }
//                }
//            },
//            zipCode: {
//                validators: {
//                    zipCode: {
//                        country: 'US',
//                        message: 'The input is not a valid US zip code'
//                    }
//                }
//            },
//            password: {
//                validators: {
//                    notEmpty: {
//                        message: 'The password is required and can\'t be empty'
//                    },
//                    identical: {
//                        field: 'confirmPassword',
//                        message: 'The password and its confirm are not the same'
//                    }
//                }
//            },
//            confirmPassword: {
//                validators: {
//                    notEmpty: {
//                        message: 'The confirm password is required and can\'t be empty'
//                    },
//                    identical: {
//                        field: 'password',
//                        message: 'The password and its confirm are not the same'
//                    }
//                }
//            },
//            ages: {
//                validators: {
//                    lessThan: {
//                        value: 100,
//                        inclusive: true,
//                        message: 'The ages has to be less than 100'
//                    },
//                    greaterThan: {
//                        value: 10,
//                        inclusive: false,
//                        message: 'The ages has to be greater than or equals to 10'
//                    }
//                }
//            }
        }
    });
//    .on('success.form.bv', function(e) {
////        e.preventDefault(); // Prevent the form from submitting 
//        alert('I am going to do other things');
//
//        // ... Do whatever you want
//
//        // If you want to submit the form, use the defaultSubmit() method
//        // http://bootstrapvalidator.com/api/#default-submit
////        $('#formLogin').bootstrapValidator('defaultSubmit');
//     });

	$('#btnEntrar').click(function() {
		var bootstrapValidator = $("#formLogin").data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			efetuarLogin();
		} else {
			return;
		}
	});
});

function efetuarLogin() {
	$.ajax({
		url: 'EfetuaLogin',
		data: $('#formLogin').serialize(),
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function() {
			waitingDialog.show('aguarde, efetuando login...', {dialogSize: 'sm', progressType: 'warning'});
		},
		success: function(data, status, request) {
			// verifica se tem erro
			if (status != "success") {
				exibirModal($("#ERRO_SISTEMA_INDISPONIVEL").val())
				return;
			}
			// verifica se tem mensagem de erro do servidor.
			if (data.mensagemUsuarioVO.mensagemErro.length > 0) {
				var mensagem = "";
				for (var i = 0; data.mensagemUsuarioVO.mensagemErro.length > i; i++) {
					mensagem = mensagem + data.mensagemUsuarioVO.mensagemErro[i];
				}
				exibirModal(mensagem);
				return;
			}
			
			if (data.erroVO == null) {
				// home
				$("#formLogin").attr("action", "Home");
				$("#formLogin").submit();
			} else {
				exibirModal(data.erroVO.mensagem);
			}
		},
		error: function (request, error) {
			exibirModal($("#ERRO_SISTEMA_INDISPONIVEL").val());
		}        
	});
}

function exibirModal(mensagem) {
	waitingDialog.hide();
	$("#spanMensagemModal").html(mensagem);
	$('#modalLogin').modal('show');	
}