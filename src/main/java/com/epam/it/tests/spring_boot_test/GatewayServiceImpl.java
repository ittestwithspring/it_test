package com.epam.it.tests.spring_boot_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service ("gatewayService")
public class GatewayServiceImpl implements GatewayService {

  private static final Logger log = LoggerFactory.getLogger(GatewayServiceImpl.class);

  @PostConstruct
  public void postInit(){

    //ONLY FOR DEMONSTRATION!!!
    try {
      synchronized (this) {
        this.wait(100);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    log.warn("GatewayServiceImpl initialized");
  }

  @Override
  public void cleanUpTransaction(String serviceName, int minLimitMs, int cutToMaxMs) throws ParseException {
  }

  public List<String> getGatewayServicesList() {
    return new ArrayList<>();
  }

  @Override
  public void prepareTransaction(String sessionName, int cutToMax) throws ParseException {
  }
}
