package it.etlabora.todolist.modello;

import java.util.Date;

public class TodoList {
	private int id;
	private String descrizione;
	private Date dataCreazione;
	private Date dataScadenza;

	public TodoList() {
		super();
	}

	public TodoList(int id, String descrizione, Date dataCreazione, Date dataScadenza) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataScadenza = dataScadenza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", descrizione=" + descrizione + ", dataCreazione=" + dataCreazione
				+ ", dataScadenza=" + dataScadenza + "]";
	}

}
