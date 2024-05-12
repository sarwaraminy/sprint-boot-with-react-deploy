package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.Room;
import com.example.demo.output.ExcelToRoomUtility;
import com.example.demo.services.RoomService;

@RestController
@RequestMapping(path="/rooms/api")
@CrossOrigin(origins = "http://localhost:3000/")
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
	
	//load excel content to Room table:
	@PostMapping("/excel/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
		String message = "";
		if(ExcelToRoomUtility.hasExcelFormat(file)) {
			try {
				roomService.loadExcelToRoom(file);
				message = "The Excel file is uploaded: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch(Exception e) {
				message = "The Excel file is not upload: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		message = "Please upload an Excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	// show the uploaded file in view
	@GetMapping("/excel/room-list")
	public ResponseEntity<?> getRooms(){
		Map<String, Object> respRoom = new LinkedHashMap<String, Object>();
		List<Room> roomList = roomService.getAllRooms();
		if(!roomList.isEmpty()) {
			respRoom.put("status", 1);
			respRoom.put("data", roomList);
			return new ResponseEntity<>(respRoom, HttpStatus.OK);
		}else {
			respRoom.clear();
			respRoom.put("status", 0);
			respRoom.put("message", "Data is not found");
			return new ResponseEntity<>(respRoom, HttpStatus.NOT_FOUND);
		}
	}
}
