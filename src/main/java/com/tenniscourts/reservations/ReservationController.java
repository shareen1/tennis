package com.tenniscourts.reservations;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.config.BaseRestController;

import lombok.AllArgsConstructor;

@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RestController
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;
    
    @PostMapping(path = "/api/reservation/bookReservation")
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }
    
    @GetMapping(path = "/api/reservation/findReservation")
    public ResponseEntity<ReservationDTO> findReservation(@RequestParam Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }
    @PostMapping(path = "/api/reservation/cancelReservation")
    public ResponseEntity<ReservationDTO> cancelReservation(@RequestBody Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }
    @PostMapping(path = "/api/reservation/rescheduleReservation")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@RequestBody Long reservationId, Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
