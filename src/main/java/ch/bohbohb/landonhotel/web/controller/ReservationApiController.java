package ch.bohbohb.landonhotel.web.controller;

import ch.bohbohb.landonhotel.data.entity.Reservation;
import ch.bohbohb.landonhotel.data.repository.ReservationRepository;
import ch.bohbohb.landonhotel.web.exception.BadRequestException;
import ch.bohbohb.landonhotel.web.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor()
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    @GetMapping()
    public List<Reservation> getAll(@RequestParam Optional<Date> date) {
        return date.isPresent()
                ? reservationRepository.findAllByReservationDate(date.get())
                : reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return reservationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateById(@PathVariable Long id, Reservation reservation) {
        if (id.equals(reservation.getId()))
            throw new BadRequestException();
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
}

