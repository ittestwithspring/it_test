package com.epam.it.tests.junit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by iart0001
 */
@RunWith(Categories.class)
@Categories.IncludeCategory (Billing.class)
@Suite.SuiteClasses(FlakyTest.class)
public class BillingSuite {

}
