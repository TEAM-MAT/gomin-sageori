package MAT.gominsageori.controller;


import MAT.gominsageori.domain.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApiController {

    @GetMapping("/recommedation")
    @ResponseBody
    @RequestBody
    public List <Restaurant> Recommend()

}
