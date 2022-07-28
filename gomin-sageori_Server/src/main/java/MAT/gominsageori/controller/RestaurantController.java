package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.domain.Stars;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.service.StarsService;
import MAT.gominsageori.transfer.RestaurantInfoSchema;
import MAT.gominsageori.transfer.starsPostUpdateParam;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        try{
            Restaurant restaurant = restaurantService.findOneById(id);
            return ResponseEntity.status(200).body(restaurant);
        } catch (Exception ex) {
            return ResponseEntity.status(404).body("no Restaurant like that");
        }
    }

    //식당 별점 조회 API
    @ApiOperation(
            value = "식당 별점 조회"
            , notes = "식당 id와 유저id를 이용해 이전에 주었던 별점을 조회.",
            response = String.class
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    response = String.class,
                    message = "별점 조회 완료"
            ),
            @ApiResponse(
                    code = 404,
                    response = String.class,
                    message = "별점을 준 적이 없음"
            ),
            @ApiResponse(
                    code = 400,
                    response = String.class,
                    message = "식당 id가 잘못되었거나 userid가 잘못됨."
            )
    })
    @ResponseBody
    @GetMapping("/{restaurantId}/stars/user/{userId}")
    public ResponseEntity<Float> getStars(@PathVariable Long restaurantId, @PathVariable String userId) {
        Optional<Member> member;
        Restaurant restaurant;
        try{
            member = memberService.findOneByUserId(userId);
            restaurant = restaurantService.findOneById(restaurantId);
        } catch(Exception ex) {
            return ResponseEntity.status(400).body(0.0F);
        }
        if(member.isEmpty()) {
            return ResponseEntity.status(400).body(0.0F);
        }
        try {
            Stars stars = starsService.getStarByRestaurantAndUser(restaurantId, member.get().getId());
            return ResponseEntity.status(200).body(stars.getStars());
        } catch (Exception noStars) {
            return ResponseEntity.status(400).body(0.0F);
        }
    }



    //식당 별점 갱신 API
    @ApiOperation(
            value = "식당 별점 갱신"
            , notes = "식당 id와 유저 id를 이용해 별점을 갱신한다.",
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
    @PatchMapping("{restaurantId}/stars")
    public ResponseEntity<String> updateStars(@PathVariable Long restaurantId, @ModelAttribute starsPostUpdateParam param) {
        Restaurant findResult;
        Optional<Member> member;
        try {
            findResult = restaurantService.findOneById(restaurantId);
            member = memberService.findOneByUserId(param.getUserId());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("restaurant doesn't exist");
        }
        if(member.isEmpty()) {
            //에러 발생. 존재하지 않음.
            return ResponseEntity.status(400).body("restaurant doesn't exist");
        }
        else {
            Restaurant restaurant = (Restaurant) findResult;
            try {
                Member temp = member.get();
                Stars stars = new Stars();
                stars.setStars(param.getStars());
                stars.setRestaurantPK(restaurantId);
                stars.setUserPK(temp.getId());
                starsService.giveStar(stars);
                return ResponseEntity.status(200).body(restaurant.getName());
            } catch (Exception ex){
                return ResponseEntity.status(500).body("Something got wrong");
            }
        }

    }

}
