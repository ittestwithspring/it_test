package com.epam.it.tests.spring_boot_test;

import java.text.ParseException;
import java.util.List;

public interface GatewayService {

  void cleanUpTransaction(String serviceName, int minLimitMs, int maxLimitMs) throws ParseException;
  void prepareTransaction(String sessionName, int cutToMax) throws ParseException;
  List<String> getGatewayServicesList();
}
