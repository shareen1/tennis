package com.tenniscourts.guests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.context.ContextConfiguration;

import com.sun.research.ws.wadl.Option;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GuestService.class)
@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

	@InjectMocks
	GuestService guestService;
	
	@Spy
	private GuestMapper guestMapper = Mappers.getMapper(GuestMapper.class);

	@Mock
	GuestRepository guestRepository;

	Random random;

	
	@Test
	public void findAllGuestTest() {
		String guestName = "Shareen";
		GuestDTO guest = new GuestDTO();
		guest.setId(123L);
		guest.setName(guestName);
		Guest guest2 = new Guest();
		guest2.setId(123L);
		guest2.setName(guestName);
		List< Guest> list= new ArrayList<Guest>();
		list.add(guest2);
		List< GuestDTO> listDTO= new ArrayList<GuestDTO>();
		listDTO.add(guest);
		when(guestRepository.findAll()).thenReturn(list);
		Assert.assertEquals(guestService.findAllGuest().get(0).getId(),listDTO.get(0).getId());
	}
	@Test
	public void registerGuesttest() {
		
		String guestName = "Shareen";
		GuestDTO guest = new GuestDTO();
		guest.setId(123L);
		Guest guest2 = new Guest();
		guest2.setId(123L);
		guest2.setName(guestName);
		Assert.assertEquals( guest.getName(),guestService.registerGuest(guestName));
	}

	@Test
	public void findByNameTest() {
		String guestName = "Shareen";
		GuestDTO guest = new GuestDTO();
		guest.setId(123L);
		guest.setName(guestName);
		Guest guest2 = new Guest();
		guest2.setId(123L);
		guest2.setName(guestName);
		List< Guest> list= new ArrayList<Guest>();
		list.add(guest2);
		List< GuestDTO> listDTO= new ArrayList<GuestDTO>();
		listDTO.add(guest);
		when(guestRepository.findByName(guestName)).thenReturn(list);
		Assert.assertEquals(guestService.findByName(guestName).get(0).getId(),listDTO.get(0).getId());
	}
	@Test
	public void findByIdTest() {
		String guestName = "Shareen";
		GuestDTO guest = new GuestDTO();
		guest.setId(123L);
		guest.setName(guestName);
		Guest guest2 = new Guest();
		guest2.setId(123L);
		guest2.setName(guestName);
		List< Guest> list= new ArrayList<Guest>();
		list.add(guest2);
		List< GuestDTO> listDTO= new ArrayList<GuestDTO>();
		listDTO.add(guest);
		Optional<Guest> option = Optional.ofNullable(guest2);
		when(guestRepository.findById(11L)).thenReturn(option);
		Assert.assertEquals(guestService.findGuestByID(11L).getName(),guest.getName());
	}
	
}
