package com.epam.it.tests.spring_boot_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by iart0001 on 9/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GatewayService2TestIT {

  private static final int TRANSACTION_ID = 12345;
  private static final String USERNAME = "TEST_USERNAME";

  @Autowired
  GatewayService gatewayService;

  @Before
  public void setUp() throws Exception {
    gatewayService.prepareTransaction(USERNAME, TRANSACTION_ID);
  }

  @After
  public void tearDown() throws Exception {
    gatewayService.cleanUpTransaction(USERNAME, TRANSACTION_ID, 0);
  }

  @Test
  public void gatewayObtainServicesPassed() {
    assertTrue(gatewayService.getGatewayServicesList() != null);
  }
}