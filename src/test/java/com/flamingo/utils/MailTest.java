package com.flamingo.utils;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Xhulio on 2/4/2016.
 */
public class MailTest {

    @Test
    public void testSendMail() throws Exception {
        String name = "Xhulio";
        String from = "xhulioxole@gmail.com";
        String message = "Test message!";
        Assert.assertTrue(Mail.sendMail(name, from, message));
    }
}