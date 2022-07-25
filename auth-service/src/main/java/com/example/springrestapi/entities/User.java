package com.example.springrestapi.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false, length = 64)
    private UUID accountId;

    @Column(length = 64)
    private String lastName;

    @Column(length = 64)
    private String email;

    private String password;

    @Column(length = 10)
    private String phone;

    @Column(length = 8)
    private String firstName;

    @CreatedDate
    private Instant createDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> roles;

    public User(String lastName, String email, String phone, String firstName) {
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.createDate = Instant.now();
    }
}
