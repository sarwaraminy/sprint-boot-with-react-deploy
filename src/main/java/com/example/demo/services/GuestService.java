package com.example.demo.services;

import java.util.List;

import com.example.demo.data.Guest;

public interface GuestService {

	List<Guest> getAllGuests();
	Guest getGuestById(long id);
	Guest saveGuest(Guest guest);
	void deleteGuest(long id);
}
