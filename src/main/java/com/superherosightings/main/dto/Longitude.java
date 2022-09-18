package com.superherosightings.main.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="longitude")
public class Longitude {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int longitudeId;
	private int degrees;
	private int minutes;
	
	@Column(precision=6, scale=4)
	private double seconds;
	
	@Column(columnDefinition="varchar(5)")
	private String direction;
	
	@OneToOne(mappedBy = "longitude")
    private Location location;

	@Override
	public int hashCode() {
		return Objects.hash(degrees, direction, location, longitudeId, minutes, seconds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Longitude other = (Longitude) obj;
		return degrees == other.degrees && Objects.equals(direction, other.direction)
				&& Objects.equals(location, other.location) && longitudeId == other.longitudeId
				&& minutes == other.minutes
				&& Double.doubleToLongBits(seconds) == Double.doubleToLongBits(other.seconds);
	}
}
