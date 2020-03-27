package com.gcsj.yunbanke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
   @Autowired
   private  GirlProperties girlProperties;
   /* RequestMapping(value = "/say/{id}",method = RequestMethod.GET)*/
   @GetMapping(value = "/say/{id}")
    public String say(@PathVariable("id") Integer id){
        return "id:" + id;
        /*return girlProperties.getCupSize();*/
    }
}
