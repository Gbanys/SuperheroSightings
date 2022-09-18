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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int locationId;
	
	@Column(columnDefinition = "varchar(50)")
	private String name;
	
	@Column(columnDefinition = "varchar(255)")
	private String description;
	
	@Column(columnDefinition = "varchar(255)")
	private String address;
	
	@OneToMany(mappedBy="location", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SuperheroLocation> Locations;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "latitudeId")
    private Latitude latitude;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "longitudeId")
    private Longitude longitude;

	@Override
	public int hashCode() {
		return Objects.hash(address, description, latitude, locationId, longitude, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(address, other.address) && Objects.equals(description, other.description)
				&& Objects.equals(latitude, other.latitude) && locationId == other.locationId
				&& Objects.equals(longitude, other.longitude) && Objects.equals(name, other.name);
	}

}
