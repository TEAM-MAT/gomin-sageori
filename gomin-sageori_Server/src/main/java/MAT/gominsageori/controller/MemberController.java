package MAT.gominsageori.controller;

import MAT.gominsageori.domain.MemberLoginparam;
import MAT.gominsageori.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody  MemberLoginparam payload){
        String result = new String();
        try{
            result = memberService.login(payload);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            result = "404";
        }
        finally {
            return ResponseEntity.status(Integer.parseInt(result)).body(payload.getId());
        }
    }
}
