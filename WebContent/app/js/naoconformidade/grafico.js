$(document).ready(function(){
	$.ajax({
		url: "../naoconformidade",
		type: "GET",
		dataType: "json",
		success: function(naoconformidades){
			var baixa = 0;
			var media = 0;
			var alta  = 0;
			
			$.each(naoconformidades, function(){
				switch(this.prioridade) {
				    case 1:
				    	baixa++;
				        break;
				    case 2:
				    	media++;
				        break;
				    case 3:
				    	alta++;
				        break;
				}
			})
			
			var data = {
				datasets: [{
			        data: [
			            baixa,
			            media,
			            alta,
			        ],
			        backgroundColor: [
			            "#FF6384",
			            "#4BC0C0",
			            "#FFCE56",
			        ]
			    }],
			    labels: [
			        "Baixa",
			        "Media",
			        "Alta",
			    ]
			};

			var ctx = $("#myChart").get(0).getContext("2d");
			
			var myPieChart = new Chart(ctx,{
			    type: 'pie',
			    data: data
			});
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
})