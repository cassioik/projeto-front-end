package br.edu.up.naoconformidade.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.up.naoconformidade.facade.NaoConformidadeFacade;
import br.edu.up.naoconformidade.facade.UsuarioFacade;
import br.edu.up.naoconformidade.model.NaoConformidade;
import br.edu.up.naoconformidade.model.Usuario;

@WebServlet("/naoconformidade")
public class NaoConformidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NaoConformidadeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String id = request.getParameter("id");
		
		String json = new String();
		NaoConformidadeFacade facade = new NaoConformidadeFacade();
		
		if(id == null){
			List<NaoConformidade> naoConformidades = new ArrayList<NaoConformidade>();
			try {
				naoConformidades = facade.listar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			json = new Gson().toJson(naoConformidades);
		} else {
			NaoConformidade nao = new NaoConformidade();
			try {
				nao = facade.buscar(Integer.valueOf(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			json = new Gson().toJson(nao);
		}
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String prazo = request.getParameter("prazo");
		String prioridade = request.getParameter("prioridade");
		String responsavel = request.getParameter("responsavel");
		
		Map<String, String> resposta = new LinkedHashMap<String, String>();
		
		NaoConformidadeFacade naoConformidadeFacade = new NaoConformidadeFacade();
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioFacade.buscar(Integer.valueOf(responsavel));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		NaoConformidade nao = new NaoConformidade();
		nao.setTitulo(titulo);
		nao.setDescricao(descricao);
		nao.setPrazo(LocalDate.parse(prazo));
		nao.setPrioridade(Integer.valueOf(prioridade));
		nao.setUsuario(usuario);
		
		try {
			naoConformidadeFacade.save(nao);
			resposta.put("mensagem", "Não conformidade cadastrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			resposta.put("mensagem", "Erro ao cadastrar não conformidade!");
		}
		
		String json = new Gson().toJson(resposta);
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String prazo = request.getParameter("prazo");
		String prioridade = request.getParameter("prioridade");
		String responsavel = request.getParameter("responsavel");
		
		Map<String, String> resposta = new LinkedHashMap<String, String>();
		
		NaoConformidadeFacade naoConformidadeFacade = new NaoConformidadeFacade();
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioFacade.buscar(Integer.valueOf(responsavel));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		NaoConformidade nao = new NaoConformidade();
		nao.setId(Integer.valueOf(id));
		nao.setTitulo(titulo);
		nao.setDescricao(descricao);
		nao.setPrazo(LocalDate.parse(prazo));
		nao.setPrioridade(Integer.valueOf(prioridade));
		nao.setUsuario(usuario);
		
		try {
			naoConformidadeFacade.update(nao);
			resposta.put("mensagem", "Não conformidade editada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			resposta.put("mensagem", "Erro ao editar não conformidade!");
		}
		
		String json = new Gson().toJson(resposta);
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String id = request.getParameter("id");
		
		Map<String, String> resposta = new LinkedHashMap<String, String>();
		
		NaoConformidadeFacade naoConformidadeFacade = new NaoConformidadeFacade();	
		
		try {
			naoConformidadeFacade.delete(Integer.valueOf(id));
			resposta.put("mensagem", "Não conformidade deletada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			resposta.put("mensagem", "Erro ao deletar não conformidade!");
		}
		
		String json = new Gson().toJson(resposta);
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
