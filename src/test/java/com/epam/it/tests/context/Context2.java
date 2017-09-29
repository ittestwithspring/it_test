package com.epam.it.tests.context;

import com.epam.it.tests.spring_boot_test.PassportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.epam.it.tests.spring_boot_test.GatewayService;
import com.epam.it.tests.spring_boot_test.GatewayServiceImpl;
import com.epam.it.tests.spring_boot_test.GatewayServiceLazyImpl;
import org.springframework.context.annotation.Lazy;

/**
 * Created by iart0001
 */
@Configuration
public class Context2 {

  private static final Logger log = LoggerFactory.getLogger(Context2.class);

  public Context2() {
    log.warn("Context2 loading");
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
