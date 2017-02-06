package com.flamingo.controllers;

import com.flamingo.Constants;
import junit.framework.Assert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Xhulio on 2/8/2016.
 */
public class StatisticsControllerTest {

    @Test
    public void testGetStatistics() throws Exception {
        long millis = 1452424210000L;
        Date startDate = new Date(millis);
        Date endDate = new Date();
        String locationId = "";
        JSONObject statistics = StatisticsController.getStatistics(locationId, startDate, endDate);
        Assert.assertTrue(statistics.size() > 0);
    }

    @Test
    public void testGetLocationStatistics() throws Exception {
        long millis = 1452424210000L;
        Date startDate = new Date(millis);
        Date endDate = new Date();
        JSONArray locationStatistics = StatisticsController.getLocationStatistics(startDate, endDate);
        Assert.assertTrue(locationStatistics.size() > 0);
    }

    @Test
    public void testGetWeeklyStatistics() throws Exception {
        long millis = 1452424210000L;
        Date startDate = new Date(millis);
        Date endDate = new Date();
        String locationId = "56b8e24ea7c4bc16c81850fd";
        JSONArray locationStatistics = StatisticsController.getWeeklyStatistics(locationId, startDate, endDate);
        Assert.assertTrue(locationStatistics.size() > 0);
    }

    @Test
    public void testGetDailyStatistics() throws Exception {
        long millis = 1452424210000L;
        Date startDate = new Date(millis);
        Date endDate = new Date();
        String locationId = "56b8e24ea7c4bc16c81850fd";
        JSONArray locationStatistics = StatisticsController.getDailyStatistics(locationId, startDate, endDate);
        Assert.assertTrue(locationStatistics.size() > 0);
    }

    @Test
    public void testGetDataByLocationId() throws Exception {

    }

    @Test
    public void testCreateTrafficData() throws Exception {
        String zoguZi = "56b8e24ea7c4bc16c81850fd";
        String inxhNdertimit = "56b8e2c0a7c4bc12409ffb26";
        String kryq21 = "56b8e2dea7c4bc082844e3e2";
        String myslymShyr = "56b8e313a7c4bc1584aed32a";
        JSONObject trafficData = StatisticsController.createStatistic(inxhNdertimit, 2);
        Assert.assertTrue(!trafficData.containsKey(Constants.Keys.ERROR));
    }

    @Test
    public void testGetMapStats() throws Exception {
        long millis = 1452424210000L;
        Date startDate = new Date(millis);
        Date endDate = new Date();
        JSONObject mapStats = StatisticsController.getMapStats(startDate, endDate);
        Assert.assertTrue(mapStats.containsKey("mapStats"));
    }
}