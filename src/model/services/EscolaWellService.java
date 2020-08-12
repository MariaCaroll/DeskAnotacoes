package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EscolasWellDao;
import model.entities.EscolasWell;

public class EscolaWellService {
	
	private EscolasWellDao dao = DaoFactory.createEsclasWellDao();
	
	public List<EscolasWell> findAll() {
		
		return dao.findAll();
	}
	
	public void saveOrUpdate(EscolasWell obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(EscolasWell obj) {
		dao.deleteById(obj.getId());
	}

}
