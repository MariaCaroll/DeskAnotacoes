package model.dao;


import db.DB;
import model.dao.impl.AnotacoesDaoJDBC;
import model.dao.impl.EscolasWellDaoJDBC2;

public class DaoFactory {

	
	public static EscolasWellDao createEsclasWellDao() {
		return new EscolasWellDaoJDBC2(DB.getConnection());
		
	}	
		
		public static AnotacoesDao createAnotacoesDao() {
			return new AnotacoesDaoJDBC(DB.getConnection());
		}
	
}
