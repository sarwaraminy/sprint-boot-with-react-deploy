package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.data.Guest;
import com.example.demo.data.Room;
import com.example.demo.services.RoomService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path="/rooms/view")
public class RoomControllerView {

	@Autowired RoomService roomService;
	
	//show all records from Room table
	@GetMapping
	public String viewAllRooms(Model model) {
		model.addAttribute("roomList", roomService.getAllRooms());
		return "rooms";
	}
	
	//show records based on room id
	@GetMapping("/{id}")
	public String viewRoomById(@PathVariable(required = false) long id, Model model) {
		Room room = roomService.getRoomById(id);
		if(room != null) {
			model.addAttribute("roomList", room);
			return "rooms";
		}else {
			model.addAttribute("roomList", "");
			return "rooms";
		}
	}
	
	//add value into Guest
		@PostMapping("/add")
		public String createRoom(@ModelAttribute Room room) {
			roomService.saveRoom(room);
			return "redirect:/rooms/view";
		}
		
		//update records
		@PostMapping("/{id}/edit")
		public String updateGuest(@PathVariable long id,  @ModelAttribute Room roomDetail) {
			Room room = roomService.getRoomById(id);
			if(room != null) {
				room.setId(roomDetail.getId());
				room.setName(roomDetail.getName());
				room.setRoomNumber(roomDetail.getRoomNumber());
				room.setBedInfo(roomDetail.getBedInfo());
				
				roomService.saveRoom(room);
			}

			return "redirect:/rooms/view";
		}
		
		//delete a room
		@PostMapping("/{id}/delete")
		public String deleteGuest(@PathVariable long id) {
			Room room = roomService.getRoomById(id);
			if(room != null) {
				roomService.deleteRoom(id);
			}
			return "redirect:/guests/view";
		}
	
}
