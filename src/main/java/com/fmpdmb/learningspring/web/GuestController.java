package com.fmpdmb.learningspring.web;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmpdmb.learningspring.business.ReservationService;
import com.fmpdmb.learningspring.data.Guest;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/guests")
@Slf4j
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(@RequestParam(value="lastName", required=false) String lastName, Model model) {
        log.info("get guests - lastName:{}", lastName);
                
        List<Guest> guests = reservationService.getGuests(lastName);
        
        model.addAttribute("guests", guests);
        model.addAttribute("date", new Date());
        model.addAttribute("guestCount", guests.size());
        model.addAttribute("searchString", Objects.toString(lastName, "all"));
        
        return "guests";
    }

}
