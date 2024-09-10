package com.fmpdmb.learningspring.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.fmpdmb.learningspring.data.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, 
                                GuestRepository guestRepository,
                                ReservationRepository reservationRepository) {
        super();
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });
        
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        
        return roomReservations;
    }
    
    public List<Room> getRooms() {
        log.info("get rooms");
        return StreamSupport.stream(this.roomRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Room::getRoomNumber))
                .toList();
    }
    
    public List<Guest> getGuests(String fuzzyLastName) {
        log.info("get guests: {}", fuzzyLastName);
        
        List<Guest> guests = null;
        if (fuzzyLastName == null) {
            guests = StreamSupport.stream(this.guestRepository.findAll().spliterator(), false)
                    .sorted(Comparator.comparing(Guest::getLastName).thenComparing(Guest::getFirstName))
                    .toList();
        }
        else {
            guests = this.guestRepository.findByLastNameContaining(fuzzyLastName);
        }
        
        return guests;
    }
    
    public boolean addGuest(Guest guest) {
        
        Guest existingGuest = this.guestRepository.findByFirstNameAndLastName(guest.getFirstName(), guest.getLastName());
        if (existingGuest == null) {
            Guest savedGuest = this.guestRepository.save(guest);
            log.info("new guest saved: {}", savedGuest);
            return true;
        }
        else {
            log.info("found existing guest {}", existingGuest);
            return false;
        }
        
    }
}

