$(document).ready(function() {
	listarNaoConformidades();
})

function listarNaoConformidades(){
	$.ajax({
		url: "../naoconformidade",
		type: "GET",
		dataType: "json",
		success: function(data){
			var tbody = $("#tbody");
			var pri = "";
			
			$.each(data, function(){				
				//define o nome da prioridade
				switch(this.prioridade) {
				    case 1:
				        pri = "Baixa";
				        break;
				    case 2:
				    	pri = "Media";
				        break;
				    case 3:
				    	pri = "Alta";
				        break;
				    default:
				    	pri = "";
				}
				var linha = $("<tr>");
				var colunas = "";
				colunas += '<td>' + this.titulo + '</td>';
				colunas += '<td>' + this.descricao + '</td>';
				colunas += '<td>' + this.prazo.day + '/' + this.prazo.month + '/' + this.prazo.year + '</td>';
				colunas += '<td>' + pri + '</td>';
				colunas += '<td>' + this.usuario.nome + '</td>';
				colunas += '<td> <button class="btn btn-warning" onclick="editar('+this.id+')">Editar</button>';
				colunas += '     <button class="btn btn-danger" onclick="excluir('+this.id+')">Excluir</button> </td>';
				
				linha.append(colunas);
				tbody.append(linha);
			})
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("Erro: " + xhr.status);
		}
	})
}

function excluir(id){
	var r = confirm("Excluir Não Conformidade!");
	if (r == true) {
		$.ajax({
			url: "../naoconformidade?id="+id,
			type: "DELETE",
			dataType: "json",
			success: function(resposta){
				location.reload();
			},
			error: function(xhr, ajaxOptions, thrownError){
				alert("Erro: " + xhr.status);
			}
		})
	}
}

function editar(id){
	window.location = "view/naoconformidade/editar.html?id="+id;
}