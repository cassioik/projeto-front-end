package br.edu.up.naoconformidade.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.up.naoconformidade.facade.UsuarioFacade;
import br.edu.up.naoconformidade.model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Map<String, String> resposta = new LinkedHashMap<String, String>();
		int autenticado = 0;
		
		UsuarioFacade facade = new UsuarioFacade();
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		
		try {
			if (facade.validarLoginBoolean(usuario)){
				autenticado = 1;
				resposta.put("mensagem", "Login realizado com sucesso!");
			} else {
				resposta.put("mensagem", "Email ou senha incorretos!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resposta.put("mensagem", "Email ou senha incorretos!");
		}

		resposta.put("autenticado", String.valueOf(autenticado));
		
		String json = new Gson().toJson(resposta);
		
		System.out.println(json);
		
		response.setContentType("application;json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
