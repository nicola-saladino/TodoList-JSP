package it.etlabora.todolist.persistenza;

import java.util.List;

import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.Utente;

public interface UtenteDao {
	List<TodoList> findByAll(String username);
	List<Utente> findUtenteByAll();
	Utente login(String username, String password);
	void registrazioneNuovoUtente(Utente utente);
	Utente findById(int id);
}
