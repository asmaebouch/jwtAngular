package com.youtube.jwt.controller;

import com.youtube.jwt.entity.JwtRequest;
import com.youtube.jwt.entity.JwtResponse;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;
    @GetMapping({"/authenticate"})
    public String createJwtToken2(Model model) throws Exception {
        model.addAttribute("user",new JwtResponse())     ;
     return "uthen";
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createJwtTokenForm(@ModelAttribute JwtRequest jwtRequest) throws Exception {
        createJwtToken(jwtRequest);
        return "hh";
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createJwtTokenJson(@RequestBody JwtRequest jwtRequest) throws Exception {
        createJwtToken(jwtRequest);
        return "hh";
    }

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
