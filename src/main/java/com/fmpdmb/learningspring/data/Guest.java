package com.fmpdmb.learningspring.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "GUEST")
public class Guest {

//	  GUEST_ID BIGSERIAL PRIMARY KEY,
//	  FIRST_NAME VARCHAR(64),
//	  LAST_NAME VARCHAR(64),
//	  EMAIL_ADDRESS VARCHAR(64),
//	  ADDRESS VARCHAR(64),
//	  COUNTRY VARCHAR(32),
//	  STATE VARCHAR(12),
//	  PHONE_NUMBER VARCHAR(24)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GUEST_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STATE")
    private String state;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
