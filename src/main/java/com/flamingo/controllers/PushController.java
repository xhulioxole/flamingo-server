package com.flamingo.controllers;

import com.flamingo.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class PushController {

    /**
     * Send Push notification
     * @param title title
     * @param message message
     */
    public static boolean sendPush(String title, String message) {
        JSONObject parseObject = new JSONObject();
        JSONArray channels = new JSONArray();
        JSONObject data = new JSONObject();
        data.put("title", title);
        data.put("alert", message);
        channels.add(Constants.Parse.FLAMINGO_CHANNEL);
        parseObject.put("channels", channels);
        parseObject.put("data", data);
        return sendPostRequest(parseObject).getStatus() == 200;
    }
    /**
     * Send post request
     * @param data Data
     * @return Response
     */
    private static Response sendPostRequest(JSONObject data) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(Constants.Parse.API_URL);
        Response response = target.request()
                .header(Constants.Parse.Header.APPLICATION_ID, Constants.Parse.Header.APPLICATION_ID_VALUE)
                .header(Constants.Parse.Header.REST_API, Constants.Parse.Header.REST_API_VALUE)
                .post(Entity.entity(data.toJSONString(), MediaType.TEXT_PLAIN));
        return response;
    }

}
