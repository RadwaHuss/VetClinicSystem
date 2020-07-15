package org.eme.petrestfulapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="OWNERS")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Length(min = 3, max = 50)
    @Pattern(regexp = "[a-zA-Z ]*",message = "Not a valid name!")
    @Column(name = "NAME",nullable = false, length = 50, updatable = false, unique = true)
    private String name;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Pattern(regexp = "01[012]\\d{8}",message = "Not a valid number!")
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "GENDER")
    private Gender gender;

    @NotBlank
    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pet> pets = new HashSet<>(0);

    public Owner() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

}
