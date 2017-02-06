package com.flamingo.controllers;

import com.flamingo.Constants;
import junit.framework.Assert;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.junit.Test;

/**
 * Created by Xhulio on 2/8/2016.
 */
public class LocationControllerTest {

    @Test
    public void testGetLocations() throws Exception {
        JSONArray locations = LocationController.getLocations();
        Assert.assertTrue(locations.size() > 0);
    }

    @Test
    public void testCreateLocation() throws Exception {
        String name = "Myslym Shyr - 21";
        String lat = "41.323459";
        String lng = "19.804550";
        Document location = LocationController.createLocation(name, lat, lng);
        Assert.assertTrue(location.containsKey(Constants.Mongo._ID));
    }

    @Test
    public void testUpdateLocation() throws Exception {

    }

    @Test
    public void testDeleteLocation() throws Exception {

    }
}