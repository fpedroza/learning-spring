package com.fmpdmb.learningspring.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="ROOM")
public class Room {

//	  ROOM_ID BIGSERIAL PRIMARY KEY,
//    NAME VARCHAR(16) NOT NULL,
//    ROOM_NUMBER CHAR(2) NOT NULL UNIQUE,
//    BED_INFO CHAR(2) NOT NULL
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ROOM_ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ROOM_NUMBER")
	private String roomNumber;
	
	@Column(name="BED_INFO")
	private String bedInfo;	
}
