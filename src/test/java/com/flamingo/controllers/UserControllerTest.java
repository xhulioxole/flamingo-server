package com.flamingo.controllers;

import com.flamingo.Constants;
import com.flamingo.utils.StringUtils;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.bson.Document;
import org.junit.Test;

import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class UserControllerTest extends TestCase {

    @Test
    public void testLogin() throws Exception {
        String email = "xhulioxole@gmail.com";
        String password = "xhulio";
        Response response = UserController.login(email, password);
        Assert.assertTrue(response.getStatus() == 200);
    }

    @Test
    public void testCreateUser() throws Exception {
        String email = "xhulioxole@gmail.com";
        String password = "xhulio";
        Document user = UserController.createUser(email, password);
        Assert.assertTrue(user.containsKey(Constants.Mongo._ID));
    }
}