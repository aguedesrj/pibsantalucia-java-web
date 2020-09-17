$(document).ready(function() {
	
	// obter lista de contatos...
	$.ajax({
		url: 'QuantidadeMembros.action',
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#spanCarregandoGrafico").html("carregando gr\u00E1fico...");
		},
		success: function(data, status, request) {
			if (status != "success") {
				$("#spanCarregandoGrafico").html("Erro ao carregar gr\u00E1fico.");
				return;
			}
			if (data.quantidadeMembroVO != null) {
				$("#spanCarregandoGrafico").html("");
				montarGrafico(data.quantidadeMembroVO);
			} else {
				$("#spanCarregandoGrafico").html("Erro ao carregar gr\u00E1fico.");
			}
		},
		error: function (request, error) {
			$("#spanCarregandoGrafico").html("Erro ao carregar gr\u00E1fico.");
		}        
	});	
});

function montarGrafico(quantidadeMembroVO) {
	
	Highcharts.setOptions({
	     colors: ['#0000FF', '#FA58D0']
	    });
	
	Highcharts.chart('container', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Percentual de Membros por sexo.'
	    },
	    subtitle: {
	        text: 'Quantidade Membros: '+quantidadeMembroVO.quantidadeMembros
	    },	    
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                style: {
	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                }
	            }
	        }
	    },
	    series: [{
	        name: 'Membros',
	        colorByPoint: true,
	        data: [{
	            name: 'Masculino',
	            y: quantidadeMembroVO.percentualMasculino
	        }, {
	            name: 'Feminino',
	            y: quantidadeMembroVO.percentualFemninino
	        }]
	    }]
	});	
}
