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

import com.example.demo.data.Room;
import com.example.demo.services.RoomService;

@RestController
@RequestMapping(path="/rooms/api")
public class RoomControllerRESTAPI {

	@Autowired
	private RoomService roomService;
	
	//this method sends data as JSON When /rooms is called in URL
	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms(){
		List<Room> rooms = roomService.getAllRooms();
		return ResponseEntity.ok(rooms);
	}
	
	//Lets crate data to get based on id from Room table and returns as JSON
	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable long id){
		Room room = roomService.getRoomById(id);
		if(room != null) {
			return ResponseEntity.ok(room);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//create a method that can save data into Room table
	@PostMapping
	public ResponseEntity<Room> createRoom(@RequestParam Room room){
		Room createRoom = roomService.saveRoom(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRoom);
	}
	
	//update room based on rood id
	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable long id, @RequestBody Room roomDetail){
		Room room = roomService.getRoomById(id);
		if(room != null) {
			room.setId(roomDetail.getId());
			room.setName(roomDetail.getName());
			room.setRoomNumber(roomDetail.getRoomNumber());
			room.setBedInfo(roomDetail.getBedInfo());
			Room updateRoom = roomService.saveRoom(room);
			return ResponseEntity.ok(updateRoom);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Create our delete request
	@DeleteMapping("/{id}")
	public ResponseEntity<Room> deleteRoom(@PathVariable long id){
		Room room = roomService.getRoomById(id);
		if(room != null) {
			roomService.deleteRoom(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
