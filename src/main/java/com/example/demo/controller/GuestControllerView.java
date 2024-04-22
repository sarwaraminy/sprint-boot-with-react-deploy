package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.data.Guest;
import com.example.demo.services.GuestService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path="/guests/view")
public class GuestControllerView {

	@Autowired GuestService guestService;
	
	//get all guests data and render in HTML
	@GetMapping
	public String viewAllGuest(Model model) {
		model.addAttribute("guestList", guestService.getAllGuests());
		return "guests";
	}
	
	//get guests based on ID
	@GetMapping("/{id}")
	public String viewGuestById(@PathVariable long id, Model model) {
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			model.addAttribute("guestList", guest);
			return "guests";
		}else {
			model.addAttribute("guestList", "");
			return "guests";
		}
		
	}
	
	//show the add page
	@GetMapping("/add")
	public String showAddGuest(Model model) {
		model.addAttribute("guest",  new Guest());
		return "add-guest";
	}
	//add value into Guest
	@PostMapping("/add")
	public String createGuest(@ModelAttribute Guest guest, RedirectAttributes redirectAttributes) {
	    try {
	        guestService.saveGuest(guest);
	        redirectAttributes.addFlashAttribute("successMessage", "Guest added successfully.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to add guest. Please try again.");
	        e.printStackTrace(); // Log the exception for debugging purposes
	    }
	    return "redirect:/guests/view";
	}

	
	//show the edit page
	@GetMapping("/edit/{id}")
	public String showUpdateGuest(@PathVariable long id, Model model) {
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			model.addAttribute("guest", guest);
			return "edit-guest";
		}else {
			return "redirect:/guests/view";
		}
	}
	//update records
	@PostMapping("/edit")
	public String updateGuest(@ModelAttribute Guest guest, RedirectAttributes redirectAttributes) {
	    try {
	        guestService.saveGuest(guest);
	        redirectAttributes.addFlashAttribute("successMessage", "Guest updated successfully.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to update guest. Please try again.");
	        e.printStackTrace(); // Log the exception for debugging purposes
	    }
	    return "redirect:/guests/view";
	}

	
	//delete a guest
	@PostMapping("/delete/{id}")
	public String deleteGuest(@PathVariable long id) {
		Guest guest = guestService.getGuestById(id);
		if(guest != null) {
			guestService.deleteGuest(id);
		}
		return "redirect:/guests/view";
	}
}
