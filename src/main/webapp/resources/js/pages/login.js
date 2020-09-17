$(document).ready(function() {
	$("#cpfOrEmail").focus();
	
	pararLoading();
	
	$('#cpfOrEmail').on('input', function() {
		$(".alert").alert('close');
	});
	
	$('#password').on('input', function() {
		$(".alert").alert('close');
	});
	
	$('#btnEntrar').click(function() {
		$(".alert").alert('close');
		var formLogin = $('#formLogin');
		if(formLogin[0].checkValidity() && $("#cpfOrEmail").parsley().isValid() && $("#password").parsley().isValid()) {
			iniciarLoading();
			
			$("#formLogin").attr("action", "EfetuaLogin.action");
			$("#formLogin").submit();
		}
	});
});