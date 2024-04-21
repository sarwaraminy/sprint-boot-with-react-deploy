package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.Room;
import com.example.demo.data.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	//now implement our room services that is initialize in RoomService
	@Override
	public List<Room> getAllRooms(){ //get all records from Room table
		Iterable<Room> iterable = roomRepository.findAll();
		List<Room> roomList = new ArrayList<>();
		iterable.forEach(roomList::add);
		return roomList;
	}
	
	@Override
	public Room getRoomById(long id) { //Get data from Room table based on id
		return roomRepository.findById(id).orElse(null);
	}
	
	@Override
	public Room saveRoom(Room room) { // insert new records from form int Room table
		return roomRepository.save(room);
	}
	
	@Override
	public void deleteRoom(long id) {
		roomRepository.deleteById(id);
	}

}
