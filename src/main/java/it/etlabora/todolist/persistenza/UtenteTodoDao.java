package it.etlabora.todolist.persistenza;

import java.util.List;

import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.UtenteTodo;

public interface UtenteTodoDao {
	  void save(UtenteTodo utenteTodo);
	  List<TodoList> getTodoListForUser(int userId);
}
