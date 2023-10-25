package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.ItemDto;
import inhatc.spring.shop.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@Slf4j
//@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/thymeleaf/temp")
    public String temp( ){

        return "thymeleaf/temp";
    }

    @GetMapping("/thymeleaf/ex3")
    public String ex3(ParamDto dto, Model model){
        // log로 찍으면 따로 파일로 관리 가능
        log.info("==================> ParamDto : " + dto);
        //System.out.println("==================> ParamDto : " + dto);

        model.addAttribute("param1", dto.getParam1());
        model.addAttribute("param2", dto.getParam2());
        return "thymeleaf/ex3";
    }

    @GetMapping("/thymeleaf/ex2")
    public String ex2( ){

        return "thymeleaf/ex2";
    }

    @GetMapping("/thymeleaf/ex1")
    public String ex1(Model model){

        ItemDto itemDto = ItemDto.builder()
                .itemNm("최신 스프링")
                .itemDetail("스프링 부트 3.1.4")
                .itemSellStatus("SELL")
                .price(20000)
                .build();

        model.addAttribute("itemDto", itemDto);

        //model.addAttribute("data", "값");

        //Point p = new Point(10, 20);
        //model.addAttribute("point", p);

        return "thymeleaf/ex1";
    }
}
