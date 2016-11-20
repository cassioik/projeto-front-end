package br.edu.up.naoconformidade.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.up.naoconformidade.model.NaoConformidade;

public class NaoConformidadeDAO extends GenericDAO<Integer, NaoConformidade>{

	public NaoConformidadeDAO() {
		super(NaoConformidade.class);
	}
	
	public List<NaoConformidade> listar(){
		String sql = "select n from NaoConformidade n";
		Query query = super.getEntityManager().createQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<NaoConformidade> retorno = query.getResultList();
		return retorno;
	}
	
}
