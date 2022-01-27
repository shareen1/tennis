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
		Guest guest = new Guest();
		Long generatedLong = new Random().nextLong();
		guest.setId(generatedLong);
		guest.setName(guestName);
		return guestMapper.map(guestRepository.save(guest));

	}

	public List<GuestDTO> findAllGuest() {

		return guestMapper.map(guestRepository.findAll());
	}
	public GuestDTO findGuestByID(Long id) {
		
		return guestMapper.map(guestRepository.findById(id).get());
	}
}
