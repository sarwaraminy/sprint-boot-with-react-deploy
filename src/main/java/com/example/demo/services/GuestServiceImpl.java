package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public List<Guest> getAllGuests() {
		Iterable<Guest> iterable = guestRepository.findAll();
		List<Guest> guestList = new ArrayList<>();
		iterable.forEach(guestList::add);
		return guestList;
	}

	@Override
	public Guest getGuestById(long id) {
		return guestRepository.findById(id).orElse(null);
	}

	@Override
	public Guest saveGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public void deleteGuest(long id) {
		guestRepository.deleteById(id);
		
	}

}
