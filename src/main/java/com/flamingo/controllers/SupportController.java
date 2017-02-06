package com.flamingo.controllers;

import com.flamingo.Constants;
import com.flamingo.db.DBHelper;
import com.flamingo.utils.DocumentUtils;
import com.flamingo.utils.Mail;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class SupportController {

    /**
     * Get messages
     * @return
     */
    public static JSONArray getMessages() {
        JSONArray messages = new JSONArray();
        FindIterable findIterable = DBHelper.getSupportCollection().find();
        findIterable.forEach((Block<Document>) message -> {
            DocumentUtils.replaceId(message);
            Date date = message.getDate(Constants.Collections.Support.SEND_DATE);
            message.replace(Constants.Collections.Support.SEND_DATE, date.getTime());
            messages.add(message);
        });
        return messages;
    }

    /**
     * Create support message
     * @param name Name
     * @param from From
     * @param message Message
     * @return boolean
     */
    public static boolean createSupportMessage(String name, String from, String message) {
        try {
            Document support = new Document();
            support.put(Constants.Collections.Support.NAME, name);
            support.put(Constants.Collections.Support.FROM, from);
            support.put(Constants.Collections.Support.MESSAGE, message);
            support.put(Constants.Collections.Support.SEND_DATE, new Date());
            DBHelper.getSupportCollection().insertOne(support);
            Mail.sendMail(name, from, message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update message
     * @param id
     * @param name
     * @param from
     * @param message
     * @return
     */
    public static Document updateMessage(String id, String name, String from, String message) {
        return new Document();
    }

    /**
     * Delete message
     * @param id
     * @return
     */
    public static JSONObject deleteMessage(String id) {
        JSONObject result = new JSONObject();
        result.put(Constants.Keys.DELETED, true);
        return result;
    }
}
