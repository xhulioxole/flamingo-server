package com.flamingo;

/**
 * Created by Xhulio on 2/2/2016.
 */
public class Constants {

    public static class Parse {
        public static final String FLAMINGO_CHANNEL = "flamingo";
        public static final String API_URL = "https://api.parse.com/1/push";
        public static final class Header {
            // TODO - Change
            public static final String APPLICATION_ID = "X-Parse-Application-Id";
            public static final String APPLICATION_ID_VALUE = "";
            public static final String REST_API = "X-Parse-REST-API-Key";
            public static final String REST_API_VALUE = "";
        }
    }

    public static class Keys {
        public static final String ERROR = "error";
        public static final String API = "";
        public static final String DELETED = "deleted";
        public static final String LOCATION_STATISTICS = "locationStatistics";
        public static final String DAILY_STATISTICS = "dailyStatistics";
        public static final String WEEKLY_STATISTICS = "weeklyStatistics";
        public static final String LOCATIONS = "locations";
        public static final String MESSAGES = "messages";
        public static final String TOKEN = "token";
        public static final String USER = "user";
        public static final String MAP_STATISTICS = "mapStats";
        public static final String LOCATION = "location";
    }

    public static class Messages {
        public static final String BAD_REQUEST = "Bad Request!";
        public static final String INVALID_TOKEN = "Invalid token!";
        public static final String MAIL_ERROR = "Failed to send email!";
    }

    public static class Mongo {
        public static final String DB = "flamingo";
        public static final String _ID = "_id";
    }
    public static class Collections {
        public static class Support {
            public static final String COLLECTION_NAME = "support";
            public static final String NAME = "name";
            public static final String EMAIL = "email";
            public static final String FROM = "from";
            public static final String MESSAGE = "message";
            public static final String SEND_DATE = "sendDate";
        }

        public static class User {
            public static final String COLLECTION_NAME = "users";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
        }

        public static class Statistics {
            public static final String COLLECTION_NAME = "statistics";
            public static final String VEHICLES_NUMBER = "vehiclesNumber";
            public static final String DATE = "date";
            public static final String LOCATION_ID = "locationId";
        }

        public static class Locations {
            public static final String COLLECTION_NAME = "locations";
            public static final String NAME = "name";
            public static final String LAT = "lat";
            public static final String LNG = "lng";
        }
    }
}
