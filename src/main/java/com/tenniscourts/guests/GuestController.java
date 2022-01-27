package com.tenniscourts.guests;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.config.BaseRestController;

import lombok.AllArgsConstructor;
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RestController
public class GuestController  extends BaseRestController {

    private final GuestService guestService;
    
    @PostMapping(path="/api/guest/RegisterGuest")
    public ResponseEntity<GuestDTO> gegisterGuest( @RequestBody String guestName) {
        return ResponseEntity.ok(guestService.registerGuest(guestName));
    }
    @GetMapping(path="/api/guest/findAllGuest")
    public ResponseEntity<List<GuestDTO>> findAllGuest() {
        return ResponseEntity.ok(guestService.findAllGuest());
    }
    @GetMapping(path="/api/guest/findGuestByID")
    public ResponseEntity<GuestDTO> findGuestByID( @RequestParam String id) {
        return ResponseEntity.ok(guestService.findGuestByID(new Long(id)));
    }

}
