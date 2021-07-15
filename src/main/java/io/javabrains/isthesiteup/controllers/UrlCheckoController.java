
package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckoController {

    private final String IS_the_up = " Site is up !!!, response is working !!!!!";
    private final String IS_the_down = " Site is down !!";
    private final String Incorrect_URL = " incorrect url";

    @GetMapping("/checking")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecategory = conn.getResponseCode() / 100;
            if (responsecategory == 2 || responsecategory == 3) {

                returnMessage = IS_the_up + " response number => " + responsecategory + " ==> "
                + conn.getResponseCode();

            } else {
                returnMessage = IS_the_down + " response number => " + responsecategory + " ==> "
                        + conn.getResponseCode();
            }

        } catch (MalformedURLException e) {
            returnMessage = Incorrect_URL;
            e.printStackTrace();
        } catch (IOException e) {
            returnMessage = IS_the_down;
        }
        return returnMessage;
    }
}
