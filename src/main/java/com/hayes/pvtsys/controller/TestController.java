package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Person;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TestController {

    @GetMapping("/getPersonInfo/{id}")
    public Person getPersonInfo(@PathVariable String id){
        Person person = new Person();
        if ("1".equals(id)){
            person.setName("Hayes");
            person.setAge(30);
        } else {
            person.setName("Hayes younger");
            person.setAge(20);
        }
        person.setAddress("上海市浦东新区");
        person.setEmail("1159013842@qq.com");
        person.setIdNo("321324189911145895");
        person.setBirthDay(LocalDate.parse("1991-01-15"));

        return person;
    }

    @PostMapping("/updatePersonInfo")
    public Person updatePersonInfo(@RequestBody Person person){
        person.setName("this is a new name");
        return person;
    }
}
