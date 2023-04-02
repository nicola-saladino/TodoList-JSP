package it.etlabora.todolist.controllo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.Utente;
import it.etlabora.todolist.modello.UtenteTodo;
import it.etlabora.todolist.persistenza.TodoListDao;
import it.etlabora.todolist.persistenza.UtenteDao;
import it.etlabora.todolist.persistenza.UtenteTodoDao;
import it.etlabora.todolist.persistenza.impl.TodoListDaoImpl;
import it.etlabora.todolist.persistenza.impl.UtenteDaoImpl;
import it.etlabora.todolist.persistenza.impl.UtenteTodoDaoImpl;

@WebServlet("/creazione-task")
public class CreaTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListDao todoListDao = new TodoListDaoImpl();
	private UtenteTodoDao utenteTodoDao = new UtenteTodoDaoImpl();
	private UtenteDao utenteDao = new UtenteDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("utente") != null) {
			// L'utente è già autenticato
			Utente utente = (Utente) session.getAttribute("utente");

			String descrizione = request.getParameter("task");
			String dataCreazioneAsString = request.getParameter("dataCreazione");
			Date dataCreazione = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dataCreazione = sdf.parse(dataCreazioneAsString);
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}

			String dataAssegnazioneAsString = request.getParameter("dataScadenza");
			Date dataAssegnazione = null;
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dataAssegnazione = sdf2.parse(dataAssegnazioneAsString);
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}

			// Crea un nuovo oggetto TodoList
			TodoList todoList = new TodoList();
			todoList.setDescrizione(descrizione);
			todoList.setDataCreazione(dataCreazione);
			todoList.setDataScadenza(dataAssegnazione);
			// Salva il nuovo oggetto TodoList nel database
			todoListDao.createNewTask(todoList);

			// Ottieni l'ID dell'utente corrente dalla sessione
			// Crea un oggetto UtenteTodo che associa il nuovo TodoList all'utente corrente
			UtenteTodo utenteTodo = new UtenteTodo();
			utenteTodo.setUtente(utente);
			utenteTodo.setTodoList(todoList);
			// Salva l'oggetto UtenteTodo nel database
			utenteTodoDao.save(utenteTodo);
			
			List<TodoList> todoLists = utenteDao.findByAll(utente.getUsername());
			request.setAttribute("todoList", todoLists);	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			// L'utente non è autenticato, redirect alla pagina di login
			response.sendRedirect(request.getContextPath() + "/login");

		}
	}
}
