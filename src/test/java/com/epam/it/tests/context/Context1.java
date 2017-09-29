package com.epam.it.tests.context;

import com.epam.it.tests.spring_boot_test.GatewayServiceLazyImpl;
import com.epam.it.tests.spring_boot_test.PassportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.epam.it.tests.spring_boot_test.GatewayService;
import com.epam.it.tests.spring_boot_test.GatewayServiceImpl;
import org.springframework.context.annotation.Lazy;

/**
 * Created by iart0001 on 9/11/17.
 */
@Configuration
public class Context1 {

  private static final Logger log = LoggerFactory.getLogger(GatewayServiceLazyImpl.class);

  public Context1() {
    log.warn("Context1 loading");
  }

  @Bean
  public GatewayService gatewayService(){
    return new GatewayServiceImpl();
  }

  @Bean
  public GatewayService passportService(){
    return new PassportServiceImpl();
  }

  @Lazy
  @Bean
  public GatewayService gatewayServiceLazy(){
    return new GatewayServiceLazyImpl();
  }

}
