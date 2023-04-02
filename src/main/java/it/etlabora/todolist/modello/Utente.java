package it.etlabora.todolist.modello;

import java.util.ArrayList;
import java.util.List;

public class Utente {
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	List<TodoList> todoList = new ArrayList<TodoList>(); 

	public Utente() {
		super();
	}

	public Utente(int id, String nome, String cognome, String username, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public List<TodoList> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoList> todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password="
				+ password + "]";
	}

}
