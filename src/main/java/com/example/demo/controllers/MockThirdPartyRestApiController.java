package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
public class MockThirdPartyRestApiController {
    @GetMapping("/gbo-location/api/v2/locations/national/mobile/{locationCode}")
    public void getThirdPartyResponseWithCookies(@PathVariable String locationCode, HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        //setting session to expiry in 5 mins
        newSession.setMaxInactiveInterval(5 * 60);

        Random random = new Random();
        int randomNumber = random.nextInt(100);
        String gboRegionValue = randomNumber > 30 ? "east" : "west";
        System.out.println("randomNumber ######################################## " + randomNumber);
        System.out.println("gboRegion  ######################################## " + gboRegionValue);
        response.addHeader("Connection", "Keep-Alive");
        response.addHeader("Content-Type", "application/json");
        Cookie cookie = new Cookie("gbo_region", gboRegionValue);
        cookie.setMaxAge(1200);
        System.out.println("max age set###########################");
        response.addCookie(cookie);

    }

}
