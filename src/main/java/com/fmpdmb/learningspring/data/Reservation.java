package com.fmpdmb.learningspring.data;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "RESERVATION")
public class Reservation {

//	  RESERVATION_ID BIGSERIAL PRIMARY KEY,
//	  ROOM_ID BIGINT NOT NULL,
//	  GUEST_ID BIGINT NOT NULL,
//	  RES_DATE DATE

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private long id;

    @Column(name = "ROOM_ID")
    private long roomId;

    @Column(name = "GUEST_ID")
    private long guestId;

    @Column(name = "RES_DATE")
    private Date date;
}
