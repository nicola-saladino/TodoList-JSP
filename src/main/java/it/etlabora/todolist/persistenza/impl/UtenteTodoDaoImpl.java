package it.etlabora.todolist.persistenza.impl;

import it.etlabora.todolist.persistenza.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.etlabora.todolist.persistenza.DBUtil;
import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.UtenteTodo;
import it.etlabora.todolist.persistenza.UtenteTodoDao;

public class UtenteTodoDaoImpl implements UtenteTodoDao {

	@Override
	public void save(UtenteTodo utenteTodo) {
		String sql = "INSERT INTO utente_todo (utente_id, todo_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql, new String[] { "id" });
			statement.setInt(1, utenteTodo.getUtente().getId());
			statement.setInt(2, utenteTodo.getTodoList().getId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				utenteTodo.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	@Override
	public List<TodoList> getTodoListForUser(int userId) {
		String sql = "SELECT * FROM todo_list JOIN utente_todo ON todo_list.id = utente_todo.todo_id WHERE utente_todo.utente_id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TodoList> todoList = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			todoList = new ArrayList<>();
			while (resultSet.next()) {
				TodoList todo = new TodoList();
				todo.setId(resultSet.getInt("id"));
				todo.setDescrizione(resultSet.getString("descrizione"));
				todo.setDataCreazione(resultSet.getDate("data_creazione"));
				todo.setDataScadenza(resultSet.getDate("data_assegnazione"));
				todoList.add(todo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return todoList;
	}

}
