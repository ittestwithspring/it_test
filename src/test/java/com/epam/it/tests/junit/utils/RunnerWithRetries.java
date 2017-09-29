package com.epam.it.tests.junit.utils;

import org.junit.AssumptionViolatedException;
import org.junit.Ignore;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iart0001
 */
public class RunnerWithRetries extends BlockJUnit4ClassRunner {

  private static final int RETRY_COUNT = 3;
  private static final Logger log = LoggerFactory.getLogger(RunnerWithRetries.class);

  public RunnerWithRetries(Class<?> clazz) throws InitializationError{
    super(clazz);
  }

  @Override
  public void run(final RunNotifier notifier) {
    EachTestNotifier testNotifier = new EachTestNotifier(notifier, getDescription());
    Statement statement = classBlock(notifier);
    try {
      statement.evaluate();
    } catch (AssumptionViolatedException ave) {
      testNotifier.fireTestIgnored();
    } catch (StoppedByUserException sbue) {
      throw sbue;
    } catch (Throwable t) {
      log.warn("Retry class: " + getDescription().getDisplayName());
      retry(testNotifier, statement, t, getDescription());
    }
  }

  @Override
  protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
    Description description = describeChild(method);
    if (method.getAnnotation(Ignore.class) != null) {
      notifier.fireTestIgnored(description);
    } else {
      runTest(methodBlock(method), description, notifier);
    }
  }

  private void runTest(Statement statement, Description description, RunNotifier notifier) {
    EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
    eachNotifier.fireTestStarted();
    try {
      statement.evaluate();
    } catch (AssumptionViolatedException e) {
      eachNotifier.addFailedAssumption(e);
    } catch (Throwable e) {
      log.warn("Test failed: " + e);
      log.warn("Retry test: " + description.getDisplayName());
      retry(eachNotifier, statement, e, description);
    } finally {
      eachNotifier.fireTestFinished();
    }
  }

  private void retry(EachTestNotifier notifier, Statement statement, Throwable currentThrowable, Description info) {
    int failedAttempts = 0;
    Throwable caughtThrowable = currentThrowable;
    while (RETRY_COUNT > failedAttempts) {
      try {
        log.warn("Retry attempt " + (failedAttempts + 1) + " for " + info.getDisplayName());
        statement.evaluate();
        return;
      } catch (Throwable t) {
        failedAttempts++;
        caughtThrowable = t;
        log.warn("Test failed: " + t);
      }
    }
    notifier.addFailure(caughtThrowable);
  }
}
