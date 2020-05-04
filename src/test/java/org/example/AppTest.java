package org.example;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @BeforeTest
    public void beforeAllTest(){
        System.out.println("Before All Tests");
    }

    @Test
    public void firstTest()
    {
        System.out.println("firstTest");
        assertTrue( true );
    }

    @Test
    public void secondTest()
    {
        //"https://www.rahulshettyacademy.com/AutomationPractice/"
        System.out.println("secondTest");
        assertTrue( true );
    }

    @Test
    public void thirdTest()
    {
        //"https://www.rahulshettyacademy.com/AutomationPractice/"
        System.out.println("thirdTest");
        assertTrue( false );
    }

    @AfterTest
    public void afterAllTests(){
        System.out.println("After All Tests");
    }
}
