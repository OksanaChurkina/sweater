package com.example.sweater;


import com.example.sweater.domain.message;
import com.example.sweater.repos.messageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



@Controller
public class GreetingController {

    @Autowired
private messageRepo messageR;


    @GetMapping("/")
    public String greeting(Map<String,Object> model) { return "greeting"; }

    @GetMapping("/main")
    public  String main(Map<String,Object> model)
    {
      Iterable<message> messages =  messageR.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model)
    {


      message msg =  new message(text, tag);
      messageR.save(msg);
        Iterable<message> messages =  messageR.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model)
    {
        Iterable<message> messages;
        if(filter!=null || filter.isEmpty())
        { messages = messageR.findByTag(filter);
        } else {messages = messageR.findAll();}
        model.put("messages", messages);
        return "main";
    }
}
