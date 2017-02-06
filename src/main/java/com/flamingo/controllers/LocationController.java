package com.flamingo.controllers;

import com.flamingo.Constants;
import com.flamingo.db.DBHelper;
import com.flamingo.utils.DocumentUtils;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Xhulio on 2/8/2016.
 */
public class LocationController {

    /**
     * Get all location
     * @return locations
     */
    public static JSONArray getLocations() {
        JSONArray locations = new JSONArray();
        FindIterable findIterable = DBHelper.getLocationCollection().find();
        findIterable.forEach((Block<Document>) location -> locations.add(DocumentUtils.replaceId(location)));
        return locations;
    }

    /**
     * Get location by id
     * @return locations
     */
    public static Document getLocationById(String locationId) {
        JSONArray locations = new JSONArray();
        FindIterable findIterable = DBHelper.getLocationCollection().find(new Document(Constants.Mongo._ID, new ObjectId(locationId)));
        return DocumentUtils.replaceId((Document) findIterable.first());
    }

    /**
     * Create location
     * @param name Name
     * @param lat Latitude
     * @param lng Longitude
     * @return Created Document
     */
    public static Document createLocation(String name, String lat, String lng) {
        Document location = new Document();
        location.put(Constants.Collections.Locations.NAME, name);
        location.put(Constants.Collections.Locations.LAT, lat);
        location.put(Constants.Collections.Locations.LNG, lng);
        DBHelper.getLocationCollection().insertOne(location);
        return location;
    }

    /**
     * Update location
     * @param id location id
     * @param name location name
     * @param lat latitude
     * @param lng longitude
     * @return updated document
     */
    public static Document updateLocation(String id, String name, String lat, String lng) {
        return new Document();
    }

    /**
     * Delete location
     * @param id location id
     * @return data with deleted status
     */
    public static JSONObject deleteLocation(String id) {
        JSONObject result = new JSONObject();
        result.put(Constants.Keys.DELETED, true);
        return result;
    }
}
