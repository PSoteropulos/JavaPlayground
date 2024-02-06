package com.psoteropulos.random.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//	@NotBlank(message="Username is required!")
	@NotNull
	@Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
	private String name;

	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email.")
	private String email;

	//	@NotBlank(message="Password is required!")
	@NotNull
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;

	@Transient
	//	@NotBlank(message="Confirm Password is required!")
	@NotNull
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;

//	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
//	private List<Game> createdGames;

//	@OneToMany(mappedBy = "commenter", fetch = FetchType.LAZY)
//	private List<Mechanic> suggestedMechanics;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User {" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
//				", createdGames='" + createdGames + '\'' +
//				", suggestedMechanics='" + suggestedMechanics + '\'' +
				'}';
	}

	// generate fields and getters/setters

}
