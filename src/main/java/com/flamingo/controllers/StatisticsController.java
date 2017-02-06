package com.flamingo.controllers;

import com.flamingo.Constants;
import com.flamingo.db.DBHelper;
import com.flamingo.utils.DocumentUtils;
import com.flamingo.utils.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class StatisticsController {

    /**
     * Get all data
     * @return
     */
    public static JSONArray getAllData() {
        JSONArray data = new JSONArray();
        FindIterable findIterable = DBHelper.getStatisticsCollection().find();
        findIterable.forEach((Block<Document>) trafficData-> {
            data.add(trafficData);
        });
        return data;
    }

    /**
     * Get statistics
     * @param locationId Location
     * @param startDate Start date
     * @param endDate End date
     * @return Data
     */
    public static JSONObject getStatistics(String locationId, Date startDate, Date endDate) {
        JSONObject statistics = new JSONObject();
        statistics.put(Constants.Keys.LOCATION_STATISTICS, getLocationStatistics(startDate, endDate));
        statistics.put(Constants.Keys.DAILY_STATISTICS, getDailyStatistics(locationId, startDate, endDate));
        statistics.put(Constants.Keys.WEEKLY_STATISTICS, getWeeklyStatistics(locationId, startDate, endDate));
        return statistics;
    }

    /**
     * Get locations statistics by start and end date
     * @param startDate Start date
     * @param endDate End date
     * @return JSONArray of results
     */
    public static JSONArray getLocationStatistics(Date startDate, Date endDate) {
        JSONArray locationStatistics = new JSONArray();
        JSONArray locations = LocationController.getLocations();
        MongoCollection statisticsCollection = DBHelper.getStatisticsCollection();
        BasicDBObject dateFilter = new BasicDBObject();
        dateFilter.put("$gte", startDate);
        dateFilter.put("$lte", endDate);
        Document match = new Document("$match", new Document("date", dateFilter));
        Document group = new Document("$group",
                new Document(Constants.Mongo._ID, "$" + Constants.Collections.Statistics.LOCATION_ID)
                .append("vehicles", new Document("$sum", "$" + Constants.Collections.Statistics.VEHICLES_NUMBER)
        ));
        AggregateIterable<Document> iterable = statisticsCollection.aggregate(Arrays.asList(match, group));
        iterable.forEach((Block<Document>) document -> {
            JSONArray location = new JSONArray();
            Document locationDocument = Utils.getLocationById(document.getString("_id"), locations);
            location.add(locationDocument.getString(Constants.Collections.Locations.NAME));
            location.add(document.get("vehicles"));
            locationStatistics.add(location);
        });
        return locationStatistics;
    }

    /**
     * Get weekly statistics by location, start and end date
     * @param startDate Start date
     * @param endDate End date
     * @return JSONArray of results
     */
    public static JSONArray getWeeklyStatistics(String locationId, Date startDate, Date endDate) {
        HashMap<Integer, Integer> weeklyStatistics = new HashMap<>();
        MongoCollection statisticsCollection = DBHelper.getStatisticsCollection();
        BasicDBObject dateFilter = new BasicDBObject();
        dateFilter.put("$gte", startDate);
        dateFilter.put("$lte", endDate);
        Document matchFilter = new Document("date", dateFilter);
        if (locationId != null && !Objects.equals(locationId, "-1")) {
            matchFilter.put(Constants.Collections.Statistics.LOCATION_ID, locationId);
        }
        Document match = new Document("$match", matchFilter);
        Document dayGroup = new Document("day", new Document("$dayOfWeek", "$date"));
        Document group = new Document("$group",
                new Document(Constants.Mongo._ID, dayGroup)
                        .append("vehicles", new Document("$sum", "$" + Constants.Collections.Statistics.VEHICLES_NUMBER))
        );
        AggregateIterable<Document> iterable = statisticsCollection.aggregate(Arrays.asList(match, group));
        iterable.forEach((Block<Document>) document -> {
            int dayOfWeek = ((Document)document.get("_id")).getInteger("day");
            weeklyStatistics.put(dayOfWeek, (Integer) document.get("vehicles"));
        });
        return Utils.formatWeeklyStats(weeklyStatistics);
    }



    /**
     * Get daily statistics by location, start and end date
     * @param startDate Start date
     * @param endDate End date
     * @return JSONArray of results
     */
    public static JSONArray getDailyStatistics(String locationId, Date startDate, Date endDate) {
        HashMap<Integer, Integer> dailyStatistics = new HashMap<>();
        MongoCollection statisticsCollection = DBHelper.getStatisticsCollection();
        BasicDBObject dateFilter = new BasicDBObject();
        dateFilter.put("$gte", startDate);
        dateFilter.put("$lte", endDate);
        Document matchFilter = new Document("date", dateFilter);
        if (locationId != null && !Objects.equals(locationId, "-1")) {
            matchFilter.put(Constants.Collections.Statistics.LOCATION_ID, locationId);
        }
        Document match = new Document("$match", matchFilter);
        Document dayGroup = new Document("hour", new Document("$hour", "$date"));
        Document group = new Document("$group",
                new Document(Constants.Mongo._ID, dayGroup)
                        .append("vehicles", new Document("$sum", "$" + Constants.Collections.Statistics.VEHICLES_NUMBER))
        );
        AggregateIterable<Document> iterable = statisticsCollection.aggregate(Arrays.asList(match, group));
        iterable.forEach((Block<Document>) document -> {
            int hour = ((Document)document.get("_id")).getInteger("hour");
            dailyStatistics.put(hour, (Integer) document.get("vehicles"));
        });
        return Utils.formatDailyStats(dailyStatistics);
    }

    /**
     * Save traffic data
     * @param locationId Location
     * @param vehiclesNumber Num of vehicles
     * @return JSONObject
     */
    public static JSONObject createStatistic(String locationId, int vehiclesNumber) {
        Document statistic = new Document();
        JSONObject data = new JSONObject();
        if (locationId == null) {
            data.put(Constants.Keys.ERROR, Constants.Messages.BAD_REQUEST);
            return data;
        }
        statistic.put(Constants.Collections.Statistics.LOCATION_ID, locationId);
        statistic.put(Constants.Collections.Statistics.VEHICLES_NUMBER, vehiclesNumber);
        statistic.put(Constants.Collections.Statistics.DATE, new Date());
        DBHelper.getStatisticsCollection().insertOne(statistic);
        return data;
    }

    /**
     * Get statistics by location id
     * @param locationId Location
     * @return JSONArray
     *
     */
    public static JSONArray getDataByLocationId(String locationId) {
        JSONArray data = new JSONArray();
        Document filterDocument = new Document(Constants.Collections.Statistics.LOCATION_ID, locationId);
        FindIterable findIterable = DBHelper.getStatisticsCollection().find(filterDocument);
        findIterable.forEach((Block<Document>) trafficData-> data.add(trafficData));
        return data;
    }

    /**
     * Get statistics for map
     * @param startDate Start date
     * @param endDate End date
     * @return JSONObject
     */
    public static JSONObject getMapStats(Date startDate, Date endDate) {
        JSONObject mapStatsObject = new JSONObject();
        JSONArray mapStatistics = new JSONArray();
        JSONArray locations = LocationController.getLocations();
        MongoCollection statisticsCollection = DBHelper.getStatisticsCollection();
        BasicDBObject dateFilter = new BasicDBObject();
        dateFilter.put("$gte", startDate);
        dateFilter.put("$lte", endDate);
        Document match = new Document("$match", new Document("date", dateFilter));
        Document group = new Document("$group",
                new Document(Constants.Mongo._ID, "$" + Constants.Collections.Statistics.LOCATION_ID)
                        .append("vehicles", new Document("$sum", "$" + Constants.Collections.Statistics.VEHICLES_NUMBER)
                        ));
        AggregateIterable<Document> iterable = statisticsCollection.aggregate(Arrays.asList(match, group));
        iterable.forEach((Block<Document>) document -> {
            JSONObject mapStat = new JSONObject();
            Document locationDocument = Utils.getLocationById(document.getString("_id"), locations);
            mapStat.put(Constants.Keys.LOCATION, locationDocument);
            mapStat.put(Constants.Collections.Statistics.VEHICLES_NUMBER, document.get("vehicles"));
            mapStatistics.add(mapStat);
        });
        mapStatsObject.put(Constants.Keys.MAP_STATISTICS, mapStatistics);
        return mapStatsObject;
    }
}
