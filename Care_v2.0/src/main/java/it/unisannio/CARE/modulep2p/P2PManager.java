package it.unisannio.CARE.modulep2p;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        String jsonInputString;
        if (this.stringBody==null){
            jsonInputString = this.jsonBody.toJSONString();
        }else {
            if (this.jsonBody == null) {
                return this.getResponse();
            }
            jsonInputString = this.stringBody;
        }

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
            }
            String responseSTR = response.toString();
            if (responseSTR.startsWith("[")){
                JSONParser parser = new JSONParser();
                responseJson = (JSONArray) parser.parse(response.toString().trim());
            }else try {
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(response.toString().trim());
                responseJson.add(object);
            } catch (ParseException e) {
                return null;
            }

        }
        return responseJson;
    }

    private JSONArray getResponse() throws IOException, ParseException {
        JSONArray responseJson = new JSONArray();
        URL url = new URL(this.request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization","Bearer "+this.token);

        int status = con.getResponseCode();
        System.out.println(status);



        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());

            }
            JSONParser parser = new JSONParser();
            responseJson = (JSONArray) parser.parse(response.toString().trim());

        }


        return responseJson;
    }

    public JSONArray sendGet() throws Exception {
        JSONArray jsonArray = new JSONArray();
        String url = this.request;

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Authorization","Bearer "+this.token);

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            //System.out.println(response.toString());
            if (response == null)
                return null;

            JSONParser parser = new JSONParser();
            String responseSTR = response.toString();
            if (responseSTR.startsWith("[")){
                jsonArray = (JSONArray) parser.parse(response.toString().trim());
                return jsonArray;
            }
            JSONObject newObj = (JSONObject) parser.parse(responseSTR);
            jsonArray.add(newObj);

            return jsonArray;

        }
    }

    public void sendGetNoResponse() throws IOException {
        String url = this.request;

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Authorization","Bearer "+this.token);

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
    }

    public JSONObject getTypedBody(Object obj) throws IOException, ParseException {
        String objectType;
        if (obj instanceof UserDAO){
            objectType = "users";
        }else if (obj instanceof BloodBagDAO){
            objectType = "bloodbags";
        }else {
            throw new InvalidClassException("the provided object is not a valid object.");
        }

        JSONArray jsonArray = this.getResponse();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put(objectType,jsonArray);

        return jsonObject1;
    }


    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStringBody() {
        return stringBody;
    }

    public void setStringBody(String stringBody) {
        this.stringBody = stringBody;
    }

    public JSONObject getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(JSONObject jsonBody) {
        this.jsonBody = jsonBody;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
