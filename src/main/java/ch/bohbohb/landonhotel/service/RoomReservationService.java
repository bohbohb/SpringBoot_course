package ch.bohbohb.landonhotel.service;

import ch.bohbohb.landonhotel.data.entity.Guest;
import ch.bohbohb.landonhotel.data.entity.Reservation;
import ch.bohbohb.landonhotel.data.repository.GuestRepository;
import ch.bohbohb.landonhotel.data.repository.ReservationRepository;
import ch.bohbohb.landonhotel.data.repository.RoomRepository;
import ch.bohbohb.landonhotel.service.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomReservationService {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate) {
        Date date = null;
        if (StringUtils.isNotEmpty(reservationDate)) {
            date = Date.valueOf(reservationDate);
        } else {
            date = new Date(new java.util.Date().getTime());
        }
        Map<Long, RoomReservation> roomReservations = new HashMap<>();

        this.roomRepository
                .findAll()
                .forEach(room ->
                        roomReservations.put(room.getId(), RoomReservation.builder()
                                .roomId(room.getId())
                                .roomName(room.getName())
                                .roomNumber(room.getRoomNumber())
                                .build()));

        List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(date);
        reservations.forEach(reservation -> {
                RoomReservation.RoomReservationBuilder roomReservation = roomReservations.remove(reservation.getRoomId()).toBuilder()
                        .reservationId(reservation.getId())
                        .reservationDate(reservation.getReservationDate().toString());
                Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
                roomReservation.guestId(guest.get().getId())
                        .lastName(guest.get().getLastName());
                roomReservations.put(roomReservation.build().getRoomId(),roomReservation.build());
        });
        return roomReservations.values().stream().toList();
    }
}
