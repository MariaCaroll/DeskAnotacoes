package model.services;

import java.util.List;

import model.dao.AnotacoesDao;
import model.dao.DaoFactory;
import model.entities.AnotacoesC;

public class AnotacoesService {
	
	private AnotacoesDao dao = DaoFactory.createAnotacoesDao();

	public List<AnotacoesC> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(AnotacoesC obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(AnotacoesC obj) {
		dao.deleteById(obj.getId());
	}
}
