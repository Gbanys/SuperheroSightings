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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int organizationId;
	
	@Column(columnDefinition="varchar(50)")
	private String name;
	
	@Column(columnDefinition="varchar(255)")
	private String description;
	
	@Column(columnDefinition="varchar(255)")
	private String address;
	
	@Column(columnDefinition="varchar(50)")
	private String phoneNumber;
	
	@Column(columnDefinition="varchar(100)")
	private String emailAddress;
	
	@OneToMany(mappedBy="organization", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SuperheroOrganization> Organizations;

	@Override
	public int hashCode() {
		return Objects.hash(address, description, emailAddress, name, organizationId, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		return Objects.equals(address, other.address) && Objects.equals(description, other.description)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(name, other.name)
				&& organizationId == other.organizationId && Objects.equals(phoneNumber, other.phoneNumber);
	}


}
