package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController 서버가 만든 객체를 들고 가는 것 -> hello 출력
//Controller를 통해 서버안에 resources안에 뷰(templates)로 들어감 -> hello page 출력

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){

        return "index";
    }
}
    //public UserDto hello(){
        ////UserDto userDto = UserDto.builder().age(10).build(); //빌더를 통해 필드가 하나인 것만 만들 수 있음
        //UserDto userDto = new UserDto();
        //userDto.setAge(20);
        //userDto.setName("홍길동");

        //return userDto;
    //}
//}