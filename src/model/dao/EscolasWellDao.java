package model.dao;

import java.util.List;

import model.entities.EscolasWell;



public interface EscolasWellDao {

	void insert(EscolasWell obj);
	void update(EscolasWell sobj);
	void deleteById(Integer id);
	EscolasWell findById(Integer id);
	List<EscolasWell> findAll();
}
