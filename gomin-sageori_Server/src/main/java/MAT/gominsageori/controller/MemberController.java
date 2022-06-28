package MAT.gominsageori.controller;

import MAT.gominsageori.domain.Member;
import MAT.gominsageori.service.MemberService;
import MAT.gominsageori.transfer.SignInParam;
import MAT.gominsageori.transfer.SignUpParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpParam param) {
        Member member = new Member();
        if (memberService.findOneByUserId(param.getUserId()).isPresent() || memberService.findOneByEmail(param.getEmail()).isPresent()) { //이미 동일 아이디 or email의 유저가 존재하면
            return ResponseEntity.status(409).body("Member with Id or Email already Exists");
        }
        if (param.getUserId() == "" || param.getEmail() == "" || param.getName() == "") {
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
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@ModelAttribute SignInParam param){
        if (param.getUserId() == "" && param.getPassword() == "") {
            return ResponseEntity.status(400).body("blank in id or password");
        }
        Optional<Member> findResult = memberService.findOneByUserId(param.getUserId());

        if(findResult.isEmpty()) {
            return ResponseEntity.status(403).body("fail");
        }
        Member member = findResult.get();
        String encodedInput = memberService.loginPwdEncryption(param.getPassword(),member.getSalt());
        if(encodedInput == member.getPwd()) {
            return ResponseEntity.status(200).body("succeed");
        }
        else {
            return ResponseEntity.status(403).body("fail");
        }
    }

}
