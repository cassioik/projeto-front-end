package br.edu.up.naoconformidade.facade;

import java.util.List;

import br.edu.up.naoconformidade.dao.UsuarioDAO;
import br.edu.up.naoconformidade.model.Usuario;

public class UsuarioFacade {
	private UsuarioDAO dao = new UsuarioDAO();
	
	public void save(Usuario usuario) throws Exception {
		try {
			dao.beginTransaction();
			dao.save(usuario);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}

	public void update(Usuario usuario) throws Exception {
		try {
			dao.beginTransaction();
			dao.update(usuario);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw new Exception(e);
		}
	}

	public Usuario buscar(Integer id) throws Exception {
		try {
			dao.beginTransaction();
			Usuario usuario = dao.find(id);
			dao.commitAndCloseTransaction();
			return usuario;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}

	public void delete(Integer id) throws Exception {
		try {
			dao.beginTransaction();
			Usuario usuario = dao.findReferenceOnly(id);
			dao.delete(usuario);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
	
	public Usuario validarLogin(Usuario u) throws Exception {
		try {
			dao.beginTransaction();
			Usuario usuario = dao.validarLogin(u);
			dao.commitAndCloseTransaction();
			return usuario;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
	
	public Boolean validarLoginBoolean(Usuario u) throws Exception {
		try {
			dao.beginTransaction();
			Boolean boo = dao.validarLoginBoolean(u);
			dao.commitAndCloseTransaction();
			return boo;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
	
	public List<Usuario> listar() throws Exception {
		try {
			dao.beginTransaction();
			List<Usuario> usuarios = dao.listar();
			dao.commitAndCloseTransaction();
			return usuarios;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
}
