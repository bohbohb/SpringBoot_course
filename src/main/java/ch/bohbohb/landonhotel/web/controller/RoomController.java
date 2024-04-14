package ch.bohbohb.landonhotel.web.controller;

import ch.bohbohb.landonhotel.data.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private RoomRepository roomRepository;
    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("rooms", this.roomRepository.findAll());
        return "room-list";
    }
}
