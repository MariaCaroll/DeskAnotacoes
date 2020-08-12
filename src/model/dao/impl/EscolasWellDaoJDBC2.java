package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.EscolasWellDao;
import model.entities.EscolasWell;

public class EscolasWellDaoJDBC2 implements EscolasWellDao {

	private Connection conn;
	
	public EscolasWellDaoJDBC2(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public EscolasWell findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM escolasWell WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				EscolasWell obj = new EscolasWell();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<EscolasWell> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM escolasWell ORDER BY nome");
			rs = st.executeQuery();

			List<EscolasWell> list = new ArrayList<>();

			while (rs.next()) {
				EscolasWell obj = new EscolasWell();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(EscolasWell obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO escolasWell " +
				"(nome) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(EscolasWell obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE escolasWell " +
				"SET nome = ? " +
				"WHERE id = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM escolasWell WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
