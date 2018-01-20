/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MuraliKrishnaB
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testGetUserId() {
        System.out.println("getUserId");
        User instance = new User();
        String expResult = "";
        String result = instance.getUserId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetUserId() {
        System.out.println("setUserId");
        String userId = "";
        User instance = new User();
        instance.setUserId(userId);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetFullName() {
        System.out.println("getFullName");
        User instance = new User();
        String expResult = "";
        String result = instance.getFullName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetFullName() {
        System.out.println("setFullName");
        String fullName = "";
        User instance = new User();
        instance.setFullName(fullName);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testIsAuthenticated() {
        System.out.println("isAuthenticated");
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.isAuthenticated();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetAuthenticated() {
        System.out.println("setAuthenticated");
        boolean isAuthenticated = false;
        User instance = new User();
        instance.setAuthenticated(isAuthenticated);
        fail("The test case is a prototype.");
    }
    
}
