package com.flamingo.utils;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/4/2016.
 */
public class ResponseUtils {

    public static Response ok(JSONObject data) {
        return Response.ok(data.toJSONString()).build();
    }

    public static Response ok() {
        return Response.ok().build();
    }

    public static Response ok(JSONArray array) {
        return Response.ok(array.toJSONString()).build();
    }

    public static Response ok(Document doc) {
        return Response.ok(doc.toJson()).build();
    }

    public static Response error(String message) {
        JSONObject errorObject = new JSONObject();
        errorObject.put("error", message);
        return Response.ok(errorObject.toJSONString()).build();
    }

    public static Response unauthorized(String message) {
        JSONObject errorObject = new JSONObject();
        errorObject.put("error", message);
        return Response.status(401).entity(errorObject.toJSONString()).build();
    }
}
