package ch.bohbohb.landonhotel;

import ch.bohbohb.landonhotel.data.entity.Room;
import ch.bohbohb.landonhotel.data.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;

    public CLRunner(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Room> room = roomRepository.findRoomByRoomNumberIgnoreCase("");
        System.out.println(room);
        roomRepository.findAll().forEach(System.out::println);
    }
}
