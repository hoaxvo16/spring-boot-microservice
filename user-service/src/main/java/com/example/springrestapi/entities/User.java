package com.example.springrestapi.entities;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
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

    public User(String lastName, String email, String phone, String firstName) {
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.createDate = Instant.now();
    }
}
