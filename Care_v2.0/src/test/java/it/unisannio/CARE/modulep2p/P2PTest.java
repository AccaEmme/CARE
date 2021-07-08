package it.unisannio.CARE.modulep2p;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.util.QRCode;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import org.springframework.security.core.parameters.P;

import java.io.IOException;

public class P2PTest {
    @Test
    public void testHTTPGET() throws IOException, ParseException {
        //String request = "http://127.0.0.1:8087/authenticate";
        String request = "http://127.0.0.1:8087/user/patch/resetpassword/username/peppiniello99";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1Njg1NjYzLCJpYXQiOjE2MjU2ODA2NjN9.x4qCoF1mbeV2xQls6LwkzXOG1AcV_6Xtn6fY156aE426WxzwhbNd2Yxb0tMqVwXolb73r46yp3SL74YseyATjw";

        JSONObject object = new JSONObject();
        object.put("username","follen99");
        object.put("password","Apicelloo9+");
        String jsonInputString = object.toJSONString();
        System.out.println(jsonInputString);

        P2PManager manager = new P2PManager(request,token,jsonInputString,RequestType.POST);

        JSONArray array = manager.sendRequest();
    }

    @Test
    public void testGet() throws IOException, ParseException {
        String request = "http://127.0.0.1:8087/user/get/all";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1Njg1NjYzLCJpYXQiOjE2MjU2ODA2NjN9.x4qCoF1mbeV2xQls6LwkzXOG1AcV_6Xtn6fY156aE426WxzwhbNd2Yxb0tMqVwXolb73r46yp3SL74YseyATjw";

        P2PManager manager = new P2PManager(request,token);
        /**private method*/
        //manager.testGet();
    }

    @Test
    public void testGetUsers() throws IOException, ParseException {
        String request = "http://127.0.0.1:8087/user/get/all";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1Njg1NjYzLCJpYXQiOjE2MjU2ODA2NjN9.x4qCoF1mbeV2xQls6LwkzXOG1AcV_6Xtn6fY156aE426WxzwhbNd2Yxb0tMqVwXolb73r46yp3SL74YseyATjw";

        P2PManager manager = new P2PManager(request,token);
        System.out.println(manager.getTypedBody(new UserDAO()));
    }
    public void testTypedBodyException() throws IOException, ParseException {
        String request = "http://127.0.0.1:8087/user/get/all";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb2xsZW45OSIsInVzZXJSb2xlIjoiUk9MRV9BRE1JTklTVFJBVE9SIiwiZXhwIjoxNjI1Njg1NjYzLCJpYXQiOjE2MjU2ODA2NjN9.x4qCoF1mbeV2xQls6LwkzXOG1AcV_6Xtn6fY156aE426WxzwhbNd2Yxb0tMqVwXolb73r46yp3SL74YseyATjw";
        P2PManager manager = new P2PManager(request,token);


        assertThrows(Exception.class, () -> {
                    manager.getTypedBody(new String());
                }
        );
    }
}