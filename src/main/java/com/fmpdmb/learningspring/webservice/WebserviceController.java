package com.fmpdmb.learningspring.webservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmpdmb.learningspring.business.ReservationService;
import com.fmpdmb.learningspring.data.Guest;
import com.fmpdmb.learningspring.data.Room;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class WebserviceController {
    
    private final ReservationService reservationService;

    public WebserviceController(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;
    }
    
    @GetMapping("/rooms")
    public List<Room> getRooms() {
       return this.reservationService.getRooms();
    }
    
    @GetMapping("/guests")
    public List<Guest> getGuests(@RequestParam(value="lastName", required=false) String lastName) {
        log.info("lastName: {}", lastName);
       return this.reservationService.getGuests(lastName);
    }
    
    @PostMapping("/guests")
    public boolean addGuest(@RequestBody Guest guest) {
        log.info("potential new guest: {}", guest);
        
        return this.reservationService.addGuest(guest);       
    }

}
