package com.flamingo.db;

import com.flamingo.Constants;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class DBHelper {

    /**
     * Get support collection
     * @return MongoCollection
     */
    public static MongoCollection getSupportCollection () {
        return getFlamingoDB().getCollection(Constants.Collections.Support.COLLECTION_NAME);
    }

    /**
     * Get statistics collection
     * @return MongoCollection
     */
    public static MongoCollection getStatisticsCollection() {
        return getFlamingoDB().getCollection(Constants.Collections.Statistics.COLLECTION_NAME);
    }

    /**
     * Get users collection
     * @return MongoCollection
     */
    public static MongoCollection getUserCollection() {
        return getFlamingoDB().getCollection(Constants.Collections.User.COLLECTION_NAME);
    }

    /**
     * Get traffic collection
     * @return MongoCollection
     */
    public static MongoCollection getLocationCollection () {
        return getFlamingoDB().getCollection(Constants.Collections.Locations.COLLECTION_NAME);
    }

    /**
     * Get db instance
     * @return MongoDB
     */
    private static MongoDatabase getFlamingoDB() {
        return DB.getConnection().getDatabase(Constants.Mongo.DB);
    }
}
