
package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class UrlCheckoController {

    private final String IS_the_up = " Site is up !!!, response is working !!!!!";
    private final String IS_the_down = " Site is down !!";
    private final String Incorrect_URL = " incorrect url";

    @ConfigurationProperties
    @GetMapping("/checking")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            // spring.cloud.azure.appconfiguration.stores[0].connection-string=
            // ${APP_CONFIGURATION_CONNECTION_STRING}
            // spring.cloud.azure.appconfiguration.S

            String val1 = System.getenv("javavalue2");
            String val2 = System.getenv("myvalue1");
            String connectionstring = System.getenv("CUSTOMCONNSTR_strconnection");

            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            System.out.println("myvalue1 : " + val2);
            System.out.println("javavalue2 : " + val1);
            System.out.println("strconnection :" + connectionstring);

            int responsecategory = conn.getResponseCode() / 100;
            if (responsecategory == 2 || responsecategory == 3) {

                returnMessage = " the value1 " + val1 + " value 2" + val2 + " value 2" + connectionstring;

                returnMessage = "######" + IS_the_up + " response number => " + responsecategory
                        + "; reponse value ==> " + conn.getResponseCode() + "!!!!!#####" + returnMessage;

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

    
    
    @GetMapping("/jsonresponse")
    public Map<String, String> sayHello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("foo", "bar");
        map.put("aa", "bb");
        return map;
    }

}
