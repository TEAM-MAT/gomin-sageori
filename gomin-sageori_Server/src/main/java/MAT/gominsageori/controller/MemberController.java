package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.domain.Restaurant;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.service.RestaurantService;
import MAT.gominsageori.transfer.AddFavoritesParam;
import MAT.gominsageori.transfer.FavoritesParam;
import MAT.gominsageori.transfer.SignInParam;
import MAT.gominsageori.transfer.SignUpParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final RestaurantService restaurantService;

    @Autowired
    public MemberController(MemberService memberService, RestaurantService restaurantService) {
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

    @ResponseBody
    @PostMapping("/favorites/signin")
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
        if(encodedInput.equals(member.getPwd())) {
            return ResponseEntity.status(200).body("succeed");
        }
        else {
            return ResponseEntity.status(403).body("fail");
        }
    }

    @ApiOperation(
            value = "즐겨찾기"
            , notes = "MemberId와 restaurantId 값을 받아 즐겨찾기 추가",
            response = String.class
    )
    @ResponseBody
    @PostMapping("/favorites/add")
    public ResponseEntity<String> addFavorites(@RequestBody AddFavoritesParam param) {
        Optional<Member> member = memberService.findOneById(param.getMemberId());
        try {
            Set<Restaurant> restaurants = restaurantService.findSpecificDataById(param.getNewFavorites());
            memberService.saveFavorites(member.get(),restaurants);
            return ResponseEntity.status(200).body(member.get().getUserId());
        }
        catch(Exception e) {
            return ResponseEntity.status(400).body("No data found corresponding to the requested URI.");
        }
    }

    @ApiOperation(
            value = "즐겨찾기"
            , notes = "MemberId와 restaurantId 값을 받아 즐겨찾기 조회",
            response = String.class
    )
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

    @ApiOperation(
            value = "즐겨찾기"
            , notes = "MemberId와 restaurantId 값을 받아 즐겨찾기 삭제",
            response = String.class
    )
    @ResponseBody
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFavorites() {
        return ResponseEntity.status(200).body("작성 예정");
    }
}
