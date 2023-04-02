package it.etlabora.todolist.persistenza.impl;

import it.etlabora.todolist.persistenza.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.etlabora.todolist.persistenza.DBUtil;
import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.persistenza.TodoListDao;

public class TodoListDaoImpl implements TodoListDao {

	@Override
	public void createNewTask(TodoList todolist) {
		String sql = "INSERT INTO todo_list(descrizione, data_creazione, data_assegnazione) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql, new String[] { "id" });
			statement.setString(1, todolist.getDescrizione());
			statement.setDate(2, new java.sql.Date(todolist.getDataCreazione().getTime()));
			statement.setDate(3, new java.sql.Date(todolist.getDataScadenza().getTime()));
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				todolist.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.close(generatedKeys);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		
	}

}


