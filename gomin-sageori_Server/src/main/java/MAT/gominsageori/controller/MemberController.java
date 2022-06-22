package MAT.gominsageori.controller;

import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.transfer.SignUpParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @PostMapping("/{id}")
    public ResponseEntity<String> signUp (@PathVariable String id, @ModelAttribute SignUpParam param) {

    }

}
