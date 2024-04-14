package ch.bohbohb.landonhotel.web.controller;

import ch.bohbohb.landonhotel.data.entity.Room;
import ch.bohbohb.landonhotel.data.repository.RoomRepository;
import ch.bohbohb.landonhotel.web.exception.BadRequestException;
import ch.bohbohb.landonhotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rooms")
public class RoomApiController {
    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room putRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable(name = "id") Long id) {
        Optional<Room> byId = roomRepository.findById(id);
        return byId.orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable(name = "id") Long id, @RequestBody Room room) {
        if(id != room.getId()) {
            throw new BadRequestException();
        }
        roomRepository.save(room);
        return room;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable(name = "id") Long id) {
        roomRepository.deleteById(id);
    }
}
