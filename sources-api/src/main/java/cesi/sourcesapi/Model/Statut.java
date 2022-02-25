package cesi.sourcesapi.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Statut")
public class Statut {

	public Statut(String name) {
		super();
		this.name = name;
		this.privileges = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String name;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Statut_Privilege",
        joinColumns = { @JoinColumn(name = "statut_id") },
        inverseJoinColumns = { @JoinColumn(name = "privilege_id")}
    )
	private Set<Privilege> privileges = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
}

