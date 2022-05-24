package MAT.gominsageori.controller;


import MAT.gominsageori.transfer.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.transfer.RestaurantInfoSchema;
import MAT.gominsageori.transfer.recommendationSchema;
import MAT.gominsageori.service.RestaurantService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    private RestaurantService restaurantService;

    @Autowired
    public ApiController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @ApiOperation(
            value = "식당 추천정보 조회"
            , notes = "characteristic , location , franchise여부를 받아 추천 식당을 받는다.",
            response = recommendationSchema.class
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "characteristic"
                            , value = "음식 특징 "
                            , required = true
                            , dataType = "string list"
                            , paramType = "query"
                            , defaultValue = "None"
                            , example = "ex)?characteristic=soup,spicy / 가능 값 : [soup,spicy,sweet,hot,meat,noodle,rice,bread]"
                    )
                    ,
                    @ApiImplicitParam(
                            name = "location"
                            , value = "식당위치"
                            , required = true
                            , dataType = "string , 신촌/서울대입구/숭실대"
                            , paramType = "query"
                            , defaultValue = "None"),
                    @ApiImplicitParam(
                            name = "franchise"
                            , value = "프렌차이즈 여부"
                            , required = true
                            , dataType = "boolean"
                            , paramType = "query"
                            , defaultValue = "None")
            }

    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    response = recommendationSchema.class,
                    message = ""
            )
        }

    )

    @ResponseBody
    @GetMapping("/recommendation")
    public ResponseEntity<recommendationSchema> Recommend(@ModelAttribute RecommandParam param){
        try{
            List<Restaurant> restaurant = restaurantService.recommandRestaurant(param);
            recommendationSchema payload = new recommendationSchema();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> address = new ArrayList<>();
            for( int i = 0 ; i<restaurant.size() ; i++){
                Restaurant tosend_restaurant = restaurant.get(i);
                names.add(tosend_restaurant.getName());
                address.add(tosend_restaurant.getFullAddress());
            }
            payload.setSize(restaurant.size());
            payload.setName(names);
            payload.setAddress(address);
            return ResponseEntity.status(200).body(payload);
        }
        //추천 알고리즘 호출 및 리턴값 받기
        catch(Exception err){
            recommendationSchema payload = new recommendationSchema();
            payload.setSize(0);
            return ResponseEntity.status(204).body(payload);
        }
    }

    @ResponseBody
    @GetMapping("/restaurant_info/{id}")
    public ResponseEntity<RestaurantInfoSchema>restaurantInfo(@RequestParam Long id){
        Optional <Restaurant> findResult = restaurantService.findOnebyId(id);
        RestaurantInfoSchema payload = new RestaurantInfoSchema();
        if( findResult.isPresent() ){
            payload.setRIschemaFromRestaurant(findResult.get());
            return ResponseEntity.status(200).body(payload);
        }
        else{
            return ResponseEntity.status(204).body(new RestaurantInfoSchema());
        }

    }

}