package com.masai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "First name can't be blank")
	private String first_name;
	
	@NotBlank(message = "Last name can't be blank")
	private String last_name;
	
	@Email(message = "Required valid email")
	@NotBlank(message = "Email can't be blank")
	private String email;
	
	@NotBlank(message = "Mobile can't be blank")
	@Pattern(regexp = "^[0-9]{10}", message = "mobile must be 10 digit number")
	private String mobile;
	
	@NotBlank(message = "Password can't be blank")
	@Size(min = 8, message = "Passwors must be minimum of 8 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).*$", message = "Password requirements not met")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotBlank(message = "Role can't be blank")
	private String role;
	
	@NotBlank(message = "Status can't be blank")
	private String status;
	
	
}
