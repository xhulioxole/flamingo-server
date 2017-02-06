package com.flamingo.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class DB {
    private static String HOST = ""; // pls no harm :'(
    private static String USER = "";
    private static String PASSWORD = "";
    private static String DB = "flamingo";
    private static MongoClient mongoClient;

    /**
     * Init client
     */
    private static void initClient() {
        List<MongoCredential> credentialList = new ArrayList<>();
        credentialList.add(MongoCredential.createScramSha1Credential(USER, DB, PASSWORD.toCharArray()));
        List<ServerAddress> serverAddresses = new ArrayList<>();
        serverAddresses.add(new ServerAddress(HOST));
        mongoClient = new MongoClient(serverAddresses);
    }

    /**
     * Get Mongo Connection Client
     * @return Mongo Client
     */
    public static MongoClient getConnection() {
        if (mongoClient == null ) {
            initClient();
        }
        return mongoClient;
    }

    /**
     * Closes Mongo Connection
     */
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
