package it.unisannio.CARE.modulep2p;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class P2PManager {
    String request;
    String token;
    String stringBody;
    JSONObject jsonBody;
    RequestType requestType;

    public P2PManager(String request, String token, String stringBody, RequestType type) {
        this.request = request;
        this.token = token;
        this.stringBody = stringBody;
        this.jsonBody = null;
        this.requestType = type;
    }
    public P2PManager(String request, String token, JSONObject jsonBody, RequestType type) {
        this.request = request;
        this.token = token;
        this.stringBody = null;
        this.jsonBody = jsonBody;
        this.requestType = type;
    }
    public P2PManager(String request, String token) {
        this.request = request;
        this.token = token;
        this.jsonBody = null;
        this.stringBody = null;
        this.requestType = null;
    }

    public JSONArray sendRequest() throws IOException, ParseException {
        // Sending get request
        //http://127.0.0.1:8087/user/get/all
        JSONArray responseJson = new JSONArray();
        URL url = new URL(this.request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //imposto il token
        conn.setRequestProperty("Authorization","Bearer "+this.token);

        conn.setRequestProperty("Content-Type", "application/json; UTF-8");
        conn.setRequestMethod(this.requestType.toString());     //imposto il tipo di richiesta


        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        String jsonInputString;
        if (this.stringBody==null){
            jsonInputString = this.jsonBody.toJSONString();
        }else {
            jsonInputString = this.stringBody;
        }

        //invio la richiesta
        try {
            OutputStream os = conn.getOutputStream();
            byte[] input = jsonInputString.getBytes("UTF-8");
            os.write(input,0,input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //leggo la risposta
        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(responseLine.trim());
                responseJson.add(json);
            }
            System.out.println(response.toString());
        }
        return responseJson;
    }

    public void testGet() throws IOException, ParseException {
        JSONArray responseJson = new JSONArray();
        URL url = new URL(this.request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        //String contentType = con.getHeaderField("Content-Type");
        con.setRequestProperty("Authorization","Bearer "+this.token);

        int status = con.getResponseCode();
        System.out.println(status);


        /*BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }*/
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
                /*JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(responseLine.trim());
                responseJson.add(json);*/

            }
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(response.toString().trim());
            System.out.println(json);

            System.out.println(json.get(1));
        }


        //System.out.println(r);
    }

    public void getUsers(){
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("users",);
    }



}
