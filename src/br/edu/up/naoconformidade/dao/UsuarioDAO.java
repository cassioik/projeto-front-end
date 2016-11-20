package br.edu.up.naoconformidade.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.up.naoconformidade.model.Usuario;

public class UsuarioDAO extends GenericDAO<Integer, Usuario>{

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario validarLogin(Usuario u){
		String sql = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		Query query = super.getEntityManager().createQuery(sql);
		query.setParameter("email", u.getEmail());
		query.setParameter("senha", u.getSenha());
		
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario;
	}
	
	public Boolean validarLoginBoolean(Usuario u) {
		String sql = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		Query query = super.getEntityManager().createQuery(sql);
		query.setParameter("email", u.getEmail());
		query.setParameter("senha", u.getSenha());
		
		try{
			Object obj = query.getSingleResult();
			if (obj.equals(null)){
				return false;
			} else {
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public List<Usuario> listar(){
		String sql = "SELECT n FROM Usuario n";
		Query query = super.getEntityManager().createQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
		return retorno;
	}
}
