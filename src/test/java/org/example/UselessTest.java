package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UselessTest {
    @Test
    public void uselessEmptyTest() {
        System.out.println("uselessEmptyTest");
        Assert.assertTrue(true);
    }

}
