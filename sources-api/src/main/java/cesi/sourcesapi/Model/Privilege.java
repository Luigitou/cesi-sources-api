package cesi.sourcesapi.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Privilege")
public class Privilege {
	
	public Privilege() {}

	public Privilege(String type) {
		super();
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "type")
	private String type;
	
	@ManyToMany(mappedBy = "privileges")
	private Set<Statut> statut = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Statut> getStatut() {
		return statut;
	}

	public void setStatut(Set<Statut> status) {
		this.statut = status;
	}
}
