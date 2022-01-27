package com.tenniscourts.guests;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping(path="/api/guest/registerGuest")
    public ResponseEntity<Void> registerGuest( @RequestBody String guestName) {
        return ResponseEntity.created(locationByEntity(guestService.registerGuest(guestName).getId())).build();
    }

    @PostMapping(path="/api/guest/updateGuestDetails")
    public ResponseEntity<Void> updateGuestDetails( @ RequestBody GuestDTO guest) {
        return ResponseEntity.created(locationByEntity(guestService.updateGuest(guest.getId(), guest.getName()).getId())).build();
    }

    @DeleteMapping(path="/api/guest/deleteGuestDetails")
    public ResponseEntity<?>  deleteGuestDetails( @RequestParam String id) {
       	String  msg="Id "+id+ "deleted successfully";
    	try {
    		guestService.deleteGuestByID(Long.parseLong(id));
    		return ResponseEntity.noContent().build();
		} catch (Exception e) {
			msg="Id "+id+ "failed to delete"+e.getMessage();
			return ResponseEntity.notFound().build();
		}
    }
   
    @GetMapping(path="/api/guest/listAllGuest")
    public ResponseEntity<List<GuestDTO>> findAllGuest() {
        return ResponseEntity.ok(guestService.findAllGuest());
    }

    @GetMapping(path="/api/guest/findGuestByID")
    public ResponseEntity<GuestDTO> findGuestByID( @RequestParam String id) {
        return ResponseEntity.ok(guestService.findGuestByID(new Long(id)));
    }

    @GetMapping(path="/api/guest/findGuestByName")
    public ResponseEntity<List<GuestDTO>> findGuestByName( @RequestParam String Name) {
        return ResponseEntity.ok(guestService.findByName(Name));
    }

   
}
