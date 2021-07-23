
package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.keyvault.spring.*;

import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;
import com.microsoft.aad.adal4j.AuthenticationContext;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

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

            KeyVaultClient client = new KeyVaultClient(
                    new ClientSecretKeyVaultCredential("f759d22c-9242-41e1-b52d-777187a17eb8", "LrBEWOpC2546z11~.XZ-A38.C4~j8Gq-4H"));
            string KEYVAULT_URL= "https://gfdqakeyvault.vault.azure.net/secrets/GFD-ConnectionString/96ae9d85e8e44dd4b9a1a79edc9b18ff";
            // KEYVAULT_URL is the location of the keyvault to use: https://yourkeyvault.vault.azure.net
            // "testSecret" is the name of the secret in the key vault
            SecretBundle secret = client.getSecret( KEYVAULT_URL, "GFD-ConnectionString" );
            log( secret.value() );

            int responsecategory = conn.getResponseCode() / 100;
            if (responsecategory == 2 || responsecategory == 3) {

                returnMessage = " the value1 " + val1 + " value 2" + val2 + " value 2" + connectionstring + " secret: " + secret.value() ;

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

/*
    private string getkeyvaulvalue()
    {
        string messages ="";
        try{
        azure.keyvault.enabled=true;
        azure.keyvault.uri="https://gfdqakeyvault.vault.azure.net/secrets/GFD-ConnectionString/96ae9d85e8e44dd4b9a1a79edc9b18ff"
        azure.keyvault.client-id=  "f759d22c-9242-41e1-b52d-777187a17eb8";//put-your-azure-client-id-here
        azure.keyvault.client-key=  "LrBEWOpC2546z11~.XZ-A38.C4~j8Gq-4H"; //put-your-azure-client-key-here
        azure.keyvault.tenant-id= "49793faf-eb3f-4d99-a0cf-aef7cce79dc1";  //put-your-azure-tenant-id-here
        azure.keyvault.token-acquire-timeout-seconds=60
        azure.keyvault.refresh-interval=1800000
        azure.keyvault.secret-keys=""
        //azure.keyvault.authority-host=put-your-own-authority-host-here(fill with default value if empty)
        azure.keyvault.secret-service-version=specify secretServiceVersion value(fill with default value if empty)
         var conn =  spring.cloud.azure.appconfiguration.stores[0].connection-string=${CUSTOMCONNSTR_strconnection};
            
        messages += " coonection : " +conn;
    
        }
         catch (Exception e) {
            messages += " error : " + e.getMessage();
        }
        return  messages;
    }
    */


}
