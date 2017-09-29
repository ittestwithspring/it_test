package com.epam.it.tests.context;

import com.epam.it.tests.spring_boot_test.GatewayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by iart0001
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Context2.class})
public class GatewayServiceContext3TestIT {

  @Autowired
  GatewayService gatewayService;

  @Test
  public void gatewayObtainServicesPassed() {
    assertTrue(gatewayService.getGatewayServicesList() != null);
  }
}