$(document).ready(function() {
	// Mascara para data
	$('.date').mask('00/00/0000');
	listarResponsaveis();
})

function listarResponsaveis(){
	$.ajax({
		url: "../usuario",
		type: "GET",
		dataType: "json",
		success: function(usuarios){
			var select_responsavel = $("#select_responsavel");
			
			$.each(usuarios, function(){
				var option = "";
				option += '<option value=' + this.id + '>' + this.nome + '</option>';
				
				select_responsavel.append(option);
			})
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function salvar(){
	var titulo = $("#titulo").val();
	var descricao = $("#descricao").val();
	var prazo = convertDate($("#prazo").val());
	var prioridade = $("#prioridade").val();
	var responsavel = $("#select_responsavel").val();
	
	$.ajax({
		url: "../naoconformidade",
		type: "POST",
		dataType: "json",
		data:{
			titulo: titulo,
			descricao: descricao,
			prazo: prazo,
			prioridade: prioridade,
			responsavel: responsavel
		},
		success: function(data){
			window.location = "view/naoconformidade/tabela.html";
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function convertDate(brDate) {
	var dateParts = brDate.split(/(\d{1,2})\/(\d{1,2})\/(\d{4})/);
	return dateParts[3] + "-" + dateParts[2] + "-" + dateParts[1];
}