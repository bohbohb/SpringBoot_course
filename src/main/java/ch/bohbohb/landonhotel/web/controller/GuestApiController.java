package ch.bohbohb.landonhotel.web.controller;

import ch.bohbohb.landonhotel.data.entity.Guest;
import ch.bohbohb.landonhotel.data.repository.GuestRepository;
import ch.bohbohb.landonhotel.web.exception.BadRequestException;
import ch.bohbohb.landonhotel.web.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/guests")
public class GuestApiController {
    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/{id}")
    public Guest getById(@PathVariable Long id){
        return guestRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping()
    public List<Guest> getAll(){
        return guestRepository.findAll();
    }

    @PostMapping
    public Guest putGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }

    @PutMapping("/{id}")
    public Guest updateById(@PathVariable Long id, @RequestBody Guest guest) {
        if(guest.getId().equals(id)) {
            throw new BadRequestException();
        }
        return guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        guestRepository.deleteById(id);
    }
}
