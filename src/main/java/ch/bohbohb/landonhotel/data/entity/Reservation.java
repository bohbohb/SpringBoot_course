package ch.bohbohb.landonhotel.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private Long id;
    @Column(name="room_id")
    private Long roomId;
    @Column(name="guest_id")
    private Long guestId;
    @Column(name="res_date")
    private Date reservationDate;
}

