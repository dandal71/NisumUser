package cl.nisum.model.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="phone")
public class Phone {

	@Id
	@GeneratedValue
	private UUID id;
	
	@NotBlank(message = "El número es un dato requerido")
	private String number;
	
	@NotBlank(message = "El código de area es un dato requerido")
	private String cityCode;
	
	@NotBlank(message = "El código de país es un dato requerido")
	private String countryCode;
	
	@JsonIgnoreProperties(value = { "phones" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else {
			if (obj instanceof Phone) {
				return (this.id != null && this.id.equals(((Phone) obj).getId()));
			} else {
				return false;
			}
		}
	}
	
}
