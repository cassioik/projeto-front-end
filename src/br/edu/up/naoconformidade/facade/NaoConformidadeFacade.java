package br.edu.up.naoconformidade.facade;

import java.util.List;

import br.edu.up.naoconformidade.dao.NaoConformidadeDAO;
import br.edu.up.naoconformidade.model.NaoConformidade;

public class NaoConformidadeFacade {
private NaoConformidadeDAO dao = new NaoConformidadeDAO();
	
	public void save(NaoConformidade naoConformidade) throws Exception {
		try {
			dao.beginTransaction();
			dao.save(naoConformidade);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}

	public void update(NaoConformidade naoConformidade) throws Exception {
		try {
			dao.beginTransaction();
			dao.update(naoConformidade);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw new Exception(e);
		}
	}

	public NaoConformidade buscar(Integer id) throws Exception {
		try {
			dao.beginTransaction();
			NaoConformidade naoConformidade = dao.find(id);
			dao.commitAndCloseTransaction();
			return naoConformidade;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}

	public void delete(Integer id) throws Exception {
		try {
			dao.beginTransaction();
			NaoConformidade naoConformidade = dao.findReferenceOnly(id);
			dao.delete(naoConformidade);
			dao.commitAndCloseTransaction();
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
	
	public List<NaoConformidade> listar() throws Exception {
		try {
			dao.beginTransaction();
			List<NaoConformidade> naoConformidade = dao.listar();
			dao.commitAndCloseTransaction();
			return naoConformidade;
		} catch (Exception e) {
			dao.rollbackAndCloseTransaction();
			throw e;
		}
	}
}
