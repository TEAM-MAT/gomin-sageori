package MAT.gominsageori.controller;


import MAT.gominsageori.domain.RecommandParam;
import MAT.gominsageori.domain.Restaurant;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiController {
    @ResponseBody
    @GetMapping("/recommendation")
    public ResponseEntity<String> Recommend(@RequestBody RecommandParam param){
        //추천 알고리즘 호출 및 리턴값 받기
        return ResponseEntity.status(200).body("hi");
    }

}
