package com.flamingo.controllers;

import com.flamingo.Constants;
import com.flamingo.db.DBHelper;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import com.mongodb.client.MongoCollection;
import com.nimbusds.jose.JOSEException;
import org.bson.Document;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class UserController {

    /**
     * Login
     * @param email Email
     * @param password password
     * @return data
     */
    public static javax.ws.rs.core.Response login(String email, String password) throws JOSEException {
        MongoCollection userCollection = DBHelper.getUserCollection();
        Document filter = new Document(Constants.Collections.User.EMAIL, email)
                .append(Constants.Collections.User.PASSWORD, StringUtils.SHA1(password));
        Document user = (Document) userCollection.find(filter).first();
        if (user == null) {
            return ResponseUtils.unauthorized("401");
        } else {
            user.remove(Constants.Collections.User.PASSWORD);
            Document document = new Document();
            String token = JWT.generate(user.getObjectId(Constants.Mongo._ID).toString());
            document.put(Constants.Keys.TOKEN, token);
            document.put(Constants.Keys.USER, user);
            return ResponseUtils.ok(document);
        }
    }

    /**
     * Create user
     * @param email user email
     * @param password user password
     * @return created user
     */
    public static Document createUser(String email, String password) {
        MongoCollection userCollection = DBHelper.getUserCollection();
        Document user = new Document();
        user.put(Constants.Collections.User.EMAIL, email);
        user.put(Constants.Collections.User.PASSWORD, StringUtils.SHA1(password));
        userCollection.insertOne(user);
        return user;
    }
}
