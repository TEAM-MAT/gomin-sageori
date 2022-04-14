package MAT.gominsageori.controller;


import MAT.gominsageori.domain.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.RestaurantService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private RestaurantService restaurantService;
    @ResponseBody
    @GetMapping("/recommendation")
    public ResponseEntity<HashMap> Recommend(@RequestBody RecommandParam param){
        try{
            List<Restaurant> restaurant = restaurantService.recommandRestaurant(param);
            HashMap<String , String> payload = new HashMap<String , String>();
            for( int i = 0 ; i<restaurant.size() ; i++){//restaurant정보 hashmap으로 변환.
                String index = Integer.toString(i);
                Restaurant tosend_restaurant = restaurant.get(i);
                payload.put(index + "id" , Long.toString(tosend_restaurant.getId()));
                payload.put(index + "name", tosend_restaurant.getName());
                payload.put(index + "address" , tosend_restaurant.getAddress().getfulladdress());
            }
            return ResponseEntity.status(200).body(payload);
        }
        //추천 알고리즘 호출 및 리턴값 받기
        catch(Exception err){
            HashMap<String,String> payload = new HashMap<>();
            payload.put("msg", "no result");
            System.out.println(err.getMessage());
            return ResponseEntity.status(404).body(payload);
        }
    }

}
