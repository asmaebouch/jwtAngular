package com.youtube.jwt.controller;

import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


 /*   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerFormData(@ModelAttribute("registerRequest") RegisterRequest request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = service.register(request);
        String token = authenticationResponse.getToken();

        // Set the token as a cookie in the response
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);

        return new ModelAndView("redirect:/api/v1/auth/authenticate");
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView registerJsonData(@RequestBody RegisterRequest request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = service.register(request);
        String token = authenticationResponse.getToken();

        // Set the token as a cookie in the response
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);

        return new ModelAndView("redirect:/api/v1/auth/authenticate");
    }*/
    @GetMapping ("/registerNewUser")
    public String registerNewUserForm(Model model) {
model.addAttribute("user",new User())     ;
return "Registre";
    }

    @PostMapping(value = "/registerNewUser",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerNewUser(@ModelAttribute User user) {
        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
         userService.registerNewUser(user);
         return "redirect:/authenticate";
    }
    @PostMapping(value = "/registerNewUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerNewUserJson(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        userService.registerNewUser(user);
        return "redirect:/authenticate";
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "forAdmin";
    }
//HNA NDIRE hasAnyRole('Admin','User')
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "forUser";
    }
}
