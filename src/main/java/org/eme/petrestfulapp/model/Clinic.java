package org.eme.petrestfulapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CLINICS")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Length(min = 3, max = 50)
    @Pattern(regexp = "[a-zA-Z ]*", message = "Not a valid name!")
    @Column(name = "NAME",nullable = false, length = 50, updatable = false, unique = true)
    private String name;


    @NotBlank
    @Column(name = "ADDRESS")
    private String address;


    @NotBlank
    @Pattern(regexp = "01[012]\\d{8}",message = "Not a valid number!")
    @Column(name = "PHONE")
    private String phone;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @URL
    @Column(name = "FACEBOOK",length = 500)
    private String facebook;

    @URL
    @Column(name = "TWITTER",length = 500)
    private String twitter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Doctor> doctors = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Visit> visits = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    private Set<WorkingDay> workingDays = new HashSet<>(0);

    public Clinic() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public Set<WorkingDay> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Set<WorkingDay> workingDays) {
        this.workingDays = workingDays;
    }
}
