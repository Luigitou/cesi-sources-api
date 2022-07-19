package cesi.sourcesapi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Commentaires")
public class Commentaire {
	public Commentaire() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public Commentaire(LocalDateTime date, String text, Utilisateur utilisateur) {
		super();
		this.date = date;
		this.text = text;
		this.utilisateur = utilisateur;
	}
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "Utilisateur_id", insertable = true, updatable = true)
	private Utilisateur utilisateur;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dateTimeFormatter.format(date);
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
