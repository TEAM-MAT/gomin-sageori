package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.transfer.RestaurantInfoSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> restaurant()
    {
        return ResponseEntity.status(200).body(restaurantService.findAll());
    }

    //식당 정보 API
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Object> restaurantInfo(@PathVariable Long id){
        HashMap<String, Object> findResult = restaurantService.findOneById(id);
        RestaurantInfoSchema payload = new RestaurantInfoSchema();
        if (findResult.get("result").getClass() == String.class) {
            return ResponseEntity.status(404).body("no Restaurant Like that");
        }
        else{
            Restaurant temp = (Restaurant) findResult.get("result");
            payload.setRiSchemaFromRestaurant(temp);
            return ResponseEntity.status(200).body(payload);
        }
    }
}
