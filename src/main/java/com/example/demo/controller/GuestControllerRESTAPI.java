package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Guest;
import com.example.demo.services.GuestService;

@RestController
@RequestMapping(path="/guests/api")
public class GuestControllerRESTAPI {

	@Autowired
	private GuestService guestService;
	
	//get all guests from Guest table as JSON
	@GetMapping
	public ResponseEntity<List<Guest>> getAllGuests(){
		List<Guest> guestList = guestService.getAllGuests();
		return ResponseEntity.ok(guestList);
	}
	
	//Get guests from Guest table based id
	@GetMapping("/{id}")
	public ResponseEntity<Guest> getGuestById(@PathVariable(required = false) long id){
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			return ResponseEntity.ok(guest);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Save data into Guest table
	@PostMapping
	public ResponseEntity<Guest> saveGuest(@RequestParam Guest guest){
		Guest createGuest = guestService.saveGuest(guest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createGuest);
	}
	
	//update guest table
	@PutMapping("/{id}")
	public ResponseEntity<Guest> updateGuest(@PathVariable long id, @RequestBody Guest guestDetail){
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			guest.setId(guestDetail.getId());
			guest.setFirstName(guestDetail.getFirstName());
			guest.setLastName(guestDetail.getLastName());
			guest.setCountry(guestDetail.getCountry());
			guest.setEmail(guestDetail.getEmail());
			guest.setPhone(guestDetail.getPhone());
			guest.setState(guestDetail.getState());
			Guest updateGuest = guestService.saveGuest(guest);
			return ResponseEntity.ok(updateGuest);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//delete a guest
	@DeleteMapping("/{id}")
	public ResponseEntity<Guest> deleteGuest(@PathVariable long id){
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			guestService.deleteGuest(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
