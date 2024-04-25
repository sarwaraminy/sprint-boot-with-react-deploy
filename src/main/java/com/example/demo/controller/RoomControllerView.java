package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.data.Room;
import com.example.demo.output.RoomToExcel;
import com.example.demo.output.RoomToPDF;
import com.example.demo.services.RoomService;

import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping(path="/rooms/view")
public class RoomControllerView {

	@Autowired RoomService roomService;

	@Autowired RoomToExcel roomToExcel;
	
	@Autowired RoomToPDF roomToPDF;
	
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
	
	//show the add view of room
	@GetMapping("/add")
	public String showAddRoom(Model model) {
		model.addAttribute("room", new Room());
		return "add-room";
	}
	//add value into Room
	@PostMapping("/add")
	public String createRoom(@ModelAttribute Room room) {
		roomService.saveRoom(room);
		return "redirect:/rooms/view";
	}
	
	//show the edit page for room
	@GetMapping("/edit/{id}")
	public String showRoomEdit(@PathVariable long id, Model model) {
		Room room  = roomService.getRoomById(id);
		if(room != null) {
			model.addAttribute("room", room);
			return "edit-room";
		} else {
			return  "redirect:rooms/view";
		}
	}
		
		//update records
		@PostMapping("/edit")
		public String updateRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {
			try {
				roomService.saveRoom(room);
				redirectAttributes.addFlashAttribute("successMessage", "Room updated successfully.");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to update room.");
				e.printStackTrace();
			}
			return "redirect:/rooms/view";
		}
		
		//delete a room
		@PostMapping("/delete/{id}")
		public String deleteGuest(@PathVariable long id) {
			Room room = roomService.getRoomById(id);
			if(room != null) {
				roomService.deleteRoom(id);
			}
			return "redirect:/rooms/view";
		}

		// create a method that handle the output to MS Excel
		@GetMapping("/excel")
		public ResponseEntity<byte[]> createExcelOutput() throws IOException {
			List<Room> roomList = roomService.getAllRooms();
            byte[] excelBytes = roomToExcel.generateExcel(roomList);

            // Set Excel content type and headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "room_data.xlsx");
    
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
		}
		
		// Your existing controller method
	    @GetMapping("/pdf")
	    public ResponseEntity<byte[]> createPDFOutput() throws IOException {
	        List<Room> roomList = roomService.getAllRooms();
	        byte[] pdfBytes = roomToPDF.generatePDF(roomList);

	        // Set PDF content type and headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", "room_data.pdf");

	        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	    }
		
	
}
