package it.unisannio.CARE.spring;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPRequestTestFromJava {
    @Test
    public void testRequest() throws IOException {
        URL yahoo = new URL("http://127.0.0.1:8087/user/get/all");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    @Test
    public void testRequestWithAuthorization() throws IOException {
        // Sending get request
        URL url = new URL("http://127.0.0.1:8087/user/get/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1Njc1NjA1LCJpYXQiOjE2MjU2NzA2MDV9.3EsvmavTjuSYtBO01gYdzVj8NC5vA2-seySU-8sJodbM75WgNc7FBh96RML3grIS3lpwRaFWf0cnEByRrWl-9Q");
        //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5jb206OjE6OjkwIiwiZXhwIjoxNTM3MzQyNTIxLCJpYXQiOjE1MzY3Mzc3MjF9.O33zP2l_0eDNfcqSQz29jUGJC-_THYsXllrmkFnk85dNRbAw66dyEKBP5dVcFUuNTA8zhA83kk3Y41_qZYx43T

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
    }



}
