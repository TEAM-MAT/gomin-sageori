package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.transfer.RestaurantInfoSchema;
import MAT.gominsageori.transfer.recommendationSchema;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public void RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> restaurant()
    {
        return ResponseEntity.status(200).body(restaurantService.findAll());
    }

    //식당 정보 API
    @ApiOperation(
            value = "식당 정보 조회"
            , notes = "식당 id를 이용해 식당 정보를 받는다",
            response = Restaurant.class
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "restaurant Id"
                            , value = "식당 id "
                            , required = true
                            , dataType = "Long"
                            , paramType = "body"
                            , defaultValue = "None"
                            , example = "1"
                    )
            }

    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    response = Restaurant.class,
                    message = "restaurant 정보"
            )
    })
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
