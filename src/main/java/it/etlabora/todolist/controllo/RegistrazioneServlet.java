package it.etlabora.todolist.controllo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.etlabora.todolist.modello.Utente;
import it.etlabora.todolist.persistenza.UtenteDao;
import it.etlabora.todolist.persistenza.impl.UtenteDaoImpl;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDao utenteDao = new UtenteDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = new Utente();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (nome != null && !nome.isBlank() && cognome != null && !cognome.isBlank() && username != null
				&& !username.isBlank() && password != null && !password.isBlank()) {
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setUsername(username);
			utente.setPassword(password);
			utenteDao.registrazioneNuovoUtente(utente);
			response.sendRedirect("login.jsp");
			return;
		} else
			response.sendRedirect("registrazione.jsp");
	}

}
