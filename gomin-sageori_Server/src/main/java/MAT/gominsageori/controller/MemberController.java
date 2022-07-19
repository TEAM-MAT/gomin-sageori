package MAT.gominsageori.controller;

import MAT.gominsageori.auth.JwtTokenProvider;
import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.transfer.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;


@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final RestaurantService restaurantService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider, RestaurantService restaurantService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
        this.restaurantService = restaurantService;
    }

    @ApiOperation(
            value = "회원가입"
            , notes = "id,email,name,password를 받아 회원가입을 수행(request body, JSON)",
            response = String.class
    )
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpParam param) {
        Member member = new Member();
        if (memberService.findOneByUserId(param.getUserId()).isPresent() || memberService.findOneByEmail(param.getEmail()).isPresent()) { //이미 동일 아이디 or email의 유저가 존재하면
            return ResponseEntity.status(409).body("Member with Id or Email already Exists");
        }
        if (param.getUserId().equals("")|| param.getEmail().equals("") || param.getName().equals("")) {
            return ResponseEntity.status(400).body("No name or Email or id given");
        }
        member.setUserId(param.getUserId());
        member.setEmail(param.getEmail());
        member.setName(param.getName());

        List<String> tempRoles = new ArrayList<>();
        tempRoles.add("user");
        member.setRoles(tempRoles);

        String pwdBeforeEncryption = param.getPassword();
        HashMap<String,String> saltAndPw = memberService.pwdEncryption(pwdBeforeEncryption);

        member.setPwd(saltAndPw.get("encodedPW"));
        member.setSalt(saltAndPw.get("salt"));
        try{
            memberService.save(member);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("internal error while handling");
        }
        return ResponseEntity.status(200).body(member.getUserId());
    }

    @ApiOperation(
            value = "로그인"
            , notes = "userId , password를 받아 로그인 수행. (request body, JSON), JWT토큰을 리턴해줌. 실패 시 400 status code리턴",
            response = String.class
    )
    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInParam param){
        if (param.getUserId().equals("") && param.getPassword().equals("")) {
            return ResponseEntity.status(400).body("blank in id or password");
        }
        Optional<Member> findResult = memberService.findOneByUserId(param.getUserId());

        if(findResult.isEmpty()) {
            return ResponseEntity.status(403).body("fail");
        }
        Member member = findResult.get();
        String encodedInput = memberService.loginPwdEncryption(param.getPassword(),member.getSalt());
        if(encodedInput.equals(member.getPassword())) {
            return ResponseEntity.status(200).body(jwtTokenProvider.createToken(member.getUserId(), member.getRoles()));
        }
        else {
            return ResponseEntity.status(400).body("fail");
        }
    }

    @ApiOperation(
            value = "즐겨찾기 추가"
            , notes = "MemberId와 추가할 favorites 리스트를 통해 즐겨찾기 추가 (URL : api/member/favorites)",
            response = String.class
    )
    @ResponseBody
    @PostMapping("/favorites")
    public ResponseEntity<String> addFavorites(@RequestBody FavoritesAddParam param) {
        Optional<Member> member = memberService.findOneByUserId(param.getUserId());
        if(!member.isPresent()) {
            return ResponseEntity.status(400).body("No user id data"); // 넘어온 데이터에 해당하는 ID의 member가 없을 시 Exception
        }
        try {
            memberService.getFavoritesList(member.get());
            return ResponseEntity.status(400).body("already have a favorite list"); // 즐겨찾기 목록이 이미 있을 시 Exception
        } catch (Exception e) {
        }
        try {
            Set<Restaurant> restaurants = restaurantService.findRestaurantInfoFromListById(param.getFavorites());
            memberService.saveFavorites(member.get(),restaurants);
            return ResponseEntity.status(200).body(member.get().getUserId());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("No restaurant data corresponding to the requested ID in the list."); // 리스트로 요청된 ID에 해당하는 식당 데이터가 없을 시 Exception
        }
    }

    @ApiOperation(
            value = "즐겨찾기 조회"
            , notes = "MemberId 값을 받아 즐겨찾기 조회 (ex : api/member/1/favorites)",
            response = FavoritesReturnParam.class
    )
    @ResponseBody
    @GetMapping("/{userId}/favorites")
    public ResponseEntity<FavoritesReturnParam> getFavoritesList(@PathVariable("userId") String userId) {
        FavoritesReturnParam favoritesParam = new FavoritesReturnParam();
        Optional<Member> member = memberService.findOneByUserId(userId);
        if(!member.isPresent()) {
            return ResponseEntity.status(400).body(favoritesParam); // URL로 넘어온 데이터에 해당하는 ID의 member가 없을 시 Exception
        }
        try {
            Set<Restaurant> restaurants = memberService.getFavoritesList(member.get());
            List<RestaurantInfoReturnByFavorites> favoritesRestaurantList = new ArrayList<>();

            int totalCount = 0;
            for(Restaurant iter : restaurants) {
                RestaurantInfoReturnByFavorites tempFavorites = new RestaurantInfoReturnByFavorites(iter.getId(),iter.getName(),iter.getNaverMapUrl());
                favoritesRestaurantList.add(tempFavorites);
                totalCount++;
            }

            favoritesParam.setUserfavorites(favoritesRestaurantList);
            favoritesParam.setCount(totalCount);
            return ResponseEntity.status(200).body(favoritesParam);
        } catch (Exception e) {
            favoritesParam.setCount(0);
            return ResponseEntity.status(404).body(favoritesParam); // 즐겨찾기 리스트가 0개일 때
        }
    }

    @ApiOperation(
            value = "즐겨찾기 수정"
            , notes = "MemberId와 추가할 favorites 리스트, 제거할 favorites 리스트를 통해 즐겨찾기 수정 (URL : api/member/favorites)",
            response = String.class
    )
    @ResponseBody
    @PutMapping("/favorites")
    public ResponseEntity<String> modifyFavorites(@RequestBody FavoritesModifyParam param) {
        Optional<Member> member = memberService.findOneByUserId(param.getUserId());
        if(!member.isPresent()) {
            return ResponseEntity.status(400).body("No user id data"); // URI로 넘어온 데이터에 해당하는 ID의 member가 없을 시 Exception
        }
        Set<Restaurant> addRestaurants = new HashSet<>();
        Set<Restaurant> deleteRestaurants = new HashSet<>();
        try {
            addRestaurants = restaurantService.findRestaurantInfoFromListById(param.getAddFavorites());
            deleteRestaurants = restaurantService.findRestaurantInfoFromListById(param.getDeleteFavorites());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("No restaurant data corresponding to the requested ID in the list."); // 리스트로 요청된 ID에 해당하는 식당 데이터가 없을 시 Exception
        }
        try {
            memberService.modifyFavorites(member.get(),addRestaurants,deleteRestaurants);
            return ResponseEntity.status(200).body(member.get().getUserId());
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 추가할 데이터가 이미 있거나 삭제할 데이터가 없을 시 Exception
        }
    }

    @ApiOperation(
            value = "즐겨찾기 삭제"
            , notes = "MemberId를 받아 즐겨찾기 리스트 전체 삭제 (ex : api/member/1/favorites)",
            response = String.class
    )
    @ResponseBody
    @DeleteMapping("/{userId}/favorites")
    public ResponseEntity<String> deleteFavorites(@PathVariable("userId") String userId) {
        Optional<Member> member = memberService.findOneByUserId(userId);
        if(!member.isPresent()) {
            return ResponseEntity.status(400).body("No user id data"); // URL로 넘어온 데이터에 해당하는 ID의 member가 없을 시 Exception
        }
        try {
            memberService.deleteAllFavorites(member.get());
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 즐겨찾기 목록이 전혀 없을 시 Exception
        }
        return ResponseEntity.status(200).body(member.get().getUserId());
    }
}
