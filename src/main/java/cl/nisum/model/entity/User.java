package cl.nisum.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import cl.nisum.utils.Const;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private UUID id;
			
    @NotBlank(message = "El nombre es un dato requerido")    
	private String name;
	
    @NotBlank(message = "El nombre de usuario es un dato requerido")
    @Size(message = "El nombre de usuario debe tener un mínimo de 6 caracteres y un máximo de 30",  min = 6, max = 30)
	private String username;
	
	private String password;
	
	@NotBlank(message = "El email es un dato requerido")
	private String email;
	
	private String token;
	
	private Integer status; 
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	@Column(name="last_login")
	private LocalDateTime lastLogin;
	
	@JsonIgnoreProperties(value = {"user"}, allowSetters = true)  
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<@Valid Phone> phones;
	
		
	public User() {
		super();
		this.phones = new ArrayList<Phone>();
	}

	@PrePersist
	public void prePersist() {
		this.created = LocalDateTime.now();
		this.modified = LocalDateTime.now();
		this.lastLogin = LocalDateTime.now();
		this.status = Const.USER_STATUS_ACTIVE;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.modified = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones.clear();
		phones.forEach(this::addPhone);
	}

	
	public void addPhone(Phone phone) {
		phone.setUser(this);		
		this.phones.add(phone);
	}
	
	public void removePhone(Phone phone) {		
		this.phones.remove(phone);
		phone.setUser(null);
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	

}
