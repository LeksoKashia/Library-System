package com.example.library.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "librarian", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Librarian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;

	// Optional Fields
	@Column(name = "contact_info")
	private String contactInfo;

	@Column(name = "membership_status")
	private String membershipStatus;

	private String role;

	@OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> books;

	@OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Borrow> borrowBooks;

	public Librarian() {

	}

	public Librarian(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
