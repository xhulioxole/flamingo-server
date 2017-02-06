package com.flamingo.controllers;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Xhulio on 2/8/2016.
 */
public class SupportControllerTest extends TestCase {

    @Test
    public void testCreateSupportMessage() throws Exception {
        String name = "Xhulio";
        String from = "xhulioxole@gmail.com";
        String message = "Good job on the project";
        boolean sent = SupportController.createSupportMessage(name, from, message);
        Assert.assertTrue(sent);
    }
}