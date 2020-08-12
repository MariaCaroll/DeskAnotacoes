package model.dao;

import java.util.List;

import model.entities.AnotacoesC;
import model.entities.EscolasWell;


public interface AnotacoesDao {
	
	void insert(AnotacoesC obj);
	void update(AnotacoesC  obj);
	void deleteById(Integer id);
	AnotacoesC  findById(Integer id);
	List<AnotacoesC > findAll();
	List<AnotacoesC> findByEscolasWell(EscolasWell escolasWell);
	
}
