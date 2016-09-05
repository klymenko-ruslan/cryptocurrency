package com.outsourcebooster.cryptocurrency.web.controller;

import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.model.UserCurrency;
import com.outsourcebooster.cryptocurrency.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by rklimemnko on 24.05.2016.
 */
@RestController
public class MainController {

    @Autowired
    private UserService userService;

    private FacebookConnectionFactory facebookConnectionFactory;
    private String applicationHost;
    public MainController() {
        this.applicationHost = "http://localhost:8081";
        facebookConnectionFactory =
                new FacebookConnectionFactory("229433700788344", "b864ce8ea649509991f1a03a598672df");
    }
    public static final String STATE = "state";
    @RequestMapping("/auth/facebook")
    public RedirectView startAuthentication(HttpSession session) {
        String state = UUID.randomUUID().toString();
        session.setAttribute(STATE, state);

        OAuth2Operations oauthOperations =
                facebookConnectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(applicationHost + "/auth/facebook/callback");
        params.setState(state);

        String authorizeUrl = oauthOperations.buildAuthorizeUrl(
                GrantType.AUTHORIZATION_CODE, params);
        return new RedirectView(authorizeUrl);
    }

    @RequestMapping("/auth/facebook/callback")
    public RedirectView callBack(@RequestParam("code") String code,
                                 @RequestParam("state") String state,
                                 HttpSession session) {
        String stateFromSession = (String) session.getAttribute(STATE);
        session.removeAttribute(STATE);
        if (!state.equals(stateFromSession)) {
            if(true)
            throw new RuntimeException("Here we are" + state + " and " + stateFromSession);
            return new RedirectView("/auth/false");
        }

        session.setAttribute("facebookUserId", getConnection(code));
        return new RedirectView("/auth/hereweare");
    }

    private AccessGrant getAccessGrant(String authorizationCode) {
        OAuth2Operations oauthOperations =
                facebookConnectionFactory.getOAuthOperations();
        return oauthOperations.exchangeForAccess(authorizationCode,
                applicationHost + "/auth/facebook/callback", null);
    }

    private Connection<Facebook> getConnection(String code) {
        return facebookConnectionFactory.createConnection(getAccessGrant(code));
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("currencies", UserCurrency.values());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/activate")
    public ModelAndView activateUser(@RequestParam("activationProperty") String activationProperty) {
        userService.activateUser(activationProperty);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(path = "/updateImage", method = RequestMethod.POST)
    public ModelAndView updateImage(@RequestParam("userImageFile") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userService.updateUserImageFile(auth.getName(), file);
        }
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
