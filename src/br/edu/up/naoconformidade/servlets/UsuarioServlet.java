package br.edu.up.naoconformidade.servlets;

import java.io.IOException;
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

import br.edu.up.naoconformidade.facade.UsuarioFacade;
import br.edu.up.naoconformidade.model.Usuario;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String id = request.getParameter("id");
		
		String json = new String();
		UsuarioFacade facade = new UsuarioFacade();
		
		if(id == null){
			List<Usuario> usuarios = new ArrayList<Usuario>();
			try {
				usuarios = facade.listar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			json = new Gson().toJson(usuarios);
		} else {
			Usuario usuario = new Usuario();
			try {
				usuario = facade.buscar(Integer.valueOf(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			json = new Gson().toJson(usuario);
		}
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Map<String, String> resposta = new LinkedHashMap<String, String>();
		
		UsuarioFacade facade = new UsuarioFacade();
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		try {
			facade.save(usuario);
			resposta.put("mensagem", "Usuario cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			resposta.put("mensagem", "Erro ao cadastrar usuario!");
		}
		
		String json = new Gson().toJson(resposta);
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
