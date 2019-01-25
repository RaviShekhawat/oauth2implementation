package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @RequestMapping(value="/helloadmin",method= RequestMethod.POST)
    public String welcomeAdmin()
    {
        return "Hello Admin";
    }

    @RequestMapping(value="/hello",method= RequestMethod.POST)
    public String welcomeAuthorities()
    {
        return "Hello Authorities";
    }

    @RequestMapping(value="/publicuri",method= RequestMethod.GET)
    public String welcomeEveryone()
    {
        return "Hi Everyone";
    }

    @RequestMapping(value="/publishes",method= RequestMethod.POST)
    public String publishUser()
    {
        return "Publish me";
    }

    @RequestMapping(value="/hellouser",method= RequestMethod.POST)
    public String welcomeUser()
    {
        return "Hello User";
    }
}
