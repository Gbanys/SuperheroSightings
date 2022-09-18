package com.superherosightings.main.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="superhero")
public class Superhero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int superheroId;
	
	@Column(columnDefinition="varchar(50)")
	private String name;
	
	@Column(columnDefinition="varchar(255)")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="superpowerId")
	private Superpower superpower;
	
	@OneToMany(mappedBy="superhero", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SuperheroOrganization> SuperheroesByOrganization;
	
	@OneToMany(mappedBy="superhero", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SuperheroLocation> SuperheroesByLocation;

	@Override
	public int hashCode() {
		return Objects.hash(description, name, superheroId, superpower);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Superhero other = (Superhero) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& superheroId == other.superheroId && Objects.equals(superpower, other.superpower);
	}

	@Override
	public String toString() {
		return "Superhero [superheroId=" + superheroId + ", name=" + name + ", description=" + description
				+ ", superpower=" + superpower + ", SuperheroesByOrganization=" + SuperheroesByOrganization
				+ ", SuperheroesByLocation=" + SuperheroesByLocation + "]";
	}
	

	
	
}
