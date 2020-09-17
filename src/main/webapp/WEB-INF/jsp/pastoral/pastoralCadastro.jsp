<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/monokai-sublime.min.css" />
    <link rel="stylesheet" href="https:/cdn.quilljs.com/1.3.7/quill.snow.css" />
    
    <!-- css -->
	<link href="../resources/css/tabs.css" rel="stylesheet" type="text/css" />
	<link href="../resources/frameworks/formvalidation/formValidation.min.css" rel="stylesheet"/>
	
	<!-- javascript -->
	<script src="../resources/frameworks/formvalidation/formValidation.min.js"></script>
	<script src="../resources/frameworks/Bootstrap-3-Typeahead-master/bootstrap3-typeahead.min.js"></script>

    <style>
      body > #standalone-container {
        margin: 50px auto;
        max-width: 720px;
      }
      #editor-container {
        height: 350px;
      }
    </style>
  </head>
  <body>
  
	<s:form class="ui form" namespace="Pastoral" id="formPastoral" name="formPastoral"  theme="simple" enctype="multipart/form-data" data-fv-framework="bootstrap">
		<s:hidden id="pasCodigo" name="pastoralVO.pasCodigo"/>
		<s:hidden id="pasPastoral" name="pastoralVO.pasPastoral"/>
		
		<!-- Mensagem -->
		<s:hidden id="ERRO_CADASTRO_DATA_REFERENCIA_OBRIGATORIO" value="%{getText('ERRO.CADASTRO.DATA.REFERENCIA.OBRIGATORIO')}"/>
		<s:hidden id="ERRO_CADASTRO_DATA_REFERENCIA_INVALIDA" 	 value="%{getText('ERRO.CADASTRO.DATA.REFERENCIA.INVALIDA')}"/>
		<s:hidden id="ERRO_CADASTRO_PASTORAL_OBRIGATORIO" 	 	 value="%{getText('ERRO.CADASTRO.PASTORAL.OBRIGATORIO')}"/>
	
		<div class="col-sm-12">
			<div class="row">
				<div class="col-md-7 form-group">
					<label>Mês Pastoral</label>
					<s:textfield style="width: 100px;" class="form-control" name="pastoralVO.pasDtReferencia" id="pasDtReferencia" maxlength="10" theme="simple" placeholder="99/9999" data-mask="00/0000" data-mask-reverse="true"/>
				</div>			
			</div>
			<div class="row">
				<div class="col-sm-12 form-group">
					<div id="standalone-container">
					      <div id="toolbar-container">
					        <span class="ql-formats">
					          <select class="ql-font"></select>
					          <select class="ql-size"></select>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-bold"></button>
					          <button class="ql-italic"></button>
					          <button class="ql-underline"></button>
					          <button class="ql-strike"></button>
					        </span>
					        <span class="ql-formats">
					          <select class="ql-color"></select>
					          <select class="ql-background"></select>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-script" value="sub"></button>
					          <button class="ql-script" value="super"></button>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-header" value="1"></button>
					          <button class="ql-header" value="2"></button>
					          <button class="ql-blockquote"></button>
					          <button class="ql-code-block"></button>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-list" value="ordered"></button>
					          <button class="ql-list" value="bullet"></button>
					          <button class="ql-indent" value="-1"></button>
					          <button class="ql-indent" value="+1"></button>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-direction" value="rtl"></button>
					          <select class="ql-align"></select>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-link"></button>
					          <button class="ql-image"></button>
					          <button class="ql-video"></button>
					          <button class="ql-formula"></button>
					        </span>
					        <span class="ql-formats">
					          <button class="ql-clean"></button>
					        </span>
					      </div>
					      <div id="editor-container"></div>
					    </div>
				</div>							
			</div>	
			<button style="margin-top: 10px;" type="submit" class="btn btn-primary">Salvar</button>	
		</div>
	
	</s:form>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
    <script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
	<script>

	const editor = document.getElementById('editor-container');
    const hiddenInput = document.getElementById('myHtml');
    const form = document.forms.mainform;

    const quill = new Quill(editor, {
      modules: {
        syntax: true,
        toolbar: '#toolbar-container'
      },
      placeholder: 'Escreva aqui a pastoral...',
      theme: 'snow'
    });

    editor.firstChild.innerHTML = $('#pasPastoral').val();
	
	$('#pasDtReferencia').mask("00/0000", {
		selectOnFocus: true
	});
	
	// validação do formulário principal
    $('#formPastoral').formValidation({
        framework: 'bootstrap',
        excluded: [':disabled'],
        fields: {
        	'pastoralVO.pasDtReferencia': {
        		validators: {
                    notEmpty: {
                        message: $("#ERRO_CADASTRO_DATA_REFERENCIA_OBRIGATORIO").val()
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
	    	
	    	$('#pasPastoral').val(editor.firstChild.innerHTML);
    	
    		$.ajax({
       		url: 'SalvaPastoral.action',
       		data: $('#formPastoral').serialize(),
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
       			waitingDialog.hide();
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
    		        buttons: [{
    		            label: 'Fechar',
    		            action: function(dialogItself){
    		                dialogItself.close();
    		                window.location = "CadastroPastoral.action";
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

	</script>
  </body>
</html>