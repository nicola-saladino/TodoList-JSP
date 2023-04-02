package it.etlabora.todolist.modello;

public class UtenteTodo {
	private int id;
	private Utente utente;
	private TodoList todoList;

	public UtenteTodo() {
		super();
	}
	
	public UtenteTodo(int id, Utente utente, TodoList todoList) {
		super();
		this.id = id;
		this.utente = utente;
		this.todoList = todoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "UtenteTodo [id=" + id + ", utente=" + utente + ", todoList=" + todoList + "]";
	}

}
