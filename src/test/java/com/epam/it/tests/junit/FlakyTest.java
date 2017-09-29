package com.epam.it.tests.junit;

import com.epam.it.tests.junit.utils.RunnerWithRetries;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by iart0001
 */
@RunWith(RunnerWithRetries.class)
@Category({Billing.class})
public class FlakyTest{

  private int counter = 0;

  @Test
  public void testRetry() {
    ++counter;
    assertEquals(3, counter);
  }
}
