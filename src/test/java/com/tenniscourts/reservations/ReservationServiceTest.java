package com.tenniscourts.reservations;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.tenniscourts.TennisCourt;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

	@InjectMocks
	ReservationService reservationService;

	@Spy
	private ReservationMapper reservationMapper = Mappers.getMapper(ReservationMapper.class);

	@Mock
	ReservationRepository reservationRepository;

	@Test
	public void getRefundValueFullRefund() {
		Schedule schedule = new Schedule();

		LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);

		schedule.setStartDateTime(startDateTime);

		Assert.assertEquals(
				reservationService
						.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build()),
				new BigDecimal(10));
	}

	@Test
	public void bookReservationTest() {
		Schedule schedule = new Schedule();
		schedule.setId(10L);
		TennisCourt tenniesCourt = new TennisCourt();
		tenniesCourt.setName("MyTenniesCourt");
		schedule.setTennisCourt(tenniesCourt);
		LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);

		schedule.setStartDateTime(startDateTime);
		CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder().guestId(100L)
				.scheduleId(schedule.getId()).build();
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setReservationStatus("READY_TO_PLAY");
		reservationDTO.setScheduledId(schedule.getId());
		reservationDTO.setValue(new BigDecimal(10L));
		Guest myGuest = new Guest();
		myGuest.setName("Shareen");
		Reservation reservation = Reservation.builder().schedule(schedule).value(new BigDecimal(10L))
				.refundValue(new BigDecimal(10L)).guest(myGuest).build();
		when(reservationMapper.map(createReservationRequestDTO)).thenReturn(reservation);
		when(reservationMapper.map(reservation)).thenReturn(reservationDTO);
		when(reservationService.updateReservation(reservation, BigDecimal.ZERO, ReservationStatus.READY_TO_PLAY))
				.thenReturn(reservation);

		Assert.assertEquals(reservationService.bookReservation(createReservationRequestDTO), reservationDTO);

	}
}