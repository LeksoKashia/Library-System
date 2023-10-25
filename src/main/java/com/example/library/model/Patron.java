package com.example.library.model;

import javax.persistence.*;

@Entity
@Table(name =  "patron")
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInfo;
    private String membershipStatus;

    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    public Patron() {

    }
    public Patron(String name, String contactInfo, String membershipStatus, Librarian librarian) {
        super();
        this.name = name;
        this.contactInfo = contactInfo;
        this.membershipStatus = membershipStatus;
        this.librarian = librarian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}
