package MAT.gominsageori.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Autowired
    public void FavoritesController() {
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
    public void getFavoritesList() {

    }

    @ResponseBody
    @PutMapping("/modify")
    public void modifyFavorites() {

    }

    @ResponseBody
    @DeleteMapping("/delete")
    public void deleteFavorites() {

    }
}
