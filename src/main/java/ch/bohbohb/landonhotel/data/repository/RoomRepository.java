package ch.bohbohb.landonhotel.data.repository;

import ch.bohbohb.landonhotel.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findRoomByRoomNumberIgnoreCase(String roomNumber);

}

