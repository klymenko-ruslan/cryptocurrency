package com.outsourcebooster.cryptocurrency.web.controller;

import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.model.UserCurrency;
import com.outsourcebooster.cryptocurrency.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rklimemnko on 24.05.2016.
 */
@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("currencies", UserCurrency.values());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(path = "/updateImage", method = RequestMethod.POST)
    public ModelAndView updateImage(@RequestParam("userImageFile") MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.updateUserImageFile(auth.getName(), file);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.setViewName("redirect:/login?logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return modelAndView;
    }
}
