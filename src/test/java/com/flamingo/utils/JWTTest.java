package com.flamingo.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project: flamingo-server
 * Package: com.flamingo.utils
 * Author: Xhulio
 * Created Date: 2016-02-15 13:35.MD
 * This code is copyright (c) 2016 Prius Solution
 */

public class JWTTest {

    @Test
    public void testGenerate() throws Exception {
        String id = "12321";
        String token = JWT.generate(id);
        Assert.assertTrue(JWT.isValid(token));
    }
}