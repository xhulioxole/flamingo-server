package com.flamingo.utils;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class StringUtilsTest extends TestCase {

    @Test
    public void testGetAuthorizationHeaderValue() throws Exception {
        String header = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmbGFtaW5nbyIsIl9pZCI6IjU2YzIwM2E4NTNiMzBjMWRiY2U2NGFiNSIsImV4cCI6MTQ1NTY0Mzc4NiwianRpIjoiMTQ1NTU1NzM4Njk2NyJ9.TBtLit0y4mUH57kq79DLhwHQmSTZgF0x6xPxou3YDy8";
        String authorizationHeaderValue = StringUtils.getAuthorizationHeaderValue(header);
        Assert.assertTrue(JWT.isValid(authorizationHeaderValue));
    }
}