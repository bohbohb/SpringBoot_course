package ch.bohbohb.landonhotel.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)

    public String getWelcome(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        String greeting = "Hello";
        return greeting + name;
    }
}

