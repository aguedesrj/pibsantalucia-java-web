$(document).ready(function() {
	
	
});

function iniciarLoading() {
    $('.loading').show();
}

function pararLoading() {
    $('.loading').hide();
}

function exibirModalErroSimples(mensagem) {
	pararLoading();
	BootstrapDialog.show({
        type: BootstrapDialog.TYPE_DANGER,
        title: $("#PIB_SANTA_LUCIA").val(),
        message: mensagem,
        buttons: [{
            label: 'Fechar',
            action: function(dialogItself){
                dialogItself.close();
            }
        }]
    });
}

function exibirModalSucessoSimples(mensagem) {
	pararLoading();
	BootstrapDialog.show({
        type: BootstrapDialog.TYPE_SUCCESS,
        title: $("#PIB_SANTA_LUCIA").val(),
        message: mensagem,
        buttons: [{
            label: 'Fechar',
            action: function(dialogItself){
                dialogItself.close();
            }
        }]
    });
}

function startLoading() {
    $.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } });
}

function somenteNumeros(valor) {
	valor = valor.replace(/[^\d]+/g,'');
	return valor;
}

function validarCPF(cpf) {  
    cpf = cpf.replace(/[^\d]+/g,'');    
    if(cpf == '') return false; 
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;       
    // Valida 1o digito 
    add = 0;    
    for (i=0; i < 9; i ++)       
        add += parseInt(cpf.charAt(i)) * (10 - i);  
        rev = 11 - (add % 11);  
        if (rev == 10 || rev == 11)     
            rev = 0;    
        if (rev != parseInt(cpf.charAt(9)))     
            return false;       
    // Valida 2o digito 
    add = 0;    
    for (i = 0; i < 10; i ++)        
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;    
    if (rev != parseInt(cpf.charAt(10)))
        return false;       
    return true;   
}

function validaData(data) {
    var dia = data.substring(0,2)
    var mes = data.substring(3,5)
    var ano = data.substring(6,10)
 
    //Criando um objeto Date usando os valores ano, mes e dia.
    var novaData = new Date(ano,(mes-1),dia);
 
    var mesmoDia = parseInt(dia,10) == parseInt(novaData.getDate());
    var mesmoMes = parseInt(mes,10) == parseInt(novaData.getMonth())+1;
    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());
 
    if (!((mesmoDia) && (mesmoMes) && (mesmoAno))) {   
        return false;
    }  
    return true;
}

function dataMenorDataHoje(data) {
    var dia = data.substring(0,2)
    var mes = data.substring(3,5)
    var ano = data.substring(6,10)
 
    //Criando um objeto Date usando os valores ano, mes e dia.
    var dataInformada = new Date(ano,(mes-1),dia);
    var dataHoje = new Date();
    if(dataInformada > dataHoje) {
    	return false;
    }
    return true;
}