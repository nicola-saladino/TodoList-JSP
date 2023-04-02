package it.etlabora.todolist.persistenza.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.etlabora.todolist.persistenza.DataSource;
import it.etlabora.todolist.persistenza.DBUtil;
import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.Utente;
import it.etlabora.todolist.persistenza.UtenteDao;

public class UtenteDaoImpl implements UtenteDao {

	@Override
	public List<TodoList> findByAll(String username) {
		String sql = "select * from utente_todo as ut inner join utente as u on ut.utente_id = u.id inner join todo_list as tl on ut.todo_id = tl.id where username = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TodoList> todoListArray = new ArrayList<TodoList>();
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TodoList todoList = new TodoList();
				todoList.setId(resultSet.getInt(9));
				todoList.setDescrizione(resultSet.getString(10));
				todoList.setDataCreazione(new Date(resultSet.getDate(11).getTime()));
				todoList.setDataScadenza(new Date(resultSet.getDate(12).getTime()));
				todoListArray.add(todoList);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return todoListArray;
	}

	@Override
	public List<Utente> findUtenteByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente login(String username, String password) {
		String sql = "select * from utente where username = ? and password = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Utente utente = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				utente = new Utente();
				utente.setId(resultSet.getInt(1));
				utente.setUsername(resultSet.getString(4));
				utente.setPassword(resultSet.getString(5));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return utente;
	}

	@Override
	public void registrazioneNuovoUtente(Utente utente) {
		String sql = "insert into utente(nome, cognome, username, password) values(?,?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getUsername());
			statement.setString(4, utente.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
	}

	@Override
	public Utente findById(int id) {
		String sql = "SELECT * FROM utente WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Utente utente = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				utente = new Utente();
				utente.setId(resultSet.getInt("id"));
				utente.setNome(resultSet.getString("nome"));
				utente.setCognome(resultSet.getString("cognome"));
				utente.setUsername(resultSet.getString("username"));
				utente.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return utente;
	}

}
