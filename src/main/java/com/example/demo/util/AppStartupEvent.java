package com.example.demo.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;
import com.example.demo.data.Reservation;
import com.example.demo.data.ReservationRepository;
import com.example.demo.data.Room;
import com.example.demo.data.RoomRepository;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;
	
	public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Iterable<Room> rooms = this.roomRepository.findAll();
		rooms.forEach(System.out::println);
		Iterable<Guest> guests = this.guestRepository.findAll();
		guests.forEach(System.out::println);
		Iterable<Reservation> reservation = this.reservationRepository.findAll();
		reservation.forEach(System.out::println);
	}

}
