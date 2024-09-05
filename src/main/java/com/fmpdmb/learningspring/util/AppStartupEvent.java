package com.fmpdmb.learningspring.util;

import java.sql.Date;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.fmpdmb.learningspring.data.*;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;

    public AppStartupEvent(RoomRepository roomRepository, 
                            ReservationRepository reservationRepository,
                            GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        
        log.info("onApplicationEvent - begin");

        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);

        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);
        
        
        Date date = Date.valueOf("2022-01-01");
        
        this.reservationRepository.findByDate(date).forEach(r -> log.info("reservation found: {}", r));
        
        log.info("onApplicationEvent - end");
    }

}
