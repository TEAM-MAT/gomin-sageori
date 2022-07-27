package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.domain.Stars;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.service.StarsService;
import MAT.gominsageori.transfer.RestaurantInfoSchema;
import MAT.gominsageori.transfer.recommendationSchema;
import MAT.gominsageori.transfer.starsUpdateParam;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;
    private StarsService starsService;
    private MemberService memberService;

    @Autowired
    public void RestaurantController(RestaurantService restaurantService, StarsService starsService, MemberService memberService) {
        this.restaurantService = restaurantService;
        this.starsService = starsService;
        this.memberService = memberService;
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

    //식당 별점 갱신 API
    @ApiOperation(
            value = "식당 별점 갱신"
            , notes = "식당 id를 이용해 별점을 갱신한다.",
            response = String.class
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    response = String.class,
                    message = "별점 갱신 완료"
            ),
            @ApiResponse(
                    code = 400,
                    response = String.class,
                    message = "식당 id가 잘못됨."
            )
    })
    @ResponseBody
    @PutMapping("/stars")
    public ResponseEntity<String> updateStars(@ModelAttribute starsUpdateParam param) {
        Object findResult = restaurantService.findOneById(param.getRestaurantId()).get("result");
        Optional member = memberService.findOneByUserId(param.getUserId());
        if(findResult.getClass() == String.class || member.isEmpty()) {
            //에러 발생. 존재하지 않음.
            return ResponseEntity.status(400).body("restaurant doesn't exist");
        }
        else {
            Restaurant restaurant = (Restaurant) findResult;
            try {
                Member temp = (Member) member.get();
                Stars stars = new Stars();
                stars.setStars(param.getStars());
                stars.setRestaurantPK(param.getRestaurantId());
                stars.setUserPK(temp.getId());
                starsService.giveStar(stars);
                return ResponseEntity.status(200).body(restaurant.getName());
            } catch (Exception ex){
                return ResponseEntity.status(500).body("Something got wrong");
            }
        }

    }

}
