package com.flamingo.utils;

import com.flamingo.Constants;
import org.bson.Document;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Xhulio on 2/8/2016.
 */
public class Utils {

    /**
     *  Check Api key
     * @param apiKey
     * @return
     */
    public static boolean validApiKey(String apiKey) {
        return Objects.equals(apiKey, Constants.Keys.API);
    }

    /**
     * Get name of day
     * @param dayOfWeek
     * @return
     */
    public static String getNameOfDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "E diele";
            case 2:
                return "E Hene";
            case 3:
                return "E Marte";
            case 4:
                return "E Merkure";
            case 5:
                return "E Enjte";
            case 6:
                return "E Premte";
            case 7:
                return "E Shtune";
            default:
                return "";
        }
    }

    /**
     * Get name of location
     * @param id id
     * @param locations Locations
     * @return
     */
    public static Document getLocationById(String id, JSONArray locations) {
        for (int i=0; i<locations.size(); i++) {
            Document location = ((Document) locations.get(i));
            if (location.get("_id").toString().equals(id)) {
                return location;
            }
        }
        return new Document();
    }

    /**
     * Format data for client
     * @param weeklyStatistics
     * @return
     */
    public static JSONArray formatWeeklyStats(HashMap<Integer, Integer> weeklyStatistics) {
        JSONArray weeklyStatisticsFormatted = new JSONArray();
        Integer[] daysOfWeek = {1, 2, 3, 4 , 5, 6, 7};
        String[] days = {"", "E Diele", "E Hene", "E Marte", "E Merkure", "E Enjte", "E Premte", "E Shtune"};
        // Add missing days
        for (int day : daysOfWeek) {
            if (!weeklyStatistics.containsKey(day)) {
                weeklyStatistics.put(day, 0);
            }
        }
        for(int i=2; i<=7; i++) {
            int vehiclesNumber = weeklyStatistics.get(i);
            JSONArray data = new JSONArray();
            data.add(days[i]);
            data.add(vehiclesNumber);
            weeklyStatisticsFormatted.add(data);
        }
        // Add Sunday
        int vehiclesNumber = weeklyStatistics.get(1);
        JSONArray data = new JSONArray();
        data.add(days[1]);
        data.add(vehiclesNumber);
        weeklyStatisticsFormatted.add(data);
        return weeklyStatisticsFormatted;
    }

    /**
     * Format and sort daily stats
     * @param dailyStatistics
     * @return
     */
    public static JSONArray formatDailyStats(HashMap<Integer, Integer> dailyStatistics) {
        JSONArray weeklyStatisticsFormatted = new JSONArray();
        Integer[] hours = {0, 1, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        // Add missing hours
        for (int hour : hours) {
            if (!dailyStatistics.containsKey(hour)) {
                dailyStatistics.put(hour, 0);
            }
        }
        for (int i=0; i<=23; i++) {
            int vehiclesNumber = dailyStatistics.get(i);
            JSONArray data = new JSONArray();
            data.add(hours[i]);
            data.add(vehiclesNumber);
            weeklyStatisticsFormatted.add(data);
        }
        return weeklyStatisticsFormatted;
    }
}
