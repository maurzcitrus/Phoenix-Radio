/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.summary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.ft08.phoenix.model.Summary;

/**
 *
 * @author phyo
 */
public class SummaryDaoMySqlTest {

  public SummaryDaoMySqlTest() {
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

  @Test
  public void testFindOne() {
    SummaryDaoMySql dao = new SummaryDaoMySql();
    
    Summary s = dao.findOne();
    
    assertNotNull(s);
    assertTrue(s.getTotalUsers() >= 0);
    assertTrue(s.getTotalProgramSlots() >= 0);
    assertTrue(s.getTotalRadioPrograms() >= 0);
  }

  

}
