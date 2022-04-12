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
    public ResponseEntity<List> Recommend(@RequestBody RecommandParam param){
        List<Restaurant> restaurants = restaurantService.recommandRestaurant(param);
        //추천 알고리즘 호출 및 리턴값 받기
        if(restaurants.size()>0){
            return ResponseEntity.status(200).body(restaurants);
        }
        else{
            return ResponseEntity.status(500).body(restaurants);
        }
    }

}
