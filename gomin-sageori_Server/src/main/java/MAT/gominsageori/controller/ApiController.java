package MAT.gominsageori.controller;


import MAT.gominsageori.domain.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.RestaurantService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private RestaurantService restaurantService;
    @ResponseBody
    @GetMapping("/recommedation")
    public ResponseEntity<Restaurant> Recommend(@RequestBody RecommandParam param){
        try{
            Restaurant restaurant = restaurantService.recommandRestaurant(param);
            return ResponseEntity.status(200).body(restaurant);
        }
        //추천 알고리즘 호출 및 리턴값 받기
        catch(err){
            Restaurant returnres = new Restaurant();
            return ResponseEntity.status(500).body(returnres);
        }
    }

}
