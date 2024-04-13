package ch.bohbohb.landonhotel.data.repository;

import ch.bohbohb.landonhotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuestRepository extends JpaRepository<Guest, Long> {

}
