package uniandes.edu.co.superandes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperAndesController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
}