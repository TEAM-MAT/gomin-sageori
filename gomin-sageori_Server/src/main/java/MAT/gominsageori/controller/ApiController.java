package MAT.gominsageori.controller;


import MAT.gominsageori.domain.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.RestaurantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

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
            , notes = "characteristic , location , franchise여부 , 알러지 정보를 받아 추천 식당을 받는다."
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

    @ResponseBody
    @GetMapping("/recommendation")
    public ResponseEntity<HashMap> Recommend(@ModelAttribute RecommandParam param){
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