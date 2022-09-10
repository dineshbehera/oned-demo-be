package com.oned.oneddemo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    
    @GetMapping("/home")
    public String home() {
        return "oned-demo is running !";
    }

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int respCodeCategory = conn.getResponseCode() / 100;
            if(respCodeCategory != 2 || respCodeCategory != 3) {
                returnMessage = "Site is UP";

            }
            else {
                returnMessage = "Site is Down";
            }
        } catch (MalformedURLException e) {
            returnMessage = "Incorrect URL";
        } catch (IOException e) {
            returnMessage = "Site is Down";
        }

        return returnMessage;
    }


    
}
