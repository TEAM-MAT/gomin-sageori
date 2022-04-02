package MAT.gominsageori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    @GetMapping("/restaurant")
    public String restaurant() { return "restaurant"; }
}
