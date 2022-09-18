package com.superherosightings.main.dto;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="superpower")
public class Superpower {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int superpowerId;
	
	@Column(columnDefinition = "varchar(30)")
	private String name;
	
	@OneToMany(mappedBy="superpower", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Superhero> Superheroes;

	@Override
	public int hashCode() {
		return Objects.hash(name, superpowerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Superpower other = (Superpower) obj;
		return Objects.equals(name, other.name) && superpowerId == other.superpowerId;
	}

	
}
