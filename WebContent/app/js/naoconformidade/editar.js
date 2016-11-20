$(document).ready(function() {
	// Mascara para data
	$('.date').mask('00/00/0000');
	var id = GetURLParameter("id");
	buscarNaoConformidade(id);
})

function buscarNaoConformidade(id){	
	$.ajax({
		url: "../naoconformidade?id="+id,
		type: "GET",
		dataType: "json",
		success: function(data){
			var dia = "";
			var mes = "";
			if (data.prazo.day < 10){
				dia = "0"+data.prazo.day;
			}else{
				dia = data.prazo.day;
			}
			if (data.prazo.month < 10){
				mes = "0"+data.prazo.month;
			}else{
				mes = data.prazo.month;
			}
			document.getElementById('id').value = data.id;
			document.getElementById('titulo').value = data.titulo;
			document.getElementById('descricao').value = data.descricao;
			document.getElementById('prazo').value = dia + "/" + mes + "/" + data.prazo.year;
			document.getElementById('prioridade').value = data.prioridade;
			listarResponsaveis(data.usuario.id);
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function listarResponsaveis(cod_usuario){
	$.ajax({
		url: "../usuario",
		type: "GET",
		dataType: "json",
		success: function(usuarios){
			var select_responsavel = $("#select_responsavel");
			
			$.each(usuarios, function(){
				var option = "";
				if (this.id == cod_usuario){
					option += '<option value=' + this.id + ' selected>' + this.nome + '</option>';
				}else{
					option += '<option value=' + this.id + '>' + this.nome + '</option>';
				}
				
				select_responsavel.append(option);
			})
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function salvar(){
	var id = $("#id").val();
	var titulo = $("#titulo").val();
	var descricao = $("#descricao").val();
	var prazo = convertDate($("#prazo").val());
	var prioridade = $("#prioridade").val();
	var responsavel = $("#select_responsavel").val();
	
	$.ajax({
		url: "../naoconformidade?id="+id+"&titulo="+titulo+"&descricao="+descricao+"&prazo="+prazo+"&prioridade="+prioridade+"&responsavel="+responsavel,
		type: "PUT",
		dataType: "json",
		success: function(data){
			window.location = "view/naoconformidade/tabela.html";
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function GetURLParameter(sParam){
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('$');
	for (var i = 0; i < sURLVariables.length; i++){
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam){
			return sParameterName[1];
		}
	}
}

function convertDate(brDate) {
	var dateParts = brDate.split(/(\d{1,2})\/(\d{1,2})\/(\d{4})/);
	return dateParts[3] + "-" + dateParts[2] + "-" + dateParts[1];
}