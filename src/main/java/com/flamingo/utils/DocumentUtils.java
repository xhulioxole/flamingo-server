package com.flamingo.utils;

import com.flamingo.Constants;
import org.bson.Document;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class DocumentUtils {

    /**
     * Replace document objectId with id
     * @param document
     * @return
     */
    public static Document replaceId(Document document) {
        String _id = document.getObjectId(Constants.Mongo._ID).toString();
        document.replace(Constants.Mongo._ID, _id);
        return document;
    }
}
