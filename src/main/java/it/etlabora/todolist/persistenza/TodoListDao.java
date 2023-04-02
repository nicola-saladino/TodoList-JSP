package it.etlabora.todolist.persistenza;

import it.etlabora.todolist.modello.TodoList;

public interface TodoListDao {
	void createNewTask(TodoList todolist);
}
