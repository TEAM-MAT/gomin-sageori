package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.transfer.FavoritesParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    private MemberService memberService;
    private RestaurantService restaurantService;

    @Autowired
    public void FavoritesController(MemberService memberService, RestaurantService restaurantService) {
        this.memberService = memberService;
        this.restaurantService = restaurantService;
    }

    @ApiOperation(
            value = "즐겨찾기"
            , notes = "MemberId와 restaurantId 값을 받아 즐겨찾기 추가, 삭제, 조회",
            response = String.class
    )
    @ResponseBody
    @PostMapping("/add")
    public void addFavorites() {
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<FavoritesParam> getFavoritesList(@PathVariable("id") Long id) {
        FavoritesParam favoritesParam = new FavoritesParam();
        Optional<Member> member = memberService.findOneById(id);
        try {
            Set<Restaurant> restaurants = memberService.getFavoritesList(member.get());
            List<Restaurant> restaurantList = new ArrayList<>(restaurants);

            int totalCount = 0;
            for(Restaurant iter : restaurantList) {
                totalCount++;
            }

            favoritesParam.setUserfavorites(restaurantList);
            favoritesParam.setCount(totalCount);
            return ResponseEntity.status(200).body(favoritesParam);
        }
        catch (Exception e) {
            favoritesParam.setCount(0);
            return ResponseEntity.status(204).body(favoritesParam);
        }
    }

    /*@ResponseBody
    @PutMapping("/modify")
    public void modifyFavorites() {

    }

    @ResponseBody
    @DeleteMapping("/delete")
    public void deleteFavorites() {

    }*/
}
