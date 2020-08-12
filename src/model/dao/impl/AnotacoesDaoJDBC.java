package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.AnotacoesDao;
import model.entities.AnotacoesC;
import model.entities.EscolasWell;

public class AnotacoesDaoJDBC implements AnotacoesDao {

	private Connection conn;
	
	public AnotacoesDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(AnotacoesC obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO registrarAnotacoe "
					+ "(usuario, dataCriado, email, telefone, problema, des_Situacao, situacao, n_chamdo, escolaWellId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getUsuario_criar());
			st.setDate(2, new java.sql.Date(obj.getDataAtual_criar().getTime()));
			st.setString(3, obj.getEmail() );
			st.setString(4, obj.getTelefone());
			st.setString(5, obj.getProblema_criar());
			st.setString(6, obj.getDesSituacao_criar());
			st.setString(7, obj.getSituacao_criar());
			st.setString(8, obj.getNumeroChamdo_criar());
			st.setInt(9, obj.getEscolasWell().getId());
			
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
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
	public void update(AnotacoesC obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE registrarAnotacoe "
					+ "SET usuario = ?, dataCriado = ?, email = ?, telefone = ?, problema= ?,"
					+ " des_Situacao = ?, situacao = ?, n_chamdo, = ?, escolaWellId = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getUsuario_criar());
			st.setDate(2, new java.sql.Date(obj.getDataAtual_criar().getTime()));
			st.setString(3, obj.getEmail() );
			st.setString(4, obj.getTelefone());
			st.setString(5, obj.getProblema_criar());
			st.setString(6, obj.getDesSituacao_criar());
			st.setString(7, obj.getSituacao_criar());
			st.setString(8, obj.getNumeroChamdo_criar());
			st.setInt(9, obj.getEscolasWell().getId());
		
			
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
			st = conn.prepareStatement("DELETE FROM registrarAnotacoe WHERE id = ?");
			
			st.setInt(1, id);
			
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
	public AnotacoesC findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT registrarAnotacoe.*,escolasWell.nome as EscolaNome "
					+ "FROM registrarAnotacoe INNER JOIN escolasWell "
					+ "ON registrarAnotacoe.escolaWellId = escolasWell.id "
					+ "WHERE registrarAnotacoe.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				EscolasWell ew = instantiateEscolaWell(rs);
			//	Colaborador cl= instantiateAjudante(rs);
				AnotacoesC obj = instantiateAnotacoes(rs, ew);
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

	private AnotacoesC instantiateAnotacoes(ResultSet rs, EscolasWell ew) throws SQLException {
		AnotacoesC obj = new AnotacoesC();

		
		obj.setId(rs.getInt("id"));
		obj.setUsuario_criar(rs.getString("Usuario"));
		obj.setDataAtual_criar(new java.util.Date(rs.getTimestamp("Data").getTime()));
		obj.setEmail(rs.getString("Email"));
		obj.setTelefone(rs.getString("Telefone"));
		obj.setProblema_criar(rs.getString("Problema"));
		obj.setDesSituacao_criar(rs.getString("Des. Situacao"));
		obj.setSituacao_criar(rs.getString("Situacao"));
		obj.setNumeroChamdo_criar(rs.getString("N chamado"));
		obj.setEscolasWell(ew);
		
		
		///creio que seje o nome das colunas para a insersão dos dados
		return obj;
	}

	private EscolasWell instantiateEscolaWell(ResultSet rs) throws SQLException {
		EscolasWell ew = new EscolasWell();
		ew.setId(rs.getInt("EscolaWellId"));
		ew.setNome(rs.getString("EscolaNome"));
		return ew;
	}
	
	
	@Override
	public List<AnotacoesC> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT registrarAnotacoe.*,escolasWell.Name as Escola "
					+ "FROM registrarAnotacoe INNER JOIN escolasWell "
					+ "ON registrarAnotacoe.escolaWellId = escolasWell.Id "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<AnotacoesC> list = new ArrayList<>();
			Map<Integer, EscolasWell> map = new HashMap<>();
			
			while (rs.next()) {
				
				EscolasWell ew = map.get(rs.getInt("EscolaWellId"));
				
				if (ew== null) {
					ew = instantiateEscolaWell(rs);
					map.put(rs.getInt("escolaWellId"), ew);
				}
				
				AnotacoesC obj = instantiateAnotacoes(rs, ew);
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
	public List<AnotacoesC> findByEscolasWell(EscolasWell escolasWell) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT registrarAnotacoe.*,escolasWell.Name as Escola "
					+ "FROM registrarAnotacoe INNER JOIN escolasWell "
					+ "ON registrarAnotacoe.escolaWellId = escolasWell.id "
					+ "WHERE escolaWellId = ? "
					+ "ORDER BY nome");
			
			st.setInt(1, escolasWell.getId());
			
			rs = st.executeQuery();
			
			List<AnotacoesC> list = new ArrayList<>();
			Map<Integer, EscolasWell> map = new HashMap<>();
			
			while (rs.next()) {
				
				EscolasWell ew = map.get(rs.getInt("escolaWellId"));
				
				if (ew == null) {
					ew = instantiateEscolaWell(rs);
					map.put(rs.getInt("escolaWellId"), ew);
				}
				
				AnotacoesC obj = instantiateAnotacoes(rs, ew);
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
}
