package it.etlabora.todolist.controllo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.etlabora.todolist.modello.TodoList;
import it.etlabora.todolist.modello.Utente;
import it.etlabora.todolist.persistenza.UtenteDao;
import it.etlabora.todolist.persistenza.impl.UtenteDaoImpl;

@WebServlet("/index")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDao utenteDao = new UtenteDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Utente utente = utenteDao.login(username, password);
		if (utente != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utente", utente);
			session.setAttribute("username", utente.getUsername());
			session.setAttribute("nome", utente.getNome());
			List<TodoList> todoLists = utenteDao.findByAll(username);
			request.setAttribute("todoList", todoLists);	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}

	}

}
