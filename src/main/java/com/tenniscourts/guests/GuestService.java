package com.tenniscourts.guests;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestService {

	private final GuestRepository guestRepository;
	private final GuestMapper guestMapper;

	public GuestDTO registerGuest(String guestName) {
		Guest guest = getGuestDetals(guestName,new Random().nextLong());
		return guestMapper.map(guestRepository.save(guest));

	}

	private Guest getGuestDetals(String guestName, Long id) {
		Guest guest = new Guest();
		guest.setId(id);
		guest.setName(guestName);
		return guest;
	}

	public List<GuestDTO> findAllGuest() {
		return guestMapper.map(guestRepository.findAll());
	}
	public GuestDTO findGuestByID(Long id) {		
		return guestMapper.map(guestRepository.findById(id).get());
	}

	public GuestDTO updateGuest(Long id, String newName) {	
		return guestMapper.map(guestRepository.save(getGuestDetals(newName, id)));
	}

	public void deleteGuestByID(Long id) throws Exception {

		guestRepository.delete(guestRepository.findById(id).get());
	}

	public List<GuestDTO> findByName(String name) {
	
		return  guestMapper.map(guestRepository.findByName(name));
	}
}
