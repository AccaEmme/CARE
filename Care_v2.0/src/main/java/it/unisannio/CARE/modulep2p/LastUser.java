package it.unisannio.CARE.modulep2p;

import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;

public class LastUser {
    private String token;
    private String username;

    public LastUser(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public LastUser(String token) throws ParseException {
        this.token = token;
        this.username = this.getUserFromToken(token);
    }

    public void saveLastUser(){

    }

    private String getUserFromToken(String token) throws ParseException {
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(payload);


        if (json.get("sub").toString()==null)
            throw new NullPointerException("Wrong token");
        return json.get("sub").toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
